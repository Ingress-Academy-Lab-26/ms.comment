package az.ingress.mapper;

import az.ingress.dao.entity.CommentEntity;
import az.ingress.model.common.PageableResponse;
import az.ingress.model.request.CreateOrUpdateCommentRequest;
import az.ingress.model.response.CommentResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static az.ingress.model.enums.CommentStatus.ACTIVE;

public enum CommentMapper {
    COMMENT_MAPPER;

    public CommentEntity buildCommentEntity(CreateOrUpdateCommentRequest request) {
        return CommentEntity.builder()
                .userId(request.getUserId())
                .productId(request.getProductId())
                .status(ACTIVE)
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    public PageableResponse<CommentResponse> buildPageableResponse(Page<CommentEntity> commentEntityList) {
        return PageableResponse.<CommentResponse>builder()
                .content(commentEntityList.stream().
                        map(COMMENT_MAPPER::buildCommentResponse)
                        .toList())
                .totalElements(commentEntityList.getTotalElements())
                .totalPages(commentEntityList.getTotalPages())
                .hasNextPage(commentEntityList.hasNext())
                .build();
    }

    public CommentResponse buildCommentResponse(CommentEntity entity) {
        return CommentResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .productId(entity.getProductId())
                .status(entity.getStatus())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}


