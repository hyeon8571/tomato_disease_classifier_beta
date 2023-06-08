package tomato.classifier.api;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

@Controller
@RequiredArgsConstructor
public class AuthApiController {

    private final UserRepository userRepository;

    @PostMapping("/auth/signup")
    public String signup(@Validated @ModelAttribute(name = "signupDto")SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/auth/signup";
        }

    }


    @PostMapping("/auth/login")
    public String login(@Validated @ModelAttribute(name = "loginDto")LoginDto loginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/")String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/auth/login";
        }

        /*
        * 시큐리티 적용 전 임시 로그인 로직
        */
        User user = userRepository.findByLoginId(loginDto.getLoginId());

        if (user == null || user.getPassword() != loginDto.getPassword()) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/auth/login";
        }

        loginDto.setUsername(user.getUsername());

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);

        return "redirect:" + redirectURL;
    }


    @PostMapping("/auth/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
