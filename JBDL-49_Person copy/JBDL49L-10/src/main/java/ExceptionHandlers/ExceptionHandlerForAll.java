package ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerForAll {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrorList =bindingResult.getFieldErrors();
        List<String> error = fieldErrorList.stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity handleBadRequestException(BadRequest e){
        String error = e.getMessage();
        ResponseEntity responseEntity = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSQLException(SQLException sqlException){
        String error = sqlException.getMessage();
        ResponseEntity responseEntity = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        return responseEntity;

    }

}
