package controller;
import dao.OwnerDAOImpl;
import model.Owner;
import service.OwnerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addOwnerServlet")
public class AddOwnerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        super.init();
        //initialize OwnerService with OwnerDAOImpl
        this.ownerService = new OwnerService(new OwnerDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //forward request and response to /addOwner.jsp
        request.getRequestDispatcher("/addOwner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //get parameters from request
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        
        //create new Owner and set properties
        Owner newOwner = new Owner();
        newOwner.setName(name);
        newOwner.setAddress(address);
        newOwner.setPhoneNumber(phoneNumber);

        //add new owner using ownerService
        ownerService.addOwner(newOwner);
        
        //set response headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        //redirect to listOwners
        response.sendRedirect("listOwners");
    }

    @Override
    public void destroy() {
        super.destroy();
        //nullify ownerService on servlet destroy
        ownerService = null;
    }
}