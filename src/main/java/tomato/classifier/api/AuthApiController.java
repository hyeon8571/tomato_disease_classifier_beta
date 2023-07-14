package tomato.classifier.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.dto.LoginDto;
import tomato.classifier.dto.SignupDto;
import tomato.classifier.entity.User;
import tomato.classifier.repository.UserRepository;
import tomato.classifier.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthApiController {

    private final UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public String signup(@Validated @ModelAttribute(name = "signupDto")SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/auth/signup";
        }

        if (!signupDto.getPassword().equals(signupDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "/auth/signup";
        }

        if (userRepository.findByLoginId(signupDto.getLoginId()) != null) {
            bindingResult.rejectValue("loginId", "idDuplication", "이미 존재하는 ID 입니다.");
            return "/auth/signup";
        }

        if (userRepository.findByNickname(signupDto.getNickname()) != null) {
            bindingResult.rejectValue("nickname", "nicknameDuplication", "이미 존재하는 닉네임 입니다.");
            return "/auth/signup";
        }

        authService.signup(signupDto);

        return "/message/signupMessage";
    }

    @PostMapping("/auth/login")
    public String login(@Validated @ModelAttribute(name = "loginDto")LoginDto loginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "/auth/login";
        }

        try {
            User user = userRepository.findByLoginId(loginDto.getLoginId());
            if (!user.getPassword().equals(loginDto.getPassword())) {
                bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return "/auth/login";
            }
        } catch (NullPointerException e) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "/auth/login";
        }

        User user = userRepository.findByLoginId(loginDto.getLoginId());

        authService.login(loginDto, user, request);

        return "redirect:" + redirectURL;
    }


    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:" + request.getHeader("Referer");
    }


}
