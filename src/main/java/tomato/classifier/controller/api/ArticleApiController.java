package tomato.classifier.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.domain.dto.ArticleDto;
import tomato.classifier.domain.dto.ResponseDto;
import tomato.classifier.domain.entity.Article;
import tomato.classifier.service.ArticleService;


@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping()
    public ResponseEntity<?> write(@RequestBody ArticleDto articleDto) {

        ArticleDto createdDto = articleService.create(articleDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "계시글 등록 완료", createdDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<?> edit(@PathVariable Integer articleId, @RequestBody ArticleDto articleDto) {

        ArticleDto updatedDto = articleService.update(articleId, articleDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "계시글 수정 완료", updatedDto), HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> delete(@PathVariable Integer articleId) {

        Article deleted = articleService.delete(articleId);

        return new ResponseEntity<>(new ResponseDto<>(1, "계시글 삭제 완료", deleted), HttpStatus.OK);
    }
}
