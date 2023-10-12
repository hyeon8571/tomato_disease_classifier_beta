package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tomato.classifier.domain.dto.ArticleDto;
import tomato.classifier.domain.entity.Article;
import tomato.classifier.domain.entity.User;
import tomato.classifier.domain.type.SearchType;
import tomato.classifier.handler.ex.CustomApiException;
import tomato.classifier.repository.ArticleRepository;
import tomato.classifier.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::convertDto);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::convertDto);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::convertDto);
            case NICKNAME -> articleRepository.findByUser_NicknameContaining(searchKeyword, pageable).map(ArticleDto::convertDto);
        };
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
