package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Owner;

import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetManagerPU");

    @Override
    public void addOwner(Owner owner) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateOwner(Owner owner) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(owner);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteOwner(int ownerId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Owner owner = em.find(Owner.class, ownerId);
            if (owner != null) {
                em.remove(owner);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Owner getOwnerById(int ownerId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Owner.class, ownerId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
