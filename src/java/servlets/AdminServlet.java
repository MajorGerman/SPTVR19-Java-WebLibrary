package servlets;

import entity.Person;
import entity.PersonFacade;
import entity.ProductFacade;
import entity.RoleFacade;
import entity.User;
import entity.UserFacade;
import entity.UserRolesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Georg
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/listPersons"




})

public class AdminServlet extends HttpServlet {
    @EJB 
    private ProductFacade productFacade;
    @EJB 
    private PersonFacade personFacade;
    @EJB 
    private UserFacade userFacade;
    @EJB 
    private RoleFacade roleFacade;
    @EJB 
    private UserRolesFacade userRolesFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            request.setAttribute("info","У вас нет прав! Войдите в систему!");
            request.setAttribute("borderwidth",6.5);
            request.getRequestDispatcher("/WEB-INF/loginForm.jsp").forward(request, response);
            return;          
        }
        User user = (User)session.getAttribute("user");
        if (user == null) {
            request.setAttribute("info","У вас нет прав! Войдите в систему!");
            request.setAttribute("borderwidth",6.5);
            request.getRequestDispatcher("/WEB-INF/loginForm.jsp").forward(request, response);
            return;                     
        }
        boolean isRole = userRolesFacade.isRole("admin", user);
        if (!isRole) {
            request.setAttribute("info","У вас нет прав!");
            request.setAttribute("borderwidth",6.5);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;               
        }
        String path = request.getServletPath();
        
        switch (path) {
            case "/listPersons":
                List<Person> listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/listPersons.jsp").forward(request, response);
                break;
            case "":
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}