package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tomato.classifier.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
}
