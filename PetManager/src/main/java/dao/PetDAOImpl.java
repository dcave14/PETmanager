package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Pet;

import java.util.List;

public class PetDAOImpl implements PetDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetManagerPU");

    @Override
    public void addPet(Pet pet) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pet);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void updatePet(Pet pet) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pet);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletePet(int petId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pet pet = em.find(Pet.class, petId);
            if (pet != null) {
                em.remove(pet);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Pet getPetById(int petId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pet.class, petId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pet p", Pet.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
