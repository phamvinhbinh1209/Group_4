/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CartDAO;
import Models.Account;
import Models.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class CartController extends HttpServlet {

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
        if (path.endsWith("/Cart")) {
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } else {
            if (path.startsWith("/Cart/Delete")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);

                    // Thực hiện xóa sản phẩm khỏi giỏ hàng, ví dụ:
                    CartDAO cartDAO = new CartDAO();
                    boolean success = cartDAO.deleteCart(id);

                    if (success) {
                        response.sendRedirect("/Cart");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
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
         if (request.getParameter("btnUpdate") != null && request.getParameter("btnUpdate").equals("Update")) {
             try {
                 int cartID = Integer.parseInt(request.getParameter("CartID"));
                 int Quantity = Integer.parseInt(request.getParameter("Quantity"));
                 CartDAO cartDAO = new CartDAO();
                 Cart cart = cartDAO.GetCartByID(cartID);                
                 cart.setQuantity(Quantity);
                 int totalPrice = Quantity * cart.getPrice();
                 cart.setTotalPrice(totalPrice);
                 cartDAO.updateCart(cart);
                 response.sendRedirect("/Cart");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
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
