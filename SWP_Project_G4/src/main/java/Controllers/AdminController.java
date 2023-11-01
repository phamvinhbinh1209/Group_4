/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AdminDAOs;
import Models.Account;
import Models.ImportSource;
import Models.Order;
import Models.Product;
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

        if (path.endsWith("/AdminController/Home")) {
            request.getRequestDispatcher("/adminHome.jsp").forward(request, response);
        } else {
            if (path.endsWith("/AdminController/AddNewProduct")) {
                request.getRequestDispatcher("/addNewProduct.jsp").forward(request, response);
            } else if (path.startsWith("/AdminController/UpdateProduct")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    AdminDAOs dao = new AdminDAOs();
                    Product pr = dao.GetProduct(id);
                    if (pr == null) {
                        response.sendRedirect("/AdminController/Home");
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("productInformation", pr);
                        request.getRequestDispatcher("/updateProduct.jsp").forward(request, response);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (path.startsWith("/AdminController/DeleteProduct/")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        dao.DeleteProduct(id);
                        response.sendRedirect("/AdminController/Home");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
//==================================================================================================================================
        if (path.endsWith("/AdminController/ImportSource")) {
            request.getRequestDispatcher("/adminImportSource.jsp").forward(request, response);
        } else {
            if (path.endsWith("/AdminController/AddNewImportSource")) {
                request.getRequestDispatcher("/addNewImportSource.jsp").forward(request, response);
            } else if (path.startsWith("/AdminController/UpdateImportSource")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    AdminDAOs dao = new AdminDAOs();
                    ImportSource ip = dao.GetImportSource(id);
                    if (ip == null) {
                        response.sendRedirect("/AdminController/ImportSource");
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("importSourceInformation", ip);
                        request.getRequestDispatcher("/updateImportSource.jsp").forward(request, response);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (path.startsWith("/AdminController/DeleteImportSource/")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        dao.DeleteImportSource(id);
                        response.sendRedirect("/AdminController/ImportSource");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        if (path.endsWith("/AdminController/orderList")) {
            // Hiển thị trang danh sách sản phẩm
            request.getRequestDispatcher("/orderList.jsp").forward(request, response);
        } else {
            if (path.startsWith("/AdminController/deleteOrder/")) {
                try {
                    String[] data = path.split("/");
                    int id = Integer.parseInt(data[data.length - 1]);
                    AdminDAOs dao = new AdminDAOs();
                    dao.Delete(id);

                    // Sử dụng response.sendRedirect với context path
                    response.sendRedirect("AdminController/orderList");

                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (path.startsWith("/AdminController/updateOrder")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        Order od = dao.GetOrder(id);
                        if (od == null) {
                            response.sendRedirect("/AdminController/orderList");
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
        if (path.endsWith("/AdminController/userList")) {
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        } else {
            if (path.endsWith("/AdminController/addNewUser")) {
                request.getRequestDispatcher("/addNewUser.jsp").forward(request, response);
            } else {
                if (path.startsWith("/AdminController/updateUser")) {
                    try {
                        String[] data = path.split("/");
                        int id = Integer.parseInt(data[data.length - 1]);
                        AdminDAOs dao = new AdminDAOs();
                        Account acc = dao.GetAccount(id);
                        if (acc == null) {
                            response.sendRedirect("/AdminController/userList");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("userInformation", acc);
                            request.getRequestDispatcher("/updateUserList.jsp").forward(request, response);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (path.startsWith("/AdminController/deleteUser/")) {
                        try {
                            String[] data = path.split("/");
                            int id = Integer.parseInt(data[data.length - 1]);
                            AdminDAOs dao = new AdminDAOs();
                            dao.DeleteAccount(id);
                            // Sử dụng response.sendRedirect với context path
                            response.sendRedirect("AdminController/userList");

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

        if (request.getParameter("btnSubmitAdd") != null && request.getParameter("btnSubmitAdd").equals("Confirm")) {
            try {
                int id = Integer.parseInt(request.getParameter("paraID"));
                String product_name = request.getParameter("paraProductName");
                String brand = request.getParameter("paraBrand");
                String color = request.getParameter("paraColor");
                int size = Integer.parseInt(request.getParameter("paraSize"));
                int quantity = Integer.parseInt(request.getParameter("paraQuantity"));
                int price = Integer.parseInt(request.getParameter("paraPrice"));
                String picture = request.getParameter("paraPicure");
                Product p = new Product(id, product_name, brand, color, size, quantity, price, picture);
                AdminDAOs dao = new AdminDAOs();
                int kq = dao.AddNewProduct(p);
                if (kq != 0) {
                    response.sendRedirect("/AdminController/Home");
                } else {
                    response.sendRedirect("/AdminController/AddNewProduct");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("btnSubmitUpdate") != null && request.getParameter("btnSubmitUpdate").equals("Confirm")) {
            try {
                int id = Integer.parseInt(request.getParameter("paraID"));
                String product_name = request.getParameter("paraProductName");
                String brand = request.getParameter("paraBrand");
                String color = request.getParameter("paraColor");
                int size = Integer.parseInt(request.getParameter("paraSize"));
                int quantity = Integer.parseInt(request.getParameter("paraQuantity"));
                int price = Integer.parseInt(request.getParameter("paraPrice"));
                String picture = request.getParameter("paraPicture");
                Product p = new Product(id, product_name, brand, color, size, quantity, price, picture);
                AdminDAOs dao = new AdminDAOs();
                int kq = dao.UpdateProduct(p);
                if (kq != 0) {
                    response.sendRedirect("/AdminController/Home");
                } else {
                    response.sendRedirect("/AdminController/UpdateProduct");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("btnSubmitAddImportSource") != null && request.getParameter("btnSubmitAddImportSource").equals("Confirm")) {
            try {
                int id = Integer.parseInt(request.getParameter("paraID"));
                String product_name = request.getParameter("paraProductName");
                String brand = request.getParameter("paraBrand");
                String color = request.getParameter("paraColor");
                int size = Integer.parseInt(request.getParameter("paraSize"));
                int quantity = Integer.parseInt(request.getParameter("paraQuantity"));
                int price = Integer.parseInt(request.getParameter("paraPrice"));
                Date date_buy = Date.valueOf(request.getParameter("paraDateBuy"));
                ImportSource ip = new ImportSource(id, product_name, brand, color, size, quantity, price, date_buy);
                AdminDAOs dao = new AdminDAOs();
                int kq = dao.AddNewImportSource(ip);
                if (kq != 0) {
                    response.sendRedirect("/AdminController/ImportSource");
                } else {
                    response.sendRedirect("/AdminController/AddNewImportSource");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("btnSubmitUpdateImportSource") != null && request.getParameter("btnSubmitUpdateImportSource").equals("Confirm")) {
            try {
                int id = Integer.parseInt(request.getParameter("paraID"));
                String product_name = request.getParameter("paraProductName");
                String brand = request.getParameter("paraBrand");
                String color = request.getParameter("paraColor");
                int size = Integer.parseInt(request.getParameter("paraSize"));
                int quantity = Integer.parseInt(request.getParameter("paraQuantity"));
                int price = Integer.parseInt(request.getParameter("paraPrice"));
                Date date_buy = Date.valueOf(request.getParameter("paraDateBuy"));
                ImportSource ip = new ImportSource(id, product_name, brand, color, size, quantity, price, date_buy);
                AdminDAOs dao = new AdminDAOs();
                int kq = dao.UpdateImportSource(ip);
                if (kq != 0) {
                    response.sendRedirect("/AdminController/ImportSource");
                } else {
                    response.sendRedirect("/AdminController/UpdateImportSource");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("btnUpdateOrder") != null && !request.getParameter("btnUpdateOrder").equals("Submit")) {
            try {
                int id = Integer.parseInt(request.getParameter("ID"));
                Date date = Date.valueOf(request.getParameter("Date"));
                String address = request.getParameter("Address");
                String phone = request.getParameter("Phone");
                String product = request.getParameter("Product");
                int price = Integer.parseInt(request.getParameter("Price"));
                Date deliveryDate = Date.valueOf(request.getParameter("DeliveryDate"));
                String status = request.getParameter("Status");
                Order od = new Order(id, date, address, phone, product, price, deliveryDate, status);
                AdminDAOs dao = new AdminDAOs();
                int ketqua = dao.UpdateOrder(od);
                if (ketqua == 0) {
                    response.sendRedirect("/AdminController/updateOrder");
                } else {
                    response.sendRedirect("AdminController/orderList");
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
                    response.sendRedirect("/AdminController/addNewUser"); //redirect la tro ve trang addnew nhung xoa het du lieu(khong co du lieu) # f5 (f5 la load trang nhung co gui du lieu)
                } else {
                    response.sendRedirect("/AdminController/userList");
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
                    response.sendRedirect("/AdminController/updateUser");
                } else {
                    response.sendRedirect("AdminController/userList");
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
