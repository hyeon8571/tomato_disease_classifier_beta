package tomato.classifier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.config.auth.LoginUser;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.entity.User;
import tomato.classifier.handler.ex.CustomApiException;
import tomato.classifier.repository.UserRepository;
import tomato.classifier.service.ArticleService;
import tomato.classifier.service.CommentService;
import java.util.Collections;
import java.util.List;


import static tomato.classifier.dto.user.UserResDto.*;

@Controller
@Slf4j
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserRepository userRepository;

    @GetMapping()
    public String articleMain(Model model) {


        List<ArticleDto> articleDtos = articleService.showAll();

        Collections.reverse(articleDtos);

        model.addAttribute("articles", articleDtos);

        return "board/articleMain";
    }

    @GetMapping("/writeForm")
    public String articleAdd(@AuthenticationPrincipal LoginUser loginUser, Model model) {

        String loginId = loginUser.getUsername();

        // 받아온 아이디를 이용해서 유저 디티오
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomApiException("유저 조회를 실패했습니다."));

        LoginResDto loginResDto = new LoginResDto(user);

        model.addAttribute("loginUser", loginResDto);

        return "board/articleAdd";
    }

//    @GetMapping("/{articleId}")
//    public String articleDetail(@AuthenticationPrincipal LoginUser loginUser, @PathVariable Integer articleId, Model model) {
//
//        ArticleDto articleDto = articleService.show(articleId);
//
//        List<CommentDto> comments = commentService.comments(articleId);
//
//
//        if (loginUser != null) {
//            User user = loginUser.getUser();
//            LoginResDto loginResDto = new LoginResDto(user);
//            model.addAttribute("loginUser", loginResDto);
//        } else {
//            model.addAttribute("loginUser", new );
//        }
//
//        model.addAttribute("article", articleDto);
//
//        model.addAttribute("comments", comments);
//
//        return "board/articleDetail";
//    }

    @GetMapping("/editForm")
    public String edit(@RequestParam Integer articleId, Model model) {

        ArticleDto articleDto = articleService.show(articleId);

        model.addAttribute("article", articleDto);

        return "board/articleUpdate";
    }

}
