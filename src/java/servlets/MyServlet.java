package servlets;

import entity.Book;
import entity.BookFacade;
import entity.User;
import entity.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = {"/addBook", "/createBook", "/addUser", "/createUser", "/bookList"})
public class MyServlet extends HttpServlet {
    
    @EJB 
    private BookFacade bookFacade;
    @EJB
    private UserFacade userFacade;
    
    List<Book> array = new ArrayList<>();
    
    String books = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String path = request.getServletPath();
        String name;
        
        switch (path) {
            case "/addBook":
                request.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(request, response);
                break;
            case "/createBook":
                name = request.getParameter("name");
                String author = request.getParameter("author");
                String year = request.getParameter("year");               
                if (name.equals("") || name == null || author.equals("") || author == null || year.equals("") || year == null) {
                    request.setAttribute("name", name);
                    request.setAttribute("author", author);
                    request.setAttribute("year", year);
                    request.setAttribute("error", "Заполните все поля!");
                    request.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(request, response);
                    break;
                }
                Book book = new Book(name, author, year);
                bookFacade.create(book);
                request.setAttribute("info", "Книга была добавлена! ('" + name + "', " + author + " " + year + "г. )");
                request.getRequestDispatcher("/index.jsp").forward(request, response);                
                break;
            case "/addUser":
                request.getRequestDispatcher("/WEB-INF/addUser.jsp").forward(request, response);
                break;                
            case "/createUser":
                name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String nick = request.getParameter("nick");       
                String phone = request.getParameter("phone");      
                if (name.equals("") || name == null || surname.equals("") || surname == null || nick.equals("") || nick == null || phone.equals("") || phone == null) {
                    request.setAttribute("name", name);
                    request.setAttribute("surname", surname);
                    request.setAttribute("nick", nick);
                    request.setAttribute("phone", phone);
                    request.setAttribute("error", "Заполните все поля!");
                    request.getRequestDispatcher("/WEB-INF/addUser.jsp").forward(request, response);
                    break;
                }
                User user = new User(name, surname, nick, phone);
                userFacade.create(user);
                request.setAttribute("info", "Пользователь был добавлен! (" + name + " '" + nick + "' " + surname + ", " + phone + ")");
                request.getRequestDispatcher("/index.jsp").forward(request, response);                
                break;                          
            case "/bookList":
                array = bookFacade.findAll();
                books = "";
                for (int i = 0; i < array.size(); i++) {
                    books += "<div> '" + array.get(i).getName() + "' (" + array.get(i).getAuthor() + ", " + array.get(i).getYear() + "г.)</div>";
                    System.out.println(books);
                }
                request.setAttribute("books", books);
                request.getRequestDispatcher("/WEB-INF/bookList.jsp").forward(request, response);  
                break;
    
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
