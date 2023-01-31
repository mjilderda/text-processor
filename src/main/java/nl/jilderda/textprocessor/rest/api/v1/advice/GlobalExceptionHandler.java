package nl.jilderda.textprocessor.rest.api.v1.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn(exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.warn("unhandled exception in", exception);
        return ResponseEntity.internalServerError().body("something went wrong.");
    }
}
