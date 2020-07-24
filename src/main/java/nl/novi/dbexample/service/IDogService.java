package nl.novi.dbexample.service;

import nl.novi.dbexample.model.Dog;

import java.util.List;

public interface IDogService {
    List<Dog> getDogs();
    Dog getDogById(Long id);
    Dog saveDog(Dog newDog);
    String deleteDog(Long id);
    Dog addDogToUserById(Long userid, Dog newDog);
}
