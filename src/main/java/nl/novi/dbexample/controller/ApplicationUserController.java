package nl.novi.dbexample.controller;

import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.service.IApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationUserController {

    @Autowired
    private IApplicationUserService applicationUserService;

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
        return applicationUserService.deleteUser(id);
    }

    @PutMapping(value = "/api/user/{id}")
    public ApplicationUser updateUserById(@PathVariable long id, @RequestBody ApplicationUser updatedUser) {
        return applicationUserService.updateUserById(id, updatedUser);
    }

    @PutMapping("/api/user/{id}/dog") // http://localhost:8080/api/user/1/dog
    public ApplicationUser addDogToUser(@PathVariable long id,
                                        @RequestBody Dog newDog) {
        return applicationUserService.addDogToUser(id, newDog);
    }


    @PostMapping("/api/user/fill")
    public ApplicationUser addTestUsers() {
        return applicationUserService.addTestUserWithDogs();
    }


}


