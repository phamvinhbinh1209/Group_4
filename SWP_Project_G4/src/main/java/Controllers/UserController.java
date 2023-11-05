/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
<<<<<<< HEAD
import DAOs.CartDAO;
import DAOs.ProductDAO;
import Models.Account;
import Models.Cart;
=======
import DAOs.ProductDAO;
import Models.Account;
>>>>>>> c3635680a5b28eeaa1ee286cbe680ef5d9cf5bd0
import Models.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        // processRequest(request, response);
        String path = request.getRequestURI();
<<<<<<< HEAD

        if (path.startsWith("/UserController/UserProfile")) {
            try {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                AccountDAO dao = new AccountDAO();
                Account acc = dao.GetAccount(id);
                if (acc == null) {
                    response.sendRedirect("/UserController/userHome");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("userInformation", acc);
                    request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (path.startsWith("/UserController/ProductDetail")) {
=======
        if (path.endsWith("/UserController/userHome")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            if (path.startsWith("/UserController/UserProfile")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    AccountDAO dao = new AccountDAO();
                    Account acc = dao.GetAccount(id);
                    if (acc == null) {
                        response.sendRedirect("/UserController/userHome");
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("userInformation", acc);
                        request.getRequestDispatcher("/userProfile.jsp").forward(request, response);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (path.startsWith("/UserController/ProductDetail")) {
>>>>>>> c3635680a5b28eeaa1ee286cbe680ef5d9cf5bd0
            try {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                ProductDAO dao = new ProductDAO();
                Products pr = dao.getProduct(id);
                if (pr == null) {
                    response.sendRedirect("/UserController/userHome");
                } else {
                    HttpSession session = (HttpSession) request.getSession();
                    session.setAttribute("productInformation", pr);
<<<<<<< HEAD
                    request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
=======
                    request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
>>>>>>> c3635680a5b28eeaa1ee286cbe680ef5d9cf5bd0
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
<<<<<<< HEAD
        }
        if (path.endsWith("/Login")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        if (path.endsWith("/Home")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
=======
>>>>>>> c3635680a5b28eeaa1ee286cbe680ef5d9cf5bd0
        }
        }
        //========================================================================================
        
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
        //  processRequest(request, response);
        if (request.getParameter("btnUpdateUser") != null && !request.getParameter("btnUpdateUser").equals("Submit")) {
            try {
                int id = Integer.parseInt(request.getParameter("ID"));
                String avatar = request.getParameter("Avatar");
                String username = request.getParameter("Username");
                String password = request.getParameter("Password");
                String email = request.getParameter("Email");
                String firstName = request.getParameter("FirstName");
                String lastName = request.getParameter("LastName");
                String gender = request.getParameter("Gender");
                Date birthday = Date.valueOf(request.getParameter("Birthday"));
                int role = Integer.parseInt(request.getParameter("Role"));
                Account acc = new Account(id, avatar, username, password, email, firstName, lastName, gender, birthday, role);
                AccountDAO dao = new AccountDAO();
                int ketqua = dao.UpdateAccount(acc);
                if (ketqua == 0) {
                    response.sendRedirect("/UserController/UserProfile");
                } else {
                    response.sendRedirect("UserController/userHome");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
<<<<<<< HEAD
        //===================================================================================================

        if (request.getParameter("btnAddToCart") != null && request.getParameter("btnAddToCart").equals("AddToCart")) {
            try {
                // lấy acccountID

                AccountDAO accDAO = new AccountDAO();
                ProductDAO proDAO = new ProductDAO();
                CartDAO cartDAO = new CartDAO();
                String username = (String) request.getSession().getAttribute("acc");
                Account acc = accDAO.GetAccountUser(username);

                if (acc == null) {
                    String message = "Please log in to purchase!";

                    String script = "var result = confirm('" + message + "');";
                    script += "if (result) { window.location = '/Login'; }";
                    script += "else { window.location.replace('/Home'); }";

                    response.getWriter().write("<script>" + script + "</script>");
                }

                int Account_ID = acc.getAccountID();
                int ProductID = (Integer) request.getSession().getAttribute("ProductID");
                int Quantity = Integer.parseInt(request.getParameter("Quantity"));
                int Price = (Integer) request.getSession().getAttribute("Price");
                int TotalPrice = Quantity * Price;
                int NumSize = Integer.parseInt(request.getParameter("NumSize"));

                List<Cart> cartList = new CartDAO().getCartsByUserID(Account_ID);
                for (Cart cart : cartList) {
                    if (cart.getProductID() == ProductID && cart.getSize() == NumSize) {
                        Quantity += cart.getQuantity();
                        TotalPrice = Quantity * Price;
                        cart.setQuantity(Quantity);
                        cart.setTotalPrice(TotalPrice);
                        cartDAO.updateCart(cart);
                        response.sendRedirect("/Home");
                        return;
                    }
                }

                Cart cart = new Cart(Account_ID, ProductID, Quantity, NumSize, Price, TotalPrice);

                int ketqua = cartDAO.addCart(cart);
                if (ketqua == 0) {
                    response.sendRedirect("/Home");
                } else {
                    response.sendRedirect("/Home");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(UserController.class
                        .getName()).log(Level.SEVERE, null, ex);
=======
        //===================================================================================
        if (request.getParameter("btnAddToCart") != null && !request.getParameter("btnAddToCart").equals("AddToCart")) {
            try {

                String image = request.getParameter("Image");
                String productName = request.getParameter("ProductName");
                int price = Integer.parseInt(request.getParameter("Price"));
                String description = request.getParameter("Description");
              
                Products pr = new Products(image,productName,price,description);
                ProductDAO dao = new ProductDAO();
                int ketqua = dao.UpdateProduct(pr);
                if (ketqua == 0) {
                    response.sendRedirect("/UserController/UserProfile");
                } else {
                    response.sendRedirect("UserController/userHome");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> c3635680a5b28eeaa1ee286cbe680ef5d9cf5bd0
            }
        }
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
