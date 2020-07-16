package nl.novi.dbexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * https://en.wikibooks.org/wiki/Java_Persistence/OneToMany
 */
@Entity
public class ApplicationUser {

    /**
     * Hieronder generen we de ID kolom. Deze noemen we USER_ID (regel x), zodat we er makkelijk naar kunnen wijzen voor
     * het foreign-key gedeelte.
     * We laten op regel X hibernate het beste generation-type selecteren. Je kunt er hier meer over lezen:
     * https://thorben-janssen.com/jpa-generate-primary-keys/
     */
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Deze twee spreken voor zich
     */
    private String name;
    private String email;

    /**
     * Hier gaan we het eerste deel van de relatie programmeren. Voor de simpelheid heb ik hier een list gebruikt.
     * Normaliter zou ik een Set gebruiken.
     *
     * Er wordt hier een relatie gelegd met het Dog-object. Deze klasse kan namelijke meerdere Dog-objecten hebben.
     * Hier verwijs ik naar de USER_ID die we op regel 25 zo hebben genoemd en deze krijgt de naam OWNER_ID in de tabel
     * die voor Dog wordt aangemaakt.
     */
    @OneToMany
    @JoinColumn(name="OWNER_ID", referencedColumnName = "USER_ID")
    private List<Dog> dogs;


    /*
    Hieronder vind je alle getter en setters. Ik ga ervanuit dat je weet wat deze zijn en doen.
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
