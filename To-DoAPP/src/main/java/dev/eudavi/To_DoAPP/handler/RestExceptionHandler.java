package dev.eudavi.To_DoAPP.handler;

import dev.eudavi.To_DoAPP.exception.EmailAlreadyExistsException;
import dev.eudavi.To_DoAPP.exception.InvalidPasswordException;
import dev.eudavi.To_DoAPP.exception.UserNotFoundException;
import dev.eudavi.To_DoAPP.exception.WeakPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<String> emailAlreadyExistsHandle(EmailAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandle(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<String> invalidPasswordHandle(InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(WeakPasswordException.class)
    private ResponseEntity<String> weakPasswordHandle(WeakPasswordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> illegalArgumentHandle(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
