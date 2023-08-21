package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomato.classifier.entity.User;
import tomato.classifier.repository.UserRepository;

import javax.servlet.http.Cookie;

import static tomato.classifier.dto.user.UserReqDto.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signup(JoinReqDto joinReqDto) {
        User user = joinReqDto.toEntity(passwordEncoder);
        userRepository.save(user);
    }

    public Cookie expireCookie(String tokenKey) {
        Cookie cookie = new Cookie(tokenKey, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }

}
