package parsedargs;

public class InvalidArgumentsException extends Exception {
    public InvalidArgumentsException(String argument, String errorMessage) {
        super(String.format("%s: %s", argument, errorMessage));
    }
}