package dao;

import java.util.List;
import model.Pet;

public interface PetDAO {
    //add new pet to database
    void addPet(Pet pet);
    
    //update existing pet in database
    void updatePet(Pet pet);
    
    //remove pet from database using petId
    void deletePet(int petId);
    
    //fetch pet from database using petId
    Pet getPetById(int petId);
    
    //fetch all pets from database
    List<Pet> getAllPets();
}