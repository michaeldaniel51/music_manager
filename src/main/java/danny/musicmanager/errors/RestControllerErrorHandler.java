package danny.musicmanager.errors;

import danny.musicmanager.exceptions.SongNotOutDatedException;
import danny.musicmanager.exceptions.UserNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestControllerErrorHandler {


    private Log log = LogFactory.getLog(this.getClass());

    /**
     * handles invalid method arguments exception.
     * @param exception exception to be handled
     * @param servletRequest servlet Request
     * @return error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationError(MethodArgumentNotValidException exception, HttpServletRequest servletRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTitle("Error");
        errorResponse.setDetail("Required field(s) omitted");

        // field errors
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError: fieldErrors) {
            List<ValidationError> validationErrorList = errorResponse.getErrors()
                    .computeIfAbsent(fieldError.getField(), k -> new ArrayList<>());
            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * handles an unknown type of exception.
     *
     * @param exception exception
     * @return error response
     */


    /**
     * handles data integrity exception.
     * @return error response
     */
//
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e){

        ErrorResponse errorResponse = new ErrorResponse();
        String errorMessage = e.getCause().getMessage();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail(errorMessage. substring(17,errorMessage.length() - 29) + " already registered on this platform");
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(null);

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }


    /**
     *Handles MusicNotOutDatedException
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(SongNotOutDatedException.class)
    public ResponseEntity<?> exceptionHandler(SongNotOutDatedException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(null);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Handles UsernameNotFoundException.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usnnfExceptionHandler(UsernameNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(null);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles ClassCastException.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<?> exceptionHandler(ClassCastException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail("Illegal Request");
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(null);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles IllegalStateExceptions.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> exceptionHandler(IllegalStateException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrors(null);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles TokeNotValidException..
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setTitle("Error");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        this.log.warn("TODO: fix Exception", exception);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }



}
