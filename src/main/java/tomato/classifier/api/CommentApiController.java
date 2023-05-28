package tomato.classifier.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.dto.CommentDto;
import tomato.classifier.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private CommentService commentService;

    @PostMapping("/article/{articleId}/comment-add")
    public ResponseEntity<CommentDto> write(@PathVariable Integer articleId, @RequestBody CommentDto commentDto) {


        CommentDto dto = commentService.create(articleId, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping("/comment-edit/{commentId}")
    public ResponseEntity<CommentDto> edit(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {

        CommentDto updateDto = commentService.update(commentId, commentDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }


    @DeleteMapping("/comment-delete/{commentId}")
    public ResponseEntity<CommentDto> delete(@PathVariable Integer commentId) {

        CommentDto deletedDto = commentService.delete(commentId);

        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

    public void hello() {

    }
}

