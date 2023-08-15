package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato.classifier.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // select * from user where username = ?
    Optional<User> findByUsername(String username); // Jpa NameQuery
}
