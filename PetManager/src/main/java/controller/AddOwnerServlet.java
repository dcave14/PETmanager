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
        this.ownerService = new OwnerService(new OwnerDAOImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/addOwner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        
        Owner newOwner = new Owner();
        newOwner.setName(name);
        newOwner.setAddress(address);
        newOwner.setPhoneNumber(phoneNumber);

        ownerService.addOwner(newOwner);
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);


        response.sendRedirect("listOwners");
    }

    @Override
    public void destroy() {
        super.destroy();
        ownerService = null;
    }
}
