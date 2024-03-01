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
    private OwnerService ownerService; // Add OwnerService

    @Override
    public void init() throws ServletException {
        super.init();
        this.petService = new PetService(new PetDAOImpl());
        this.ownerService = new OwnerService(new OwnerDAOImpl()); // Initialize OwnerService
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));
        Pet pet = petService.getPetById(petId);
        request.setAttribute("pet", pet);

        List<Owner> owners = ownerService.getAllOwners();
        request.setAttribute("owners", owners);

        request.getRequestDispatcher("/editPet.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int petId = Integer.parseInt(request.getParameter("petId"));
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        int age = Integer.parseInt(request.getParameter("age"));
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));

        Pet updatedPet = new Pet(); 
        updatedPet.setPetId(petId);
        updatedPet.setName(name);
        updatedPet.setSpecies(species);
        updatedPet.setBreed(breed);
        updatedPet.setAge(age);

        Owner owner = ownerService.getOwnerById(ownerId);
        updatedPet.setOwner(owner);

        petService.updatePet(updatedPet);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.sendRedirect("listPets");
    }
}
