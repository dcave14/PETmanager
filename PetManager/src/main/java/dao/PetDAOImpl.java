package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Pet;
import java.util.List;

public class PetDAOImpl implements PetDAO {

    //create EntityManagerFactory
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetManagerPU");

    @Override
    public void addPet(Pet pet) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //persist pet
            em.persist(pet);
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public void updatePet(Pet pet) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //merge pet
            em.merge(pet);
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public void deletePet(int petId) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //find pet by id
            Pet pet = em.find(Pet.class, petId);
            //check if pet exists
            if (pet != null) {
                //remove pet
                em.remove(pet);
            }
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public Pet getPetById(int petId) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //find pet by id
            return em.find(Pet.class, petId);
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //execute query to get all pets
            return em.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    public void close() {
        //close EntityManagerFactory
        emf.close();
    }
}