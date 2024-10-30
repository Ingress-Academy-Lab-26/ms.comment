package az.ingress.controller;

import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.CommentCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;
import az.ingress.services.abstraction.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CreateOrUpdateCommentRequest request) {
        commentService.createComment(request);
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping
    public PageableResponse<CommentResponse> getAllComments(CommentCriteria commentCriteria,
                                                            PageCriteria pageCriteria) {
        return commentService.getAllComments(commentCriteria, pageCriteria);
    }
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateComment(@PathVariable Long id, @RequestBody CreateOrUpdateCommentRequest request) {
        commentService.updateComment(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
