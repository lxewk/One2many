package nl.novi.dbexample.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private long dogId;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("dogs")
    private ApplicationUser owner;

    public long getId() {
        return dogId;
    }

    public void setId(long id) {
        this.dogId = id;
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
