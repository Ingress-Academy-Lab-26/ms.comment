package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateCommentRequest {

    private Long userId;
    private Long productId;
    private String content;
}

