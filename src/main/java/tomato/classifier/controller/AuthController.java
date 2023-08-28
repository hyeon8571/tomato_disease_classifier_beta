package tomato.classifier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import static tomato.classifier.dto.user.UserReqDto.*;


@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute JoinReqDto joinReqDto) {
        return "auth/signup";
    }

    @GetMapping("/err-page")
    public String errPage() {
        return "message/unAuthenticatedMessage";
    }
 }
