package com.Hos.core.common.exception;

import com.Hos.core.common.util.Constants;
import org.apache.coyote.BadRequestException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ExceptionResolver resolver;
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("errorCode", "401");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected final ErrorMessage handleBadRequest(BadRequestException exception) {
        return resolver.resolveError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    public static String constructString(String... args) {
        StringJoiner buildString = new StringJoiner(Constants.SPACE);
        for (String arg : args) {
            buildString.add(arg);
        }
        return buildString.toString();
    }

    private String getErrorStackString(Exception error) {
        StringWriter writer = new StringWriter();
        error.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
