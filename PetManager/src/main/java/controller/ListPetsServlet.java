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
        this.petService = new PetService(new PetDAOImpl());
        System.out.println("ListPetsServlet initialized with PetService.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Pet> pets = petService.getAllPets();
        System.out.println("Fetched pets from database: " + pets);

        if (pets.isEmpty()) {
            System.out.println("No pets found in database.");
        } else {
            System.out.println("Number of pets found: " + pets.size());
        }

        request.setAttribute("pets", pets);
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        request.getRequestDispatcher("/petsList.jsp").forward(request, response);
    }
}
