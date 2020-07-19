package nl.novi.dbexample.controller;

import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.service.ApplicationUserRepository;
import nl.novi.dbexample.service.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DogController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    /**
         {
         "name": "Doggo",
         "species": "Terrier",
         "furColour": "purple",
         "owner": null,
         "female": true
         }
     *
     */

    @GetMapping(value = "/api/dog")
    public List<Dog> getDogs() {
        List<Dog> dogList = dogRepository.findAll();
        return dogList;
    }

    @GetMapping(value = "/api/dog/{id}")
    public Dog getDog(@PathVariable Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()) {
            return dog.get();
        }
        return null;
    }

    @PostMapping(value = "/api/dog")
    public Dog saveDog(@RequestBody Dog newDog) {
        return dogRepository.save(newDog);
    }

    @DeleteMapping(value = "/api/dog/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogRepository.deleteById(id);
    }

    @PostMapping(value = "/api/user/{userId}")
    public Dog addDogToUser(@PathVariable Long userId, @RequestBody Dog dog) {
        Optional<ApplicationUser> user = applicationUserRepository.findById(userId);

        if(user.isPresent()) {
            dog.setOwner(user.get());
            return dogRepository.save(dog);
        }

        return null;
    }
}
