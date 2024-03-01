package controller;

import dao.PetDAOImpl;
import model.Pet;
import service.PetService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listPets")
public class ListPetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PetService petService;

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize petService with PetDAOImpl
        this.petService = new PetService(new PetDAOImpl());
        //print initialization message
        System.out.println("ListPetsServlet initialized with PetService.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //get all pets using petService
        List<Pet> pets = petService.getAllPets();
        //print fetched pets
        System.out.println("Fetched pets from database: " + pets);

        //check if pets list is empty
        if (pets.isEmpty()) {
            //print message if no pets found
            System.out.println("No pets found in database.");
        } else {
            //print number of pets found
            System.out.println("Number of pets found: " + pets.size());
        }

        //set pets as request attribute
        request.setAttribute("pets", pets);
        
        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //forward request and response to petsList.jsp
        request.getRequestDispatcher("/petsList.jsp").forward(request, response);
    }
}