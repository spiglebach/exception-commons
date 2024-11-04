package hu.spiglebach.commons.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public enum CommonErrorCode implements ApiErrorCode {
    UNEXPECTED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected exception"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found.")
    ;
    public final HttpStatus status;
    public final String message;

    CommonErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public ApiException exception() {
        return new ApiException(name(), status, message, Map.of());
    }

    @Override
    public ApiException exception(Map<String, Object> parameters) {
        return new ApiException(name(), status, message, parameters);
    }

}
