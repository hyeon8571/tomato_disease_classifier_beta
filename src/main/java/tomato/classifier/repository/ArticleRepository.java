package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomato.classifier.entity.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
