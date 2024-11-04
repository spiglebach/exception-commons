package hu.spiglebach.commons.exception;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorMessage {
    public final OffsetDateTime timeStamp = OffsetDateTime.now();
    public final String errorCode;
    public final String message;
    public final Map<String, String> parameters;

    public ErrorMessage(ApiException apiException) {
        this.errorCode = apiException.errorCode;
        this.message = apiException.defaultMessage;
        this.parameters = apiException.parameters.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.valueOf(entry.getValue())
                ));;
    }

    public ErrorMessage(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.parameters = Map.of();
    }
}
