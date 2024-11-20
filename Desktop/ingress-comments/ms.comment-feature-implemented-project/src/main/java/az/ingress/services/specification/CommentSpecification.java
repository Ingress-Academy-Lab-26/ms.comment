package az.ingress.services.specification;

import az.ingress.dao.entity.CommentEntity;
import az.ingress.dao.entity.CommentEntity.Fields;
import az.ingress.model.criteria.CommentCriteria;
import az.ingress.util.PredicateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class CommentSpecification implements Specification<CommentEntity> {
    private CommentCriteria commentCriteria;

    public Predicate toPredicate(@NonNull Root<CommentEntity> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(commentCriteria.getContent(),
                        content -> cb.like(cb.lower(root.get(Fields.content)), applyLikePattern(content.toLowerCase())))
                .addNullSafety(commentCriteria.getProductId(),
                        productId -> cb.equal(root.get(Fields.productId), productId))
                .addNullSafety(commentCriteria.getUserId(),
                        userId -> cb.equal(root.get(Fields.userId), userId))
                .build();
        return cb.and(predicates);
    }
}