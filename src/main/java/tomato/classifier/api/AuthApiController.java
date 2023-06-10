package tomato.classifier.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tomato.classifier.auth.SessionConst;
import tomato.classifier.dto.LoginDto;
import tomato.classifier.dto.SignupDto;
import tomato.classifier.entity.User;
import tomato.classifier.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthApiController {

    private final UserRepository userRepository;

    @PostMapping("/auth/signup")
    public String signup(@Validated @ModelAttribute(name = "signupDto")SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/auth/signup";
        }

        if (!signupDto.getPassword().equals(signupDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "/auth/signup";
        }

        /*
        * 아이디 중복, 닉네임 중복 체크
        * */

        User user = signupDto.convertEntity(signupDto);

        log.info("user: {}", user);

        userRepository.save(user);

        return "redirect:/auth/login";
    }

    @PostMapping("/auth/login")
    public String login(@Validated @ModelAttribute(name = "loginDto")LoginDto loginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/auth/login";
        }

        User user = userRepository.findByLoginId(loginDto.getLoginId());

        if ((user == null) || (!user.getPassword().equals(loginDto.getPassword()))) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/auth/login";
        }

        loginDto.setNickname(user.getNickname());

        loginDto.setRole(user.getRole());

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);

        return "redirect:" + redirectURL;
    }


    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
