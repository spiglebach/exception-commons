package hu.spiglebach.commons.exception.util;

import hu.spiglebach.commons.exception.ApiErrorCode;
import hu.spiglebach.commons.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@RequiredArgsConstructor
public enum CustomTestErrorCode implements ApiErrorCode {
    TEST_ERROR_CODE(HttpStatus.BAD_REQUEST, "This is a test error")
    ;

    public final HttpStatus status;
    public final String message;

    @Override
    public ApiException exception() {
        return new ApiException(name(), status, message);
    }

    @Override
    public ApiException exception(Map<String, Object> parameters) {
        return new ApiException(name(), status, message, parameters);
    }
}
