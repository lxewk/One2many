package nl.novi.dbexample.controller;

import nl.novi.dbexample.exception.UserNotFoundException;
import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping(value = "/api/user/{id}")
    public ApplicationUser getUserById(@PathVariable Long id) {
        return applicationUserService.getUserById(id);
    }

    @PostMapping(value = "/api/user/")
    public ApplicationUser addUser(@RequestBody ApplicationUser newUser) {
        return applicationUserService.addUser(newUser);
    }


    @DeleteMapping(value = "/api/user/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<ApplicationUser> user = applicationUserRepository.findById(id);

        if(user.isPresent()) {
            applicationUserRepository.deleteById(id);
            return "User met id " + user.get().getId() + " is verwijderd";
        }
        throw new UserNotFoundException("Hallo, ik besta niet");
    }

    @PutMapping(value = "/api/user/{id}")
    public ApplicationUser updateUserById(@PathVariable long id, @RequestBody ApplicationUser updatedUser) {
        return applicationUserService.updateUserById(id, updatedUser);
    }

    @PutMapping("/api/user/{id}/dog") // http://localhost:8080/api/user/1/dog
    public ApplicationUser addDogToUser(@PathVariable long id,
                                        @RequestBody Dog newDog) {
        Optional<ApplicationUser> user =
                applicationUserRepository.findById(id);

        if(user.isPresent()) {
            ApplicationUser userFromDb = user.get();
            List<Dog> currentDogs = userFromDb.getDogs();

            if(newDog.getOwner() == null || newDog.getOwner().getId() != id) {
                newDog.setOwner(userFromDb);
            }

            currentDogs.add(newDog);
            userFromDb.setDogs(currentDogs);

            return applicationUserRepository.save(userFromDb);
        }

        return null;

    }


    @PostMapping("/api/user/fill")
    public ApplicationUser addTestUsers() {
        ApplicationUser user = new ApplicationUser();
        user.setName("Nick Stuivenberg");
        user.setEmail("n.stuivenberg@novi.nl");

        Dog barra = new Dog();
        barra.setName("Barra");
        barra.setSpecies("vuilnisbak");
        barra.setFemale(true);
        barra.setFurColour("black");

        Dog joop = new Dog();
        joop.setName("Joop");
        joop.setSpecies("Hyperactief");
        joop.setFemale(false);
        joop.setFurColour("mixed");

        user.setDogs(Arrays.asList(barra, joop));
        barra.setOwner(user);
        joop.setOwner(user);

        applicationUserRepository.save(user);
        return user;
    }


}


