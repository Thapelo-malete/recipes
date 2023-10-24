package tech.thapelomalifi.recipes.exceptions;

public class CategoryAlreadyAddedException extends RuntimeException{
    public CategoryAlreadyAddedException(String message) {
        super(message);
    }
}
