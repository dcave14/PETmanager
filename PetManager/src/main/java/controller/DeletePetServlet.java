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
        this.petService = new PetService(new PetDAOImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));
        
        petService.deletePet(petId);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        
        response.sendRedirect("listPets");
    }

}
