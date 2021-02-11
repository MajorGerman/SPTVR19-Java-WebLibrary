package servlets;

import entity.Cover;
import entity.CoverFacade;
import entity.History;
import entity.HistoryFacade;
import entity.Person;
import entity.PersonFacade;
import entity.Product;
import entity.ProductFacade;
import entity.RoleFacade;
import entity.User;
import entity.UserFacade;
import entity.UserRolesFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserServlet", urlPatterns = {
    "/editPersonForm1",
    "/editPersonForm2",
    "/editPerson",
    "/buyProductForm",
    "/buyProduct"

})
public class UserServlet extends HttpServlet {
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
    @EJB 
    private HistoryFacade historyFacade;
    @EJB 
    private CoverFacade coverFacade;
     
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
        boolean isRole = userRolesFacade.isRole("customer", user);
        if (!isRole) {
            request.setAttribute("info","У вас нет прав!");
            request.setAttribute("borderwidth",6.5);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;               
        }
        String path = request.getServletPath();
        
        switch (path) {
            case "/addProductForm":
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                break;
            case "/addProduct":
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                String coverId = request.getParameter("coverId");
                if("".equals(name) || name == null 
                        || "".equals(price) || price == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("price",price);
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break; 
                } else if (Integer.parseInt(price) < 1) {
                    request.setAttribute("info","Цена не может быть меньше 1$!");
                    request.setAttribute("borderwidth",6.5);          
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break;                     
                }
                Cover cover = coverFacade.find(Long.parseLong(coverId));
                Product product = new Product(name, Integer.parseInt(price), cover);
                productFacade.create(product);
                request.setAttribute("info","Добавлен товар: " + product.toString() );
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                List<Product> listProductsOr = productFacade.findAll();
                List<Product> listProducts = new ArrayList<>();
                if (listProductsOr.size() > 0) {
                    for (int i = 0; i < listProductsOr.size(); i++) {
                        if (listProductsOr.get(i).isAccess() == true) {
                            listProducts.add(listProductsOr.get(i));
                        }
                    }
                }
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
            case "/editProductForm1":
                listProductsOr = productFacade.findAll();
                listProducts = new ArrayList<>();
                if (listProductsOr.size() > 0) {
                    for (int i = 0; i < listProductsOr.size(); i++) {
                        if (listProductsOr.get(i).isAccess() == true) {
                            listProducts.add(listProductsOr.get(i));
                        }
                    }
                }
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/editProductForm1.jsp").forward(request, response);
            case "/editProductForm2":                  
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/editProductForm2.jsp").forward(request, response);
                break;               
            case "/editProduct":
                productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                name = request.getParameter("name");
                price = request.getParameter("price");
                if("".equals(name) || name == null || "".equals(price) || price == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("price",price);
                    request.setAttribute("productId", product.getId()); 
                    request.getRequestDispatcher("/WEB-INF/editProductForm2.jsp").forward(request, response);
                    break; 
                } else if (Integer.parseInt(price) < 1) {
                    request.setAttribute("info","Цена не может быть меньше 1$!");
                    request.setAttribute("borderwidth",6.5);          
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break;    
                }   
                product.setName(name);
                product.setPrice(Integer.parseInt(price));
                productFacade.edit(product);
                request.setAttribute("productId", product.getId());
                request.setAttribute("info","Товар успешно отредактирован: " + product.toString() );
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;     
            case "/addPersonForm":
                request.getRequestDispatcher("/WEB-INF/addPersonForm.jsp").forward(request, response);
                break;
            case "/addPerson":
                name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String phone = request.getParameter("phone");
                String money = request.getParameter("money");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if("".equals(name) || name == null
                        || "".equals(surname) || surname == null
                        || "".equals(phone) || phone == null
                        || "".equals(money) || money == null
                        || "".equals(login) || login == null
                        || "".equals(password) || password == null ){
                    request.setAttribute("info","Заполните все поля формы!");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("surname",surname);
                    request.setAttribute("phone",phone);
                    request.setAttribute("money",money);     
                    request.setAttribute("login",login);     
                    request.setAttribute("password",password);     
                    request.getRequestDispatcher("/WEB-INF/addPersonForm.jsp").forward(request, response);
                    break; 
                } else if (Integer.parseInt(money) < 1) {
                    request.setAttribute("info","Не может быть денег меньше 0$!");
                    request.setAttribute("borderwidth",6.5);          
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break;                     
                }           
                Person pers = new Person(name, surname, phone, Integer.parseInt(money));
                personFacade.create(pers);
                user = new User(login, password, pers);
                userFacade.create(user);
                request.setAttribute("info","Добавлен пользователь: " + pers.toString());
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listPersons":
                List<Person> listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/listPersons.jsp").forward(request, response);
                break;
            case "/editPersonForm1":
                isRole = userRolesFacade.isRole("admin", user);
                if (!isRole) {
                    String personId = user.getPerson().getId().toString();
                    pers = personFacade.find(Long.parseLong(personId));
                    request.setAttribute("pers", pers);
                    request.getRequestDispatcher("/WEB-INF/editPersonForm2.jsp").forward(request, response);
                    break;
                }
                listPersons = personFacade.findAll();                
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/editPersonForm1.jsp").forward(request, response);
                break;
            case "/editPersonForm2":          
                isRole = userRolesFacade.isRole("admin", user);
                if (!isRole) {
                    String personId = user.getPerson().getId().toString();
                    pers = personFacade.find(Long.parseLong(personId));
                    request.setAttribute("pers", pers);
                    request.getRequestDispatcher("/WEB-INF/editPersonForm2.jsp").forward(request, response);  
                    break;
                }
                    String personId = request.getParameter("personId");
                    pers = personFacade.find(Long.parseLong(personId));
                    request.setAttribute("pers", pers);
                    request.getRequestDispatcher("/WEB-INF/editPersonForm2.jsp").forward(request, response);
                break;               
            case "/editPerson":
                personId = request.getParameter("persId");
                pers = personFacade.find(Long.parseLong(personId));
                request.setAttribute("person", pers);
                name = request.getParameter("name");
                surname = request.getParameter("surname");
                phone = request.getParameter("phone");
                money = request.getParameter("money");
                password = request.getParameter("password");
                if("".equals(name) || name == null
                        || "".equals(surname) || surname == null
                        || "".equals(phone) || phone == null
                        || "".equals(money) || money == null
                        || "".equals(password) || password == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("surname",surname);
                    request.setAttribute("phone",phone);
                    request.setAttribute("money",money); 
                    request.setAttribute("password", password); 
                    request.setAttribute("personId", pers.getId()); 
                    request.getRequestDispatcher("/WEB-INF/editPersonForm2.jsp").forward(request, response);
                    break; 
                } else if (Integer.parseInt(money) < 0) {
                    request.setAttribute("info","Не может быть денег меньше 0$!");
                    request.setAttribute("borderwidth",6.5);          
                    request.getRequestDispatcher("/WEB-INF/editPersonForm2.jsp").forward(request, response);
                    break;    
                }   
                pers.setName(name);
                pers.setSurname(surname);
                pers.setPhone(phone);
                pers.setMoney(Integer.parseInt(money));
                user.setPassword(password); 
                personFacade.edit(pers);
                userFacade.edit(user);
                user = userFacade.findByLogin(user.getLogin());
                session.setAttribute("user", user);
                session.setAttribute("upuser", session.getAttribute("user").toString()); 
                request.setAttribute("personId", pers.getId());
                request.setAttribute("info","Данные успешно отредактированы: " + pers.getName() + " " + pers.getSurname() + "(" + pers.getMoney() + "$)");
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;                
            case "/buyProductForm":
                listProductsOr = productFacade.findAll();
                if (listProductsOr.size() == 0) {
                    request.setAttribute("info","Товаров в данный момент нет!");
                    request.setAttribute("borderwidth",6.5);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);      
                    break;
                }
                listProducts = new ArrayList<>();
                if (listProductsOr.size() > 0) {
                    for (int i = 0; i < listProductsOr.size(); i++) {
                        if (listProductsOr.get(i).isAccess() == true) {
                            listProducts.add(listProductsOr.get(i));
                        }
                    }
                }
                List<History> listProducts2 = historyFacade.findBoughtProducts(user.getPerson());
                request.setAttribute("listProducts2", listProducts);
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                break;
            case "/buyProduct":
                personId = user.getPerson().getId().toString();
                pers = personFacade.find(Long.parseLong(personId));
                productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));               
                if (pers.getMoney() < product.getPrice()) {
                    request.setAttribute("info","У покупателя недостаточно денег!");
                    request.setAttribute("borderwidth",6.5); 
                    listProductsOr = productFacade.findAll();
                    listProducts = new ArrayList<>();
                    if (listProductsOr.size() > 0) {
                        for (int i = 0; i < listProductsOr.size(); i++) {
                            if (listProductsOr.get(i).isAccess() == true) {
                                listProducts.add(listProductsOr.get(i));
                            }
                        }
                    }
                    request.setAttribute("listProducts", listProducts);
                    listPersons = personFacade.findAll();
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break; 
                }
                pers.setMoney(pers.getMoney() - product.getPrice());
                pers.getListProducts().add(product);
                personFacade.edit(pers);
                userFacade.edit(user);
                user = userFacade.findByLogin(user.getLogin());
                session.setAttribute("user", user);
                session.setAttribute("upuser", session.getAttribute("user").toString()); 
                product.setAccess(false);
                History history = new History(product, pers, new GregorianCalendar().getTime(), null);
                historyFacade.create(history);
                productFacade.edit(product);
                request.setAttribute("info", "Товар '" + product.getName() + "' успешно куплен покупателем " + pers.getName() + " " + pers.getSurname() + "!");
                request.setAttribute("borderwidth",6.5); 
                request.getRequestDispatcher("/index.jsp").forward(request, response);                
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
