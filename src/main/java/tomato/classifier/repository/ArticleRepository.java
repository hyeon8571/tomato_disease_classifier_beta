package tomato.classifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomato.classifier.domain.entity.Article;


public interface ArticleRepository extends JpaRepository<Article, Integer>, ArticleCustomRepository {
}
