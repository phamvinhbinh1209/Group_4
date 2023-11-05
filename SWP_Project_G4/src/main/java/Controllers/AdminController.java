/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AdminDAOs;
import Models.Account;
import Models.Order;
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
public class AdminController extends HttpServlet {

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
            out.println("<title>Servlet adminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminController at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/Admin")) {
            // Hiển thị trang danh sách sản phẩm
            request.getRequestDispatcher("/orderList.jsp").forward(request, response);
        } else {
            if (path.startsWith("/Admin/deleteOrder/")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    AdminDAOs dao = new AdminDAOs();
                    dao.Delete(id);

                    // Sử dụng response.sendRedirect với context path
                    response.sendRedirect("Admin/orderList");

                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (path.startsWith("/Admin/updateOrder")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        Order od = dao.GetOrder(id);
                        if (od == null) {
                            response.sendRedirect("/Admin/orderList");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("orderInformation", od);
                            request.getRequestDispatcher("/updateOrderList.jsp").forward(request, response);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
//===================================================================================================================
        if (path.endsWith("/Admin/userList")) {
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        } else {
            if (path.endsWith("/Admin/addNewUser")) {
                request.getRequestDispatcher("/addNewUser.jsp").forward(request, response);
            } else {
                if (path.startsWith("/Admin/updateUser")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        Account acc = dao.GetAccount(id);
                        if (acc == null) {
                            response.sendRedirect("/Admin/userList");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("userInformation", acc);
                            request.getRequestDispatcher("/updateUserList.jsp").forward(request, response);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (path.startsWith("/Admin/deleteUser/")) {
                        try {
                            String[] data = path.split("/");
                            int id = Integer.parseInt(data[data.length - 1]);
                            AdminDAOs dao = new AdminDAOs();
                            dao.DeleteAccount(id);
                            // Sử dụng response.sendRedirect với context path
                            response.sendRedirect("Admin/userList");

                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

// Redirect hoặc thực hiện các thao tác khác sau khi xóa đơn hàng            }
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
        //processRequest(request, response);
        if (request.getParameter("btnUpdateOrder") != null && !request.getParameter("btnUpdateOrder").equals("Submit")) {
            try {
                int id = Integer.parseInt(request.getParameter("OrderID"));
                Date date = Date.valueOf(request.getParameter("Date"));
                String address = request.getParameter("Address");
                String phone = request.getParameter("Phone");
                int totalPrice = Integer.parseInt(request.getParameter("TotalPrice"));
                Date deliveryDate = Date.valueOf(request.getParameter("DeliveryDate"));
                String status = request.getParameter("Status");
                Order od = new Order(id, date, address, phone, totalPrice, deliveryDate, status);
                AdminDAOs dao = new AdminDAOs();
                int ketqua = dao.UpdateOrder(od);
                if (ketqua == 0) {
                    response.sendRedirect("/Admin/updateOrder");
                } else {
                    response.sendRedirect("/Admin");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //=====================================================================================================
        if (request.getParameter("btnAddNewUser") != null && request.getParameter("btnAddNewUser").equals("Addnew")) {
            try {
                String avatar = request.getParameter("Avatar");
                String username = request.getParameter("Username");
                String password = request.getParameter("Password");
                String email = request.getParameter("Email");
                String firstName = request.getParameter("FirstName");
                String lastName = request.getParameter("LastName");
                String gender = request.getParameter("Gender");
                Date birthday = Date.valueOf(request.getParameter("Birthday"));
                int role = Integer.parseInt(request.getParameter("Role"));

                Account acc = new Account(avatar, username, password, email, firstName, lastName, gender, birthday, role);
                AdminDAOs dao = new AdminDAOs();
                int ketqua = dao.AddNew(acc);
                if (ketqua == 0) {
                    response.sendRedirect("/Admin/addNewUser"); //redirect la tro ve trang addnew nhung xoa het du lieu(khong co du lieu) # f5 (f5 la load trang nhung co gui du lieu)
                } else {
                    response.sendRedirect("/Admin/userList");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //=====================================================================================================
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
                AdminDAOs dao = new AdminDAOs();
                int ketqua = dao.UpdateAccount(acc);
                if (ketqua == 0) {
                    response.sendRedirect("/Admin/updateUser");
                } else {
                    response.sendRedirect("Admin/userList");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
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