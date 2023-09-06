package tomato.classifier.domain.dto;

import lombok.*;
import tomato.classifier.domain.entity.Article;
import tomato.classifier.domain.entity.Comment;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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


    @Builder
    public ArticleDto(Integer articleId, String title, String content, String nickname, boolean deleteYn, boolean updateYn, String updateTime, List<Comment> comments, Integer commentCount) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
        this.updateTime = updateTime;
        this.comments = comments;
        this.commentCount = commentCount;
    }


    public static ArticleDto convertDto(Article target) {

        Integer count = 0;
        if(target.getComments() != null) {
            for (Comment comment : target.getComments()) {
                if (!comment.isDeleteYn()) {
                    count++;
                }
            }
        }

        return ArticleDto.builder()
                .articleId(target.getArticleId())
                .title(target.getTitle())
                .content(target.getContent())
                .nickname(target.getUser().getNickname())
                .deleteYn(target.isDeleteYn())
                .updateYn(target.isUpdateYn())
                .updateTime(target.getUpdateTime())
                .comments(target.getComments())
                .commentCount(count)
                .build();
    }

}
