package tomato.classifier.repository;

import tomato.classifier.domain.entity.Comment;

import java.util.List;

public interface CommentCustomRepository {
    List<Comment> findByArticleId(Integer articleId);
}
