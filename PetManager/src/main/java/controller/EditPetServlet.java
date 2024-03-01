package controller;

import service.OwnerService;
import service.PetService;
import model.Owner;
import model.Pet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import dao.OwnerDAOImpl;
import dao.PetDAOImpl;

@WebServlet("/editPetServlet")
public class EditPetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PetService petService;
    private OwnerService ownerService; //add OwnerService

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize petService with PetDAOImpl
        this.petService = new PetService(new PetDAOImpl());
        //initialize ownerService with OwnerDAOImpl
        this.ownerService = new OwnerService(new OwnerDAOImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //parse petId from request parameters
        int petId = Integer.parseInt(request.getParameter("petId"));
        
        //get pet by id using petService
        Pet pet = petService.getPetById(petId);
        
        //set pet as request attribute
        request.setAttribute("pet", pet);

        //get all owners using ownerService
        List<Owner> owners = ownerService.getAllOwners();
        
        //set owners as request attribute
        request.setAttribute("owners", owners);

        //forward request and response to /editPet.jsp
        request.getRequestDispatcher("/editPet.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //parse petId, age, and ownerId from request parameters
        int petId = Integer.parseInt(request.getParameter("petId"));
        int age = Integer.parseInt(request.getParameter("age"));
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        
        //get parameters from request
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");

        //create updatedPet and set properties
        Pet updatedPet = new Pet(); 
        updatedPet.setPetId(petId);
        updatedPet.setName(name);
        updatedPet.setSpecies(species);
        updatedPet.setBreed(breed);
        updatedPet.setAge(age);

        //get owner by id using ownerService
        Owner owner = ownerService.getOwnerById(ownerId);
        
        //set owner of updatedPet
        updatedPet.setOwner(owner);

        //update pet using petService
        petService.updatePet(updatedPet);

        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //redirect to listPets
        response.sendRedirect("listPets");
    }
}