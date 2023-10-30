package tomato.classifier.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tomato.classifier.domain.dto.CommentDto;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {

    private Long articleId;

    private Long parentCommentId;

    private String content;

    @Builder
    public CommentRequest(Long articleId, Long parentCommentId, String content) {
        this.articleId = articleId;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public CommentDto toDto() {
        return CommentDto.builder()
                .articleId(this.articleId)
                .parentCommentId(this.parentCommentId)
                .content(this.content)
                .build();
    }
}
