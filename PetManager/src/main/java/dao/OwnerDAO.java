package dao;


import java.util.List;

import model.Owner;

public interface OwnerDAO {
    void addOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(int ownerId);
    Owner getOwnerById(int ownerId);
    List<Owner> getAllOwners();
}