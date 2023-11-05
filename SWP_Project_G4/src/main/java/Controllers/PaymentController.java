/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CartDAO;
import DAOs.OrderDAO;
import DAOs.OrderDetailDAO;
import DAOs.ProductDAO;
import Models.Account;
import Models.Cart;
import Models.Order;
import Models.OrderDetail;
import Models.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class PaymentController extends HttpServlet {

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
            out.println("<title>Servlet PaymentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentController at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        if (path.endsWith("/Payment")) {
            request.getRequestDispatcher("/payment.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession();
            OrderDAO orderDAO = new OrderDAO();
            AccountDAO accDAO = new AccountDAO();
            CartDAO cartDAO = new CartDAO();
            ProductDAO proDAO = new ProductDAO();

            OrderDetailDAO orderdetailDAO = new OrderDetailDAO();
            String username = (String) session.getAttribute("acc");
            Account acc = accDAO.GetAccountUser(username);
            // Date date = new Date();
            // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            List<Cart> cartList = cartDAO.getCartsByUserID(acc.getAccountID());
            if (request.getParameter("btnOrder") != null && request.getParameter("btnOrder").equals("Order")) {

                String phone = request.getParameter("Phone");
                String address = request.getParameter("address");
                String status = request.getParameter("Status");
                int totalPrice = (int) session.getAttribute("totalPrice");
                Order order = new Order();
                order.setAddress(address);
                order.setPhone(phone);
                order.setDate(new Date());
                order.setAccountID(acc.getAccountID());
                order.setTotalPrice(totalPrice);
                order.setStatus(status);

                Order addedOrder = orderDAO.AddOrder(order);
                int newOrderID = addedOrder.getOrderID();
                
                for (Cart cart : cartList) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(newOrderID);
                    orderDetail.setPrice(cart.getPrice());
                    orderDetail.setQuantity(cart.getQuantity());
                    orderDetail.setSize(cart.getSize());
                    orderDetail.setProductID(cart.getProductID());
                    orderdetailDAO.AddNewOrderDetail(orderDetail);
                    int NewQuantity = cartDAO.getProductQuantity(cart.getSize(), cart.getProductID()) - cart.getQuantity();
                    System.out.println(NewQuantity);
                    cartDAO.UpdateSizeQuantity(cart.getSize(), cart.getProductID(), NewQuantity);
                    cartDAO.deleteCart(cart.getCartID());
                }
              
                
                response.sendRedirect("/Home");

            }
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
