package controller;

import service.OwnerService;
import model.Owner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.OwnerDAOImpl;

@WebServlet("/editOwnerServlet")
public class EditOwnerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize ownerService with OwnerDAOImpl
        this.ownerService = new OwnerService(new OwnerDAOImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //parse ownerId from request parameters
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        
        //get owner by id using ownerService
        Owner owner = ownerService.getOwnerById(ownerId);
        
        //set owner as request attribute
        request.setAttribute("owner", owner);
        
        //forward request and response to /editOwner.jsp
        request.getRequestDispatcher("/editOwner.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //parse ownerId from request parameters
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        
        //get parameters from request
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        //create updatedOwner and set properties
        Owner updatedOwner = new Owner();
        updatedOwner.setOwnerId(ownerId);
        updatedOwner.setName(name);
        updatedOwner.setAddress(address);
        updatedOwner.setPhoneNumber(phoneNumber);

        //update owner using ownerService
        ownerService.updateOwner(updatedOwner);
        
        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //redirect to listOwners
        response.sendRedirect("listOwners");
    }
}