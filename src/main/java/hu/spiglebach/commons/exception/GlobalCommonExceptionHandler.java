package hu.spiglebach.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCommonExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorMessage> handleApiException(ApiException exception) {
        return ResponseEntity
                .status(exception.status)
                .body(new ErrorMessage(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleUnexpectedException(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(CommonErrorCode.UNEXPECTED_EXCEPTION.name(), exception.getMessage()));
    }
}
