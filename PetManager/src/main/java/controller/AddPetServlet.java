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
        this.petService = new PetService(new PetDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Owner> owners = new OwnerService(new OwnerDAOImpl()).getAllOwners();
        request.setAttribute("owners", owners);
        
        request.getRequestDispatcher("/addPet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        int age;
        int ownerId;
        
        try {
            age = Integer.parseInt(request.getParameter("age"));
            ownerId = Integer.parseInt(request.getParameter("ownerId"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid age or owner ID.");
            request.getRequestDispatcher("/addPet.jsp").forward(request, response);
            return;
        }

        OwnerService ownerService = new OwnerService(new OwnerDAOImpl());
        Owner owner = ownerService.getOwnerById(ownerId);

        if (owner == null) {
            response.sendRedirect("addOwner.jsp");
            return;
        }

        Pet newPet = new Pet();
        newPet.setName(name);
        newPet.setSpecies(species);
        newPet.setBreed(breed);
        newPet.setAge(age);
        newPet.setOwner(owner);

        petService.addPet(newPet);
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.sendRedirect("listPets");
    }



    @Override
    public void destroy() {
        super.destroy();
        // Clean up resources, if necessary
        petService = null;
    }
}
