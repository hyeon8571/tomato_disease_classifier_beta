package tomato.classifier.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomato.classifier.dto.CommentDto;

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

    @Column
    private String commentWriter;

    @Column
    private String content;

    @Column
    private boolean deleteYn;

    @Column
    private boolean updateYn;

    public static Comment convertEntity(CommentDto commentDto, Article article) {
        if (commentDto.getCommentId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패, id가 존재");
        }

        if (commentDto.getArticleId() != article.getArticleId()) {
            throw new IllegalArgumentException("err, 게시글이 일치하지 않음");
        }

        return new Comment(
                commentDto.getCommentId(),
                article,
                commentDto.getCommentWriter(),
                commentDto.getContent(),
                commentDto.isDeleteYn(),
                commentDto.isUpdateYn()
        );
    }


    public void patch(CommentDto commentDto) {
        if (this.commentId != commentDto.getCommentId()) {
            throw new IllegalArgumentException("게시글 id가 일치하지 않습니다.");
        }

        if (commentDto.getContent() != "") {
            this.content = commentDto.getContent();
            this.updateYn = true;
        }
    }

    public void delete() {
        this.deleteYn = true;
    }

}
