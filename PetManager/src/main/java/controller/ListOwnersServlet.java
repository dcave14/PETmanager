package controller;

import dao.OwnerDAOImpl;
import model.Owner;
import service.OwnerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOwners")
public class ListOwnersServlet extends HttpServlet {
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
        List<Owner> owners = ownerService.getAllOwners();
        request.setAttribute("owners", owners);
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        request.getRequestDispatcher("ownersList.jsp").forward(request, response);
    }
}
