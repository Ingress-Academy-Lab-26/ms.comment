package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
    COMMENT_NOT_FOUND("Comment not found"),
    USER_NOT_FOUND("User not found"),
    PRODUCT_NOT_FOUND("Product not found"),
    UNEXPECTED_EXCEPTION("Unexpected exception occurred"),
    BIND_EXCEPTION("BindException"),
    JSON_FORMAT_EXCEPTION("The data is not in JSON format"),
    CUSTOM_FEIGN_EXCEPTION("Custom Feign Exception"),
    CLIENT_EXCEPTION("CLIENT_EXCEPTION");

    private final String message;
}
