package nl.novi.dbexample.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User met id: " + id + " bestaat niet.");
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
