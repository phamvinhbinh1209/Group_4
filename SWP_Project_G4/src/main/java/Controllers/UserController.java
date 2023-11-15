/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CartDAO;
import DAOs.ProductDAO;
import Models.Account;
import Models.Cart;
import Models.Order;
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

        if (path.startsWith("/User/UserProfile")) {
            try {
                String[] data = path.split("/");
                int id = Integer.parseInt(data[data.length - 1]);
                AccountDAO dao = new AccountDAO();
                Account acc = dao.GetAccount(id);
                if (acc == null) {
                    response.sendRedirect("/Home");
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
        //========================================================================================
        if (path.endsWith("/User/userHome")) {
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            if (path.startsWith("/User/ProductDetail")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    ProductDAO dao = new ProductDAO();
                    Products pr = dao.getProduct(id);
                    if (pr == null) {
                        response.sendRedirect("/User/userHome");
                    } else {
                        HttpSession session = (HttpSession) request.getSession();
                        session.setAttribute("productInformation", pr);
                        request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                Account acc = new Account(id, avatar, username, password, email, firstName, lastName, gender, birthday);
                AccountDAO dao = new AccountDAO();
                int ketqua = dao.UpdateAccount(acc);
                if (ketqua == 0) {
                    
                    response.sendRedirect("/User/UserProfile");
                } else {
                    //cho nay la update thanh cong
                    //thong bao
                    request.getSession().setAttribute("success", "Update successully");
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
            }
        }

        //===================================================================================
        if (request.getParameter("btnAddToCart") != null && request.getParameter("btnAddToCart").equals("Add To Cart")) {
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

                int AccountID = acc.getAccountID();
                int ProductID = (Integer) request.getSession().getAttribute("ProductID");
                int Quantity = Integer.parseInt(request.getParameter("Quantity"));
                int Price = (Integer) request.getSession().getAttribute("Price");
                int TotalPrice = Quantity * Price;
                int NumSize = Integer.parseInt(request.getParameter("NumSize"));
                int RemainQuantity = cartDAO.getProductQuantity(NumSize, ProductID);
                if (Quantity > RemainQuantity) {
                    request.getSession().setAttribute("QuantityError", "There are only " + RemainQuantity + " products remaining!");
                    response.sendRedirect("UserController/ProductDetail/" + ProductID);

                } else {
                    List<Cart> cartList = new CartDAO().getCartsByUserID(AccountID);
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

                    Cart cart = new Cart(AccountID, ProductID, Quantity, NumSize, Price, TotalPrice);

                    int ketqua = cartDAO.addCart(cart);
                    if (ketqua == 0) {
                        response.sendRedirect("/Home");
                    } else {
                        response.sendRedirect("/Home");
                    }
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
