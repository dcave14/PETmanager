package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private int petId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "species")
    private String species;
    
    @Column(name = "breed")
    private String breed;
    
    @Column(name = "age")
    private int age;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    public Pet() {
    }

    public Pet(String name, String species, String breed, int age, Owner owner) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }

    // Getters and setters
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "Pet{" +
               "petId=" + petId +
               ", name='" + name + '\'' +
               ", species='" + species + '\'' +
               ", breed='" + breed + '\'' +
               ", age=" + age +
               ", owner=" + (owner != null ? owner.getName() : "No Owner") +
               '}';
    }
}
