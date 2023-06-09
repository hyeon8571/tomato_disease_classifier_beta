package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato.classifier.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    public User findByLoginId(String loginId);

    public User findByNickname(String nickname);
}
