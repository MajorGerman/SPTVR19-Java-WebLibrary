package servlets;

import entity.Person;
import entity.PersonFacade;
import entity.Product;
import entity.ProductFacade;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = {
    "/addProductForm",
    "/addProduct",
    "/listProducts",
    "/editProductForm1",
    "/editProductForm2",
    "/editProduct",
    "/addPersonForm",
    "/addPerson",
    "/listPersons",
    "/editPersonForm1",
    "/editPersonForm2",
    "/editPerson",
    "/buyProductForm",
    "/buyProduct"

})
public class MyServlet extends HttpServlet {
    @EJB 
    private ProductFacade productFacade;
    @EJB 
    private PersonFacade personFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        
        switch (path) {
            case "/addProductForm":
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                break;
            case "/addProduct":
                String name = request.getParameter("name");
                String price = request.getParameter("price");
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
                Product product = new Product(name, Integer.parseInt(price));
                productFacade.create(product);
                request.setAttribute("info","Добавлен товар: " + product.toString() );
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                for (int i = 0; i < listProducts.size(); i++) {
                    if (listProducts.get(i).isAccess() != true) {
                        listProducts.remove(i);
                    }
                }
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
            case "/editProductForm1":
                listProducts = productFacade.findAll();
                for (int i = 0; i < listProducts.size(); i++) {
                    if (listProducts.get(i).isAccess() != true) {
                        listProducts.remove(i);
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
                if("".equals(name) || name == null
                        || "".equals(surname) || surname == null
                        || "".equals(phone) || phone == null
                        || "".equals(money) || money == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("surname",surname);
                    request.setAttribute("phone",phone);
                    request.setAttribute("money",money);                   
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
                listPersons = personFacade.findAll();
                
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/editPersonForm1.jsp").forward(request, response);
            case "/editPersonForm2":                  
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
                if("".equals(name) || name == null
                        || "".equals(surname) || surname == null
                        || "".equals(phone) || phone == null
                        || "".equals(money) || money == null){
                    request.setAttribute("info","Заполните все поля формы");
                    request.setAttribute("borderwidth",6.5);
                    request.setAttribute("name",name);
                    request.setAttribute("surname",surname);
                    request.setAttribute("phone",phone);
                    request.setAttribute("money",money); 
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
                personFacade.edit(pers);
                request.setAttribute("personId", pers.getId());
                request.setAttribute("info","Данные покупателя успешно отредактированы: " + pers.toString());
                request.setAttribute("borderwidth",6.5);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;                
            case "/buyProductForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                break;
            case "/buyProduct":
                personId = request.getParameter("persId");
                pers = personFacade.find(Long.parseLong(personId));
                productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));               
                if (pers.getMoney() < product.getPrice()) {
                    request.setAttribute("info","У покупателя недостаточно денег!");
                    request.setAttribute("borderwidth",6.5); 
                    request.getRequestDispatcher("/WEB-INF/buyProductForm.jsp").forward(request, response);
                    break; 
                }
                pers.setMoney(pers.getMoney() - product.getPrice());
                pers.getListProducts().add(product);
                personFacade.edit(pers);
                product.setAccess(false);
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
