package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tomato.classifier.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentCustomRepository {


}
