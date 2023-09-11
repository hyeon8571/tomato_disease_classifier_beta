package tomato.classifier.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomato.classifier.domain.dto.CommentDto;
import tomato.classifier.handler.ex.CustomApiException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String content;

    @Column
    private boolean deleteYn;

    @Column
    private boolean updateYn;

    @Builder
    public Comment(Integer commentId, Article article, User user, String content, boolean deleteYn, boolean updateYn) {
        this.commentId = commentId;
        this.article = article;
        this.user = user;
        this.content = content;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
    }

    public static Comment convertEntity(CommentDto commentDto, Article article, User user) {
        if (commentDto.getCommentId() != null) {
            throw new CustomApiException("Dto -> Entity 전환을 실패했습니다.");
        }

        if (commentDto.getArticleId() != article.getArticleId()) {
            throw new CustomApiException("Dto -> Entity 전환을 실패했습니다.");
        }

        return Comment.builder()
                .commentId(commentDto.getCommentId())
                .article(article)
                .user(user)
                .content(commentDto.getContent())
                .deleteYn(commentDto.isDeleteYn())
                .updateYn(commentDto.isUpdateYn())
                .build();
    }


    public void patch(CommentDto commentDto) {
        if (this.commentId != commentDto.getCommentId()) {
            throw new CustomApiException("댓글 수정을 실패했습니다.");
        }

        this.content = commentDto.getContent();
        this.updateYn = true;

    }

    public void delete() {
        this.deleteYn = true;
    }

}
