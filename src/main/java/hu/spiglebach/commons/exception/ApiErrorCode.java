package hu.spiglebach.commons.exception;

import java.util.Map;

public interface ApiErrorCode {
    ApiException exception();
    ApiException exception(Map<String, Object> parameters);
}
