package tomato.classifier.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomato.classifier.dto.CommentDto;
import tomato.classifier.handler.ex.CustomApiException;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "loginId")
    private User user;

    @Column
    private String content;

    @Column
    private boolean deleteYn;

    @Column
    private boolean updateYn;

    public static Comment convertEntity(CommentDto commentDto, Article article, User user) {
        if (commentDto.getCommentId() != null) {
            throw new CustomApiException("Dto -> Entity 전환을 실패했습니다.");
        }

        if (commentDto.getArticleId() != article.getArticleId()) {
            throw new CustomApiException("Dto -> Entity 전환을 실패했습니다.");
        }

        return new Comment(
                commentDto.getCommentId(),
                article,
                user,
                commentDto.getContent(),
                commentDto.isDeleteYn(),
                commentDto.isUpdateYn()
        );
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
