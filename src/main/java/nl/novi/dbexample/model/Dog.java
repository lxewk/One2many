package nl.novi.dbexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
     * Voor uitleg van de bi-directionele relatie met mappedBy, kun je terug bij {@link ApplicationUser}
     */
    @ManyToOne
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
