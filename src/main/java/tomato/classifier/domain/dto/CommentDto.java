package tomato.classifier.domain.dto;

import lombok.*;
import tomato.classifier.domain.entity.Comment;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Integer commentId;

    private Integer articleId;

    private String nickname;

    private String content;

    private boolean deleteYn;

    private boolean updateYn;

    private LocalDateTime modifiedTime;

    @Builder
    public CommentDto(Integer commentId, Integer articleId, String nickname, String content, boolean deleteYn, boolean updateYn, LocalDateTime modifiedTime) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.nickname = nickname;
        this.content = content;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
        this.modifiedTime = modifiedTime;
    }

    public static CommentDto convertDto(Comment target) {

        return CommentDto.builder()
                .commentId(target.getCommentId())
                .articleId(target.getArticle().getArticleId())
                .nickname(target.getUser().getNickname())
                .content(target.getContent())
                .deleteYn(target.isDeleteYn())
                .updateYn(target.isUpdateYn())
                .modifiedTime(target.getModifiedTime())
                .build();
    }


}
