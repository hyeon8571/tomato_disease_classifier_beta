package tomato.classifier.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.entity.Article;
import tomato.classifier.service.ArticleService;


@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleApiController {


    private final ArticleService articleService;


    @PostMapping("/add")
    public ResponseEntity<ArticleDto> write(@RequestBody ArticleDto articleDto) {

        ArticleDto created = articleService.create(articleDto);

        return ResponseEntity.status(HttpStatus.OK).body(articleDto);
    }

    @PatchMapping("/edit/{articleId}")
    public ResponseEntity<ArticleDto> edit(@PathVariable Integer articleId, @RequestBody ArticleDto articleDto) {


        ArticleDto updatedDto = articleService.update(articleId, articleDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/delete/{articleId}")
    public ResponseEntity<Article> delete(@PathVariable Integer articleId) {

        Article deleted = articleService.delete(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}

