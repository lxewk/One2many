package nl.novi.dbexample.exception;

public class DogNotFoundException extends RuntimeException {

    public DogNotFoundException(Long id) {
        super("Could not find dog with id: " + id);
    }
}
