package controller;

import service.PetService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.PetDAOImpl;

@WebServlet("/deletePetServlet")
public class DeletePetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PetService petService;

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize petService with PetDAOImpl
        this.petService = new PetService(new PetDAOImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //parse petId from request parameters
        int petId = Integer.parseInt(request.getParameter("petId"));
        
        //delete pet using petService
        petService.deletePet(petId);

        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //redirect to listPets
        response.sendRedirect("listPets");
    }
}