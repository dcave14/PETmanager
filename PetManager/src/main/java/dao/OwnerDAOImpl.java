package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Owner;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

    //create EntityManagerFactory
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetManagerPU");

    @Override
    public void addOwner(Owner owner) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //persist owner
            em.persist(owner);
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public void updateOwner(Owner owner) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //merge owner
            em.merge(owner);
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public void deleteOwner(int ownerId) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //begin transaction
            em.getTransaction().begin();
            //find owner by id
            Owner owner = em.find(Owner.class, ownerId);
            //check if owner exists
            if (owner != null) {
                //remove owner
                em.remove(owner);
            }
            //commit transaction
            em.getTransaction().commit();
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public Owner getOwnerById(int ownerId) {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //find owner by id
            return em.find(Owner.class, ownerId);
        } finally {
            //close EntityManager
            em.close();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        //create EntityManager
        EntityManager em = emf.createEntityManager();
        try {
            //execute query to get all owners
            return em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
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