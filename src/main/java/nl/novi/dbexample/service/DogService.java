package nl.novi.dbexample.service;

import nl.novi.dbexample.exception.DogNotFoundException;
import nl.novi.dbexample.model.ApplicationUser;
import nl.novi.dbexample.model.Dog;
import nl.novi.dbexample.persistence.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService implements IDogService {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public List<Dog> getDogs() {
        List<Dog> dogList = dogRepository.findAll();
        return dogList;
    }

    @Override
    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(
                () -> new DogNotFoundException(id));
    }

    @Override
    public Dog saveDog(Dog newDog) {
        return dogRepository.save(newDog);
    }

    @Override
    public String deleteDog(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isPresent()) {
            dogRepository.deleteById(id);
            return "Dog with id " + dog.get().getId() + " is deleted.";
        }
        throw new DogNotFoundException(id);
    }

    @Override
    public Dog addDogToUserById(Long userid, Dog newDog) {
        SimpleJpaRepository applicationUserRepository = null;

        Optional<ApplicationUser> user =
                applicationUserRepository.findById(userid);
        if(user.isPresent()) {
            newDog.setOwner(user.get());
            return dogRepository.save(newDog);
        }
        return null;
    }

}
