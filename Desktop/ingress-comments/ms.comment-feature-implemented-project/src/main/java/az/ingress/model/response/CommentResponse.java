package az.ingress.model.response;

import az.ingress.model.enums.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long id;

    private Long userId;

    private Long productId;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content;


    private CommentStatus status;
}
