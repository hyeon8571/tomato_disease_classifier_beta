package tomato.classifier.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.dto.ResponseDto;
import tomato.classifier.entity.Article;
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

        System.out.println("접근");

        Article deleted = articleService.delete(articleId);

        return new ResponseEntity<>(new ResponseDto<>(1, "계시글 삭제 완료", deleted), HttpStatus.OK);
    }
}

