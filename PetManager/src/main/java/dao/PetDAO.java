package dao;

import java.util.List;

import model.Pet;

public interface PetDAO {
    void addPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(int petId);
    Pet getPetById(int petId);
    List<Pet> getAllPets();
}