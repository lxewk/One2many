package nl.novi.dbexample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dog {

    /**
     * Voor uitleg id, zie {@link ApplicationUser}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Onderstaande properties spreken voor zich, maar kijk eens hoe de boolean in de database-tabel wordt aangemaakt.
     */
    private String name;
    private String species;
    private String furColour;
    private boolean isFemale;

    /**
     * Hier is het tweede deel van de relatie.
     * verschil Lazy en Eager loading:
     * https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
     *
     * We voegen hier de annotatie JoinColumn toe, om aan te geven dat de FOREIGN-KEY die verwijst naar de ID van
     * applicationowner opgeslagen moet worden in de kolom OWNER_ID in de database.
     * Daaronder zeggen we met private ApplicationUser owner er 1 ApplicationUser object aan een dog object toegevoegd
     * kan worden.
     */
    @ManyToOne(fetch=FetchType.LAZY)
    private ApplicationUser owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFurColour() {
        return furColour;
    }

    public void setFurColour(String furColour) {
        this.furColour = furColour;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }
}
