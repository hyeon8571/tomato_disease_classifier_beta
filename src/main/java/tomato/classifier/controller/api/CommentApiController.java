package tomato.classifier.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomato.classifier.domain.dto.CommentDto;
import tomato.classifier.domain.dto.ResponseDto;
import tomato.classifier.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentApiController {

    private  final CommentService commentService;

    @PostMapping()
    public ResponseEntity<?> write(@RequestBody CommentDto commentDto) {

        CommentDto createdDto = commentService.create(commentDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 등록 완료", createdDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> edit(@PathVariable Integer commentId, @RequestBody CommentDto commentDto) {

        CommentDto updateDto = commentService.update(commentId, commentDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 수정 완료", updateDto), HttpStatus.OK);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Integer commentId) {

        CommentDto deletedDto = commentService.delete(commentId);

        return new ResponseEntity<>(new ResponseDto<>(1, "댓글 삭제 완료", deletedDto), HttpStatus.OK);
    }

}

