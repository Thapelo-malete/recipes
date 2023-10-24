package tech.thapelomalifi.recipes.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.thapelomalifi.recipes.exceptions.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = CategoryAlreadyAddedException.class)
    public ResponseEntity<ResponseMessage> categoryAlreadyAddedExceptionHandler(CategoryAlreadyAddedException exception) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setStatus(HttpStatus.FORBIDDEN.value());
        responseMessage.setTime(LocalDateTime.now());

        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ResponseMessage> categoryNotFoundExceptionHandler(CategoryNotFoundException exception) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setStatus(HttpStatus.NOT_FOUND.value());
        responseMessage.setTime(LocalDateTime.now());

        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailTakenException.class)
    public ResponseEntity<ResponseMessage> emailTakenExceptionHandler(EmailTakenException exception) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setStatus(HttpStatus.FORBIDDEN.value());
        responseMessage.setTime(LocalDateTime.now());

        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<ResponseMessage> recipeNotFoundExceptionHandler(RecipeNotFoundException exception) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setStatus(HttpStatus.FORBIDDEN.value());
        responseMessage.setTime(LocalDateTime.now());

        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = RatingOutOfBoundException.class)
    public ResponseEntity<ResponseMessage> ratingOutOfBoundExceptionHandler(RatingOutOfBoundException exception) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setStatus(HttpStatus.FORBIDDEN.value());
        responseMessage.setTime(LocalDateTime.now());

        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }
}
