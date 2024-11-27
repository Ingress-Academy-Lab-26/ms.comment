package az.ingress.mapper;

import az.ingress.dao.entity.CommentEntity;
import az.ingress.model.common.PageableResponse;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

import static az.ingress.model.enums.CommentStatus.ACTIVE;


public enum CommentMapper {
    COMMENT_MAPPER;

    public CommentEntity buildCommentEntity(CreateOrUpdateCommentRequest request) {
        return CommentEntity.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .status(ACTIVE)
                .content(request.getContent())
                .build();
    }

    public PageableResponse<CommentResponse> buildPageableResponse(Page<CommentEntity> page) {

        List<CommentResponse> commentResponses = page.stream()
                .map(COMMENT_MAPPER::buildCommentResponse)
                .toList();
        return PageableResponse.<CommentResponse>builder()
                .content(commentResponses)
                .hasNextPage(page.hasNext())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public CommentResponse buildCommentResponse(CommentEntity entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .productId(entity.getProductId())
                .status(entity.getStatus())
                .content(entity.getContent())
                .build();
    }

    public void updateComment(CommentEntity commentEntity, CreateOrUpdateCommentRequest request) {
        commentEntity.setContent(request.getContent());
    }
}