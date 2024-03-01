package service;

import dao.PetDAO;
import model.Pet;
import java.util.List;

public class PetService {
    private final PetDAO petDAO;

    public PetService(PetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public void addPet(Pet pet) {
        petDAO.addPet(pet);
    }
    
    public void deletePet(int petId) {
        petDAO.deletePet(petId);
    }
    
    public Pet updatePet(Pet updatedPetDetails) {
        petDAO.updatePet(updatedPetDetails);
        return petDAO.getPetById(updatedPetDetails.getPetId());
    }
    
    public Pet getPetById(int petId) {
        return petDAO.getPetById(petId);
    }

    public List<Pet> getAllPets() {
        return petDAO.getAllPets();
    }

}
