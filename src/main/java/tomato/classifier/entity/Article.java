package tomato.classifier.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomato.classifier.dto.ArticleDto;
import tomato.classifier.handler.ex.CustomApiException;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "loginId")
    private User user;

    @Column
    private String content;

    @Column
    private boolean deleteYn;

    @Column
    private boolean updateYn;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // article 엔티티의 comments 필드의 주인은 반대편(commnet 엔티티)의 article필드
    private List<Comment> comments;


    public static Article convertEntity(ArticleDto target, User user) {
        if(target.getArticleId() != null)
            throw new CustomApiException("DTO -> ENTITY 실패, Article");

        return new Article(
                target.getArticleId(),
                target.getTitle(),
                user,
                target.getContent(),
                target.isDeleteYn(),
                target.isUpdateYn(),
                target.getComments()
        );
    }

    public void patch(ArticleDto articleDto) {
        if (this.articleId != articleDto.getArticleId()) {
            throw new CustomApiException("게시글 수정을 실패했습니다.");
        }

        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.updateYn = true;


    }

    public void delete() {
        this.deleteYn = true;

        for (Comment comment : this.comments) {
            comment.delete();
        }
    }
}
