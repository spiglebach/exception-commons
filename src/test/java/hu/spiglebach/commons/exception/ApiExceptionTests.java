package hu.spiglebach.commons.exception;

import hu.spiglebach.commons.exception.util.CustomTestErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiExceptionTests {

    @Test
    void testCustomErrorCodeExceptionClass() {
        assertThrows(ApiException.class, () -> {
            throw CustomTestErrorCode.TEST_ERROR_CODE.exception();
        });
    }

    @Test
    void testCommonErrorCodeExceptionClass() {
        assertThrows(ApiException.class, () -> {
            throw CommonErrorCode.UNEXPECTED_EXCEPTION.exception();
        });
    }

    @Test
    void testCustomErrorCodeException() {
        try {
            throw CustomTestErrorCode.TEST_ERROR_CODE.exception();
        } catch (ApiException e) {
            assertEquals("TEST_ERROR_CODE", e.errorCode);
            assertEquals("This is a test error", e.defaultMessage);
            assertEquals(HttpStatus.BAD_REQUEST, e.status);
            assertEquals(Map.of(), e.parameters);
        } catch (Exception e) {
            fail("Unexpected exception instead of ApiException");
        }
    }

    @Test
    void testCustomErrorCodeException_withParameters() {
        try {
            throw CustomTestErrorCode.TEST_ERROR_CODE.exception(Map.of(
                    "test", true,
                    "num", 14
            ));
        } catch (ApiException e) {
            assertEquals("TEST_ERROR_CODE", e.errorCode);
            assertEquals("This is a test error", e.defaultMessage);
            assertEquals(HttpStatus.BAD_REQUEST, e.status);
            assertEquals(Map.of("test", true, "num", 14), e.parameters);
        } catch (Exception e) {
            fail("Unexpected exception instead of ApiException");
        }
    }

    @Test
    void testCustomErrorCodeException_asRegularException() {
        try {
            throw CustomTestErrorCode.TEST_ERROR_CODE.exception();
        } catch (Exception e) {
            assertEquals("Error 400 - \"This is a test error\" (TEST_ERROR_CODE). Parameters: {}", e.getMessage());
        } catch (Throwable e) {
            fail("Unexpected throwable instead of Exception");
        }
    }

    @Test
    void testCustomErrorCodeException_asRegularException_withParameters() {
        try {
            throw CustomTestErrorCode.TEST_ERROR_CODE.exception(Map.of(
                    "param1", 500,
                    "testParam", "Hello world"
            ));
        } catch (Exception e) {
            assertEquals("Error 400 - \"This is a test error\" (TEST_ERROR_CODE). Parameters: {\"param1\":\"500\", \"testParam\":\"Hello world\"}", e.getMessage());
        } catch (Throwable e) {
            fail("Unexpected throwable instead of Exception");
        }
    }
}
