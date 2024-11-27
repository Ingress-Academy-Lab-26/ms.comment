package az.ingress.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstants {

    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "Comment not found"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "http method is not correct"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "Product not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred");

    private final String code;
    private final String message;
}
