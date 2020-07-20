package nl.novi.dbexample.controller;

import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.service.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ApplicationUserController {

    /*
    {
    "name": "Nicky",
    "email": "n@n.nl"
    }
     */

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


}


