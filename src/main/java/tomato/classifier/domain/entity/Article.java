package tomato.classifier.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomato.classifier.domain.dto.ArticleDto;
import tomato.classifier.handler.ex.CustomApiException;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String content;

    @Column
    private boolean deleteYn;

    @Column
    private boolean updateYn;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // article 엔티티의 comments 필드의 주인은 반대편(commnet 엔티티)의 article필드
    private List<Comment> comments;


    @Builder
    public Article(Integer articleId, String title, User user, String content, boolean deleteYn, boolean updateYn, List<Comment> comments) {
        this.articleId = articleId;
        this.title = title;
        this.user = user;
        this.content = content;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
        this.comments = comments;
    }

    public static Article convertEntity(ArticleDto target, User user) {
        if(target.getArticleId() != null)
            throw new CustomApiException("DTO -> ENTITY 실패, Article");

        return Article.builder()
                .articleId(target.getArticleId())
                .title(target.getTitle())
                .user(user)
                .content(target.getContent())
                .deleteYn(target.isDeleteYn())
                .updateYn(target.isUpdateYn())
                .comments(target.getComments())
                .build();

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
