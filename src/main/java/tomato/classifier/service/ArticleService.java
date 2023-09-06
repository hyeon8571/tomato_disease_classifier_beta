package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tomato.classifier.domain.dto.ArticleDto;
import tomato.classifier.domain.entity.Article;
import tomato.classifier.domain.entity.User;
import tomato.classifier.handler.ex.CustomApiException;
import tomato.classifier.repository.ArticleRepository;
import tomato.classifier.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public List<ArticleDto> showAll() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleDto> articleDtos = new ArrayList<>();

        articles.stream()
                .filter(article -> article.isDeleteYn() == false)
                .map(article -> ArticleDto.convertDto(article))
                .forEach(articleDto -> articleDtos.add(articleDto));

        return articleDtos;
    }

    public ArticleDto show(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomApiException("게시글 조회를 실패했습니다."));

        ArticleDto articleDto = ArticleDto.convertDto(article);

        return articleDto;
    }

    @Transactional
    public ArticleDto create(ArticleDto articleDto) {

        articleDto.setDeleteYn(false);
        articleDto.setUpdateYn(false);

        String nickname = articleDto.getNickname();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new CustomApiException("유저 조회를 실패했습니다."));

        Article article = Article.convertEntity(articleDto, user);

        Article created = articleRepository.save(article);

        return ArticleDto.convertDto(created);
    }

    @Transactional
    public ArticleDto update(Integer articleId, ArticleDto articleDto) {

        Article target = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomApiException("게시글 조회를 실패했습니다."));

        target.patch(articleDto);

        Article updated = articleRepository.save(target);

        return ArticleDto.convertDto(updated);
    }

    @Transactional
    public Article delete(Integer articleId) {

        Article target = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomApiException("게시글 조회를 실패했습니다."));

        target.delete();

        Article deleted = articleRepository.save(target);

        return deleted;
    }
}
