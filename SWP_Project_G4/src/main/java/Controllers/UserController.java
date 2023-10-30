/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.ProductDAO;
import Models.Account;
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
                    request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
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
