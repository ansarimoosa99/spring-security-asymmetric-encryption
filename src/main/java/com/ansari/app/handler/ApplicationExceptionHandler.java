package com.ansari.app.handler;

import com.ansari.app.exception.BusinessException;
import com.ansari.app.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.ansari.app.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(final BusinessException ex) {
        final ErrorResponse body = ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getMessage())
                .build();

        log.info("Business exception: {}", ex.getMessage());
        log.debug(ex.getMessage(), ex);


        return ResponseEntity.status(ex.getErrorCode()
                                       .getHttpStatus() != null ? ex.getErrorCode()
                                                                    .getHttpStatus() : BAD_REQUEST)
                             .body(body);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handleException(final DisabledException ex) {
        final ErrorResponse body = ErrorResponse.builder()
                                                .code(ERR_USER_DISABLED.getCode())
                                                .message(ERR_USER_DISABLED.getDefaultMessage())
                                                .build();

        return ResponseEntity.status(ERR_USER_DISABLED.getHttpStatus())
                             .body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException(final BadCredentialsException ex) {
        log.debug(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.builder()
                                                .code(BAD_CREDENTIALS.getCode())
                                                .message(BAD_CREDENTIALS.getDefaultMessage())
                                                .build();


        return ResponseEntity.status(BAD_CREDENTIALS.getHttpStatus())
                             .body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final UsernameNotFoundException ex) {
        log.debug(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.builder()
                                                .code(USERNAME_NOT_FOUND.getCode())
                                                .message(USERNAME_NOT_FOUND.getDefaultMessage())
                                                .build();

        return new ResponseEntity<>(response, NOT_FOUND);
        // this is just another way to write the above method code
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final EntityNotFoundException ex) {
        log.debug(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.builder()
                                                .code("TBD")   //To be defined
                                                .message(ex.getMessage())
                                                .build();

        return new ResponseEntity<>(response, NOT_FOUND);
    }

    //very important - needed for validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(final MethodArgumentNotValidException ex) {
        log.debug(ex.getMessage(), ex);
        final List<ErrorResponse.ValidationError> errors = new ArrayList<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error ->{
                    final String fieldName = ((FieldError) error).getField();
                    final String errorCode = error.getDefaultMessage();
                    errors.add(ErrorResponse.ValidationError.builder()
                                                            .field(fieldName)
                                                            .code(errorCode)
                                                            .message(error.getDefaultMessage())
                                                            .build());
                });


        final ErrorResponse errorResponse = ErrorResponse.builder()
                                                .code(BAD_REQUEST.name())
                                                .message(BAD_REQUEST.getReasonPhrase())
                                                .validationErrors(errors)
                                                .build();

        return ResponseEntity.status(BAD_REQUEST)
                             .body(errorResponse);
    }



    //Global - any exception in our application will be handled by this method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.builder()
                                                .code(INTERNAL_EXCEPTION.getCode())
                                                .message(INTERNAL_EXCEPTION.getDefaultMessage())
                                                .build();

        return ResponseEntity.status(ErrorCode.INTERNAL_EXCEPTION.getHttpStatus())
                             .body(response);
    }

}
