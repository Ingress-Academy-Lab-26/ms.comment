package az.ingress.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCriteria {
    private String content;
    private Long userId;
    private Long productId;
}