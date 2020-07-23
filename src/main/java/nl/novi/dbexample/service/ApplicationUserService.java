package nl.novi.dbexample.service;

import nl.novi.dbexample.exception.UserNotFoundException;
import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.persistence.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService implements IApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public ApplicationUser getUserById(Long id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public ApplicationUser addUser(ApplicationUser newUser) {
        String userName = newUser.getName();

        if(!userName.contains("fuck")) {
            return applicationUserRepository.save(newUser);
        }
        throw new UserNotFoundException(Long.valueOf(1));
    }

    @Override
    public ApplicationUser updateUserById(Long id, ApplicationUser updatedUser) {
        Optional<ApplicationUser> userFromDb = applicationUserRepository.findById(id);

        if(userFromDb.isPresent()) {
            if (checkIsValidName(updatedUser.getName())) {
                ApplicationUser applicationUser = new ApplicationUser();
                applicationUser.setName(updatedUser.getName());
                applicationUser.setEmail(updatedUser.getEmail());
                return applicationUserRepository.save(applicationUser);
            }
        }
            throw new UserNotFoundException(id);
    }

    @Override
    public String deleteUser(Long id) {
        Optional<ApplicationUser> user = applicationUserRepository.findById(id);
        if(user.isPresent()) {
            applicationUserRepository.deleteById(id);
            return "User met id " + user.get().getId() + " is verwijderd";
        }
        throw new UserNotFoundException("Hallo, ik besta niet");
    }

    @Override
    public ApplicationUser addDogToUser(Long id, Dog newDog) {
        Optional<ApplicationUser> user =
                applicationUserRepository.findById(id);

        if (user.isPresent()) {
            ApplicationUser userFromDb = user.get();
            List<Dog> currentDogs = userFromDb.getDogs();

            if (newDog.getOwner() == null || newDog.getOwner().getId() != id) {
                newDog.setOwner(userFromDb);
            }

            currentDogs.add(newDog);
            userFromDb.setDogs(currentDogs);

            return applicationUserRepository.save(userFromDb);
        }
        throw new UserNotFoundException(id);
    }

    public ApplicationUser addTestUserWithDogs() {
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

        return applicationUserRepository.save(user);
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
