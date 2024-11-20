package az.ingress.controller;

import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.CommentCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;
import az.ingress.services.abstraction.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createComment(@RequestBody @Valid CreateOrUpdateCommentRequest request) {
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
    public void updateComment(@PathVariable Long id, @RequestBody @Valid CreateOrUpdateCommentRequest request) {
        commentService.updateComment(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}