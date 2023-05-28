package tomato.classifier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tomato.classifier.entity.Article;
import tomato.classifier.entity.Comment;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Integer articleId;

    private String title;

    private String articleWriter;

    private String content;

    private boolean deleteYn;

    private boolean updateYn;

    private String updateTime;

    private List<Comment> comments;

    private Integer commentCount;

    public static ArticleDto convertDto(Article target) {


        return new ArticleDto(
                target.getArticleId(),
                target.getTitle(),
                target.getArticleWriter(),
                target.getContent(),
                target.isDeleteYn(),
                target.isUpdateYn(),
                target.getUpdateTime(),
                target.getComments(),
                target.getComments().size()
        );
    }

}
