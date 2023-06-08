package tomato.classifier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import tomato.classifier.dto.LoginDto;
import tomato.classifier.dto.SignupDto;

@Controller
public class AuthController {

    @GetMapping("/loginForm")
    public String loginForm(@ModelAttribute(name = "loginDto") LoginDto loginDto) {
        return "auth/login";
    }

    @GetMapping("/signupForm")
    public String signupForm(@ModelAttribute(name = "signupDto") SignupDto signupDto) {
        return "auth/signup";
    }

 }
