package nl.novi.dbexample.controller;

import nl.novi.dbexample.exception.DogNotFoundException;
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
        /* Oude code voordat we een exceptie hadden.
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()) {
            return dog.get();
        }
        return null;
        */
        return dogRepository.findById(id).orElseThrow(
                () -> new DogNotFoundException(id));
    }

    @PostMapping(value = "/api/dog")
    public Dog saveDog(@RequestBody Dog newDog) {
        return dogRepository.save(newDog);
    }


    @DeleteMapping(value = "/api/dog/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogRepository.deleteById(id);
    }

    @PostMapping(value = "/api/dog/{userid}")
    public Dog addDogToUserById(@PathVariable long userid,
                                @RequestBody Dog newDog) {

        Optional<ApplicationUser> user =
                applicationUserRepository.findById(userid);
        if(user.isPresent()) {
            newDog.setOwner(user.get());
            return dogRepository.save(newDog);
        }

        return null;
    }


}
