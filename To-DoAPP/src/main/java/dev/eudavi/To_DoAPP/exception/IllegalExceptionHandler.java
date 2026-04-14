package dev.eudavi.To_DoAPP.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class IllegalExceptionHandler extends RuntimeException {
    public IllegalExceptionHandler(String message) {
        super(message);
    }

    public IllegalExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
