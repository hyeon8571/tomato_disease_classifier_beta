package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.entity.Article;
import tomato.classifier.repository.ArticleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleDto> showAll() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articles) {

            if (!article.isDeleteYn()) {

                ArticleDto articleDto = ArticleDto.convertDto(article);
                articleDtos.add(articleDto);
            }
        }
        return articleDtos;
    }

    public ArticleDto show(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("id err"));

        ArticleDto articleDto = ArticleDto.convertDto(article);

        return articleDto;
    }

    @Transactional
    public ArticleDto create(ArticleDto articleDto) {
        articleDto.setDeleteYn(false);
        articleDto.setUpdateYn(false);

        Article article = Article.convertEntity(articleDto);


        Article created = articleRepository.save(article);

        return ArticleDto.convertDto(created);
    }

    @Transactional
    public ArticleDto update(Integer articleId, ArticleDto articleDto) {
        Article target = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 조회 실패!"));

        target.patch(articleDto);

        Article updated = articleRepository.save(target);

        return ArticleDto.convertDto(updated);
    }

    @Transactional
    public Article delete(Integer articleId) {
        Article target = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("id err"));

        target.delete();

        Article deleted = articleRepository.save(target);

        return deleted;
    }
}
