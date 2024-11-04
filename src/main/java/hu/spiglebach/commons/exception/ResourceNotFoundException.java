package hu.spiglebach.commons.exception;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(@Nonnull Class<?> resourceClass, @Nonnull String fieldName, @Nonnull Object fieldValue) {
        super(
                CommonErrorCode.RESOURCE_NOT_FOUND.name(),
                HttpStatus.NOT_FOUND,
                String.format("%s not found by %s: %s", resourceClass.getSimpleName(), fieldName, fieldValue),
                Map.of(
                        "class", resourceClass.getSimpleName(),
                        "fieldName", fieldName,
                        "fieldValue", String.valueOf(fieldValue)
                )
        );
    }

}
