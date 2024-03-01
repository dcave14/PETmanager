package controller;
import dao.OwnerDAOImpl;
import dao.PetDAOImpl;
import model.Owner;
import model.Pet;
import service.OwnerService;
import service.PetService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addPetServlet")
public class AddPetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PetService petService;

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize petService with PetDAOImpl
        this.petService = new PetService(new PetDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //get all owners and set as request attribute
        List<Owner> owners = new OwnerService(new OwnerDAOImpl()).getAllOwners();
        request.setAttribute("owners", owners);
        
        //forward request and response to /addPet.jsp
        request.getRequestDispatcher("/addPet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //get parameters from request
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        int age;
        int ownerId;
        
        try {
            //parse age and ownerId from request parameters
            age = Integer.parseInt(request.getParameter("age"));
            ownerId = Integer.parseInt(request.getParameter("ownerId"));
        } catch (NumberFormatException e) {
            //set error attribute and forward back to /addPet.jsp on number format exception
            request.setAttribute("error", "Invalid age or owner ID.");
            request.getRequestDispatcher("/addPet.jsp").forward(request, response);
            return;
        }

        //get owner by id
        OwnerService ownerService = new OwnerService(new OwnerDAOImpl());
        Owner owner = ownerService.getOwnerById(ownerId);

        //redirect to addOwner.jsp if owner is null
        if (owner == null) {
            response.sendRedirect("addOwner.jsp");
            return;
        }

        //create new pet and set properties
        Pet newPet = new Pet();
        newPet.setName(name);
        newPet.setSpecies(species);
        newPet.setBreed(breed);
        newPet.setAge(age);
        newPet.setOwner(owner);

        //add new pet using petService
        petService.addPet(newPet);
        
        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //redirect to listPets
        response.sendRedirect("listPets");
    }

    @Override
    public void destroy() {
        super.destroy();
        //nullify petService on servlet destroy
        petService = null;
    }
}