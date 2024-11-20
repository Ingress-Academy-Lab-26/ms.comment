package az.ingress.services.concrete;

import az.ingress.annotation.Log;
import az.ingress.dao.entity.CommentEntity;
import az.ingress.dao.repository.CommentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.CommentCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;
import az.ingress.services.abstraction.CommentService;
import az.ingress.services.specification.CommentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static az.ingress.mapper.CommentMapper.COMMENT_MAPPER;
import static az.ingress.model.enums.CommentStatus.*;
import static az.ingress.model.enums.ExceptionConstants.COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class CommentServiceHandler implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(CreateOrUpdateCommentRequest request) {
        var commentEntity = COMMENT_MAPPER.buildCommentEntity(request);
        commentRepository.save(commentEntity);
    }

    @Override
    public CommentResponse getCommentById(Long id) {
        var comment = fetchCommentExist(id);
        return COMMENT_MAPPER.buildCommentResponse(comment);
    }

    @Override
    public PageableResponse<CommentResponse> getAllComments(CommentCriteria CommentCriteria,
                                                            PageCriteria pageCriteria) {
        var specification = new CommentSpecification(CommentCriteria);
        var pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
        var comments = commentRepository.findAll(specification, pageable);
        return COMMENT_MAPPER.buildPageableResponse(comments);
    }

    @Override
    public void updateComment(Long id, CreateOrUpdateCommentRequest request) {
        var comment = fetchCommentExist(id);
        COMMENT_MAPPER.updateComment(comment, request);
        commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long id) {
        var commentEntity = fetchCommentExist(id);
        commentEntity.setStatus(DELETED);
        commentRepository.save(commentEntity);
    }

    private CommentEntity fetchCommentExist(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(COMMENT_NOT_FOUND.getCode(), COMMENT_NOT_FOUND.getMessage()));
    }
}
