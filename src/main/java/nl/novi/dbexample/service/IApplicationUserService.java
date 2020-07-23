package nl.novi.dbexample.service;

import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;

public interface IApplicationUserService {
    ApplicationUser getUserById(Long id);
    ApplicationUser addUser(ApplicationUser newUser);
    ApplicationUser updateUserById(Long id, ApplicationUser updatedUser);
    String deleteUser(Long id);
    ApplicationUser addDogToUser(Long id, Dog newDog);
    ApplicationUser addTestUserWithDogs();
}
