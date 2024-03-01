package service;

import dao.OwnerDAO;
import model.Owner;
import java.util.List;

public class OwnerService {
    private final OwnerDAO ownerDAO; //DAO for owner operations

    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO; //initialize ownerDAO
    }

    public void addOwner(Owner owner) {
        ownerDAO.addOwner(owner); //add owner
    }

    public Owner updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner); //update owner
        return ownerDAO.getOwnerById(owner.getOwnerId()); //return updated owner
    }

    public void deleteOwner(int ownerId) {
        ownerDAO.deleteOwner(ownerId); //delete owner
    }

    public Owner getOwnerById(int ownerId) {
        return ownerDAO.getOwnerById(ownerId); //get owner by id
    }

    public List<Owner> getAllOwners() {
        return ownerDAO.getAllOwners(); //get all owners
    }
}