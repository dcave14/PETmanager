package service;

import dao.OwnerDAO;
import model.Owner;
import java.util.List;

public class OwnerService {
    private final OwnerDAO ownerDAO;

    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public void addOwner(Owner owner) {
        ownerDAO.addOwner(owner);
    }

    public Owner updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);
        return ownerDAO.getOwnerById(owner.getOwnerId());
    }

    public void deleteOwner(int ownerId) {
        ownerDAO.deleteOwner(ownerId);
    }

    public Owner getOwnerById(int ownerId) {
        return ownerDAO.getOwnerById(ownerId);
    }

    public List<Owner> getAllOwners() {
        return ownerDAO.getAllOwners();
    }

}
