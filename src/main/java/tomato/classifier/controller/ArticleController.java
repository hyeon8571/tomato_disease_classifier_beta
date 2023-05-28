package tomato.classifier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.dto.CommentDto;
import tomato.classifier.repository.ArticleRepository;
import tomato.classifier.service.ArticleService;
import tomato.classifier.service.CommentService;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping()
    public String articleMain(Model model) {


        List<ArticleDto> articleDtos = articleService.showAll();

        Collections.reverse(articleDtos);

        model.addAttribute("articles", articleDtos);

        return "board/articleMain";
    }

    @GetMapping("/add")
    public String articleAdd(Model model) {
        return "board/articleAdd";
    }

    @GetMapping("/{articleId}")
    public String articleDetail(@PathVariable Integer articleId, Model model) {

        ArticleDto articleDto = articleService.show(articleId);


        List<CommentDto> comments = commentService.comments(articleId);


        model.addAttribute("article", articleDto);

        model.addAttribute("comments", comments);

        return "board/articleDetail";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer articleId, Model model) {

        ArticleDto articleDto = articleService.show(articleId);

        model.addAttribute("article", articleDto);

        return "board/articleUpdate";
    }

}
