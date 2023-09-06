package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato.classifier.domain.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentCustomRepository {


}
