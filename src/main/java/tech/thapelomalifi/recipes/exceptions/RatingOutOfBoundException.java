package tech.thapelomalifi.recipes.exceptions;

public class RatingOutOfBoundException extends RuntimeException {
    public RatingOutOfBoundException(String message) {
        super(message);
    }
}
