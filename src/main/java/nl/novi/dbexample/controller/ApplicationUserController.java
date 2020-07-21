package nl.novi.dbexample.controller;

import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.service.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @GetMapping(value = "/api/user/{id}")
    public ApplicationUser getUserById(@PathVariable Long id) {
        Optional<ApplicationUser> user = applicationUserRepository.findById(id);
        return user.orElse(null);
    }

    @PostMapping(value = "/api/user/")
    public ApplicationUser addUser(@RequestBody ApplicationUser newUser) {
        return applicationUserRepository.save(newUser);
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


