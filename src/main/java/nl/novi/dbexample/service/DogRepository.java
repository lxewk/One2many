package nl.novi.dbexample.service;

import nl.novi.dbexample.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
