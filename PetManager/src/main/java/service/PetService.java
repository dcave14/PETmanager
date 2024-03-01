package service;

import dao.PetDAO;
import model.Pet;
import java.util.List;

public class PetService {
    private final PetDAO petDAO; //DAO for pet operations

    public PetService(PetDAO petDAO) {
        this.petDAO = petDAO; //initialize petDAO
    }

    public void addPet(Pet pet) {
        petDAO.addPet(pet); //add pet
    }
    
    public void deletePet(int petId) {
        petDAO.deletePet(petId); //delete pet
    }
    
    public Pet updatePet(Pet updatedPetDetails) {
        petDAO.updatePet(updatedPetDetails); //update pet
        return petDAO.getPetById(updatedPetDetails.getPetId()); //return updated pet
    }
    
    public Pet getPetById(int petId) {
        return petDAO.getPetById(petId); //get pet by id
    }

    public List<Pet> getAllPets() {
        return petDAO.getAllPets(); //get all pets
    }
}