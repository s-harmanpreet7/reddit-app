package in.harmanpreet.learn.reddit.controller;

import in.harmanpreet.learn.reddit.dto.CommentsDto;
import in.harmanpreet.learn.reddit.service.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentsService.save(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentsService.getAllCommentsForPost(postId));
    }

}
