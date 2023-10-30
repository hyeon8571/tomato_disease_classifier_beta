package tomato.classifier.domain.dto;

import lombok.*;
import tomato.classifier.domain.entity.Comment;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Long commentId;

    private Long articleId;

    private String nickname;

    private Long parentCommentId;

    private String content;

    private boolean deleteYn;

    private boolean updateYn;

    private LocalDateTime modifiedTime;

    @Builder
    public CommentDto(Long commentId, Long articleId, String nickname, Long parentCommentId, String content, boolean deleteYn, boolean updateYn, LocalDateTime modifiedTime) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.nickname = nickname;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
        this.modifiedTime = modifiedTime;
    }

    public static CommentDto toDto(Comment target) {

        return CommentDto.builder()
                .commentId(target.getCommentId())
                .articleId(target.getArticle().getArticleId())
                .nickname(target.getUser().getNickname())
                .parentCommentId(target.getParentCommentId())
                .content(target.getContent())
                .deleteYn(target.isDeleteYn())
                .updateYn(target.isUpdateYn())
                .modifiedTime(target.getModifiedTime())
                .build();
    }

}
