package nl.novi.dbexample.persistence;

import nl.novi.dbexample.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

}
