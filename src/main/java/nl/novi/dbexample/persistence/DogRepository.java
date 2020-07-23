package nl.novi.dbexample.persistence;

import nl.novi.dbexample.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
