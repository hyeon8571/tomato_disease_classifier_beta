package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tomato.classifier.auth.SessionConst;
import tomato.classifier.dto.LoginDto;
import tomato.classifier.dto.SignupDto;
import tomato.classifier.entity.User;
import tomato.classifier.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupDto signupDto) {

        try {
            User user = signupDto.convertEntity(signupDto);
            userRepository.save(user);
        } catch (RuntimeException e) {
            throw e;
        }

    }

    @Transactional
    public void login(LoginDto loginDto, User user, HttpServletRequest request) {

        try {
            loginDto.setNickname(user.getNickname());

            loginDto.setRole(user.getRole());

            HttpSession session = request.getSession();

            session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);
        } catch (RuntimeException e) {
            throw e;
        }

    }
}
