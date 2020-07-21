package nl.novi.dbexample.exception;

public class DogNotFoundException extends RuntimeException {

    /**
     * Creates the DogNotFoundException
     * @param id of the dog that was not found in the database
     */
    public DogNotFoundException(Long id) {
        super("Could not find dog with id: " + id);
    }
}
