package tomato.classifier.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tomato.classifier.entity.Comment;
import tomato.classifier.entity.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer commentId;

    private Integer articleId;

    private String nickname;

    private String content;

    private boolean deleteYn;

    private boolean updateYn;

    private String updateTime;

    public static CommentDto convertDto(Comment target) {

        return new CommentDto(
                target.getCommentId(),
                target.getArticle().getArticleId(),
                target.getUser().getNickname(),
                target.getContent(),
                target.isDeleteYn(),
                target.isUpdateYn(),
                target.getUpdateTime()
        );
    }


}
