package nl.novi.dbexample.service;

import nl.novi.dbexample.exception.UserNotFoundException;
import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.persistence.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUser getUserById(Long id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public ApplicationUser addUser(ApplicationUser newUser) {
        String userName = newUser.getName();

        if(!userName.contains("fuck")) {
            return applicationUserRepository.save(newUser);
        }
        throw new UserNotFoundException(Long.valueOf(1));
    }

    public ApplicationUser updateUserById(Long id, ApplicationUser updatedUser) {
        return applicationUserRepository.findById(id).map(
                user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    return applicationUserRepository.save(user);
                })
                // Kan de user niet vinden in database
                .orElseGet(() -> {
                    updatedUser.setId(id);
                    return applicationUserRepository.save(updatedUser);
                });
    }

}
