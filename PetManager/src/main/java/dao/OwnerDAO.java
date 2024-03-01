package dao;

import java.util.List;
import model.Owner;

public interface OwnerDAO {
    //add new owner to database
    void addOwner(Owner owner);
    
    //update existing owner in database
    void updateOwner(Owner owner);
    
    //remove owner from database using ownerId
    void deleteOwner(int ownerId);
    
    //fetch owner from database using ownerId
    Owner getOwnerById(int ownerId);
    
    //fetch all owners from database
    List<Owner> getAllOwners();
}