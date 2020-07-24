package nl.novi.dbexample.controller;

import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.service.IDogService;
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
    private IDogService dogService;

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
    public List<Dog> getDogs() { return dogService.getDogs();
    }

    @GetMapping(value = "/api/dog/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
        /* Oude code voordat we een exceptie hadden.
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()) {
            return dog.get();
        }
        return null;
        */
    }

    @PostMapping(value = "/api/dog")
    public Dog saveDog(@RequestBody Dog newDog) {
        return dogService.saveDog(newDog);
    }

    @DeleteMapping(value = "/api/dog/{id}")
    public String deleteDog(@PathVariable Long id) {
        return dogService.deleteDog(id);
    }

    @PostMapping(value = "/api/dog/{userid}")
    public Dog addDogToUserById(@PathVariable long userid,
                                @RequestBody Dog newDog) {
        return dogService.saveDog(newDog);
    }


}
