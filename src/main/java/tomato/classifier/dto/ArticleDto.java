package tomato.classifier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tomato.classifier.entity.Article;
import tomato.classifier.entity.Comment;
import tomato.classifier.entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Integer articleId;

    private String title;

    private String content;

    private String nickname;

    private boolean deleteYn;

    private boolean updateYn;

    private String updateTime;

    private List<Comment> comments;

    private Integer commentCount;


    public static ArticleDto convertDto(Article target) {

        Integer count = 0;
        if(target.getComments() != null) {
            for (Comment comment : target.getComments()) {
                if (!comment.isDeleteYn()) {
                    count++;
                }
            }
        }

        return new ArticleDto(
                target.getArticleId(),
                target.getTitle(),
                target.getContent(),
                target.getUser().getNickname(),
                target.isDeleteYn(),
                target.isUpdateYn(),
                target.getUpdateTime(),
                target.getComments(),
                count
        );
    }

}
