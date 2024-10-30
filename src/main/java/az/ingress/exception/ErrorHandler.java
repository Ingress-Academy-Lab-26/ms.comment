package az.ingress.exception;

import az.ingress.model.enums.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.model.enums.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ErrorResponse(UNEXPECTED_EXCEPTION.getMessage(), UNEXPECTED_EXCEPTION.getCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException: ", ex);
        return new ErrorResponse(ExceptionConstants.METHOD_NOT_ALLOWED.getMessage(), ExceptionConstants.METHOD_NOT_ALLOWED.getCode());
    }
}
