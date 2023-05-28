package tomato.classifier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tomato.classifier.dto.CommentDto;
import tomato.classifier.entity.Article;
import tomato.classifier.entity.Comment;
import tomato.classifier.repository.ArticleRepository;
import tomato.classifier.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    public List<CommentDto> comments(Integer articleId) {

        List<Comment> allComments = commentRepository.findByArticleId(articleId);

        List<CommentDto> allCommentDtos = allComments
                .stream()
                .map(comment -> CommentDto.convertDto(comment))
                .collect(Collectors.toList());

        List<CommentDto> comments = new ArrayList<>();

        for (CommentDto comment : allCommentDtos) {
            if (!comment.isDeleteYn()) {
                comments.add(comment);
            }
        }

        return comments;
    }

    @Transactional
    public CommentDto create(int articleId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패, 게시글 조회 실패"));

        commentDto.setDeleteYn(false);
        commentDto.setUpdateYn(false);

        Comment comment = Comment.convertEntity(commentDto, article);

        Comment created = commentRepository.save(comment);

        return CommentDto.convertDto(created);
    }

    @Transactional
    public CommentDto update(Integer commentId, CommentDto commentDto) {

        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 id 오류"));

        target.patch(commentDto);

        Comment updated = commentRepository.save(target);

        return CommentDto.convertDto(updated);
    }

    @Transactional
    public CommentDto delete(Integer commentId) {

        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 id 오류"));

        target.delete();

        Comment deleted = commentRepository.save(target);

        return CommentDto.convertDto(deleted);
    }
}
