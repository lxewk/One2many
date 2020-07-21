package nl.novi.dbexample.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
 */
@Entity
public class ApplicationUser {

    /*
    {
        "name": "Nick",
        "email": "n@n.nl"
    }

     */

    /**
     * We laten op regel X hibernate het beste generation-type selecteren. Je kunt er hier meer over lezen:
     * https://thorben-janssen.com/jpa-generate-primary-keys/
     *
     * UITBREIDING (gebruik generator):
     * https://stackoverflow.com/questions/49241216/has-spring-boot-changed-the-way-auto-increment-of-ids-works-through-generatedva
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native"
    )
    private long userId;

    /**
     * Deze twee spreken voor zich
     */
    private String name;
    private String email;

    /**
     * Dog is de many van de one to many kant. Aan de many kant moet de mappedBy geconfigureerd worden. Deze geef je
     * de naam van de variabele mee van de relatie aan de one-kant. In Dog is dat deze regel:
     * private ApplicationUser owner;
     *
     * UITBREIDING
     * Wanneer we een ApplicationUser verwijderen, moeten de bijbehorende honden ook verwijderd worden.
     * Meer info hier: https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
     * Klein beetje extra uitleg hier: https://stackoverflow.com/questions/23925322/delete-child-from-parent-and-parent-from-child-automatically-with-jpa-annotation
     * */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner",
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> dogs;

    /*
    Hieronder vind je alle getter en setters. Ik ga ervanuit dat je weet wat deze zijn en doen.
     */
    public long getId() {
        return userId;
    }

    public void setId(long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

}
