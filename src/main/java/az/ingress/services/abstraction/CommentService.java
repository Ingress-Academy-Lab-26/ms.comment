package az.ingress.services.abstraction;


import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.CommentCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;

public interface CommentService {
    void createComment(CreateOrUpdateCommentRequest request);
    CommentResponse getCommentById(Long id);
    void updateComment(Long id, CreateOrUpdateCommentRequest request);
    void deleteComment(Long id);
    PageableResponse<CommentResponse> getAllComments(CommentCriteria meetupCriteria, PageCriteria pageCriteria);
}
