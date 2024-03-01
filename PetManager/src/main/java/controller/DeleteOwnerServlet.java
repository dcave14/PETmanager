package controller;

import service.OwnerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.OwnerDAOImpl;

@WebServlet("/deleteOwnerServlet")
public class DeleteOwnerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ownerService = new OwnerService(new OwnerDAOImpl());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        
        ownerService.deleteOwner(ownerId);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        
        response.sendRedirect("ownersList.jsp");
    }

}
