/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.ProductDAO;
import Models.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class SearchByAjax extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt");
        try {
            ProductDAO dao = new ProductDAO();
            ArrayList<Products> products = dao.searchByName(txtSearch);
            PrintWriter out = response.getWriter();
            for (Products o : products) {
                out.println("<div class=\"col-sm-6 col-md-4 products\" data-price=\"" + o.getPrice() + "\">\n"
                        + "                            <div class=\"thumbnail\">\n"
                        + "                                    <div class=\"cont-item\">\n"
                        + "                                        <img src=\"" + o.getImage() + "\" alt=\"\" />\n"
                        + "                                    </div>\n"
                        + "                                    <div class=\"caption\">\n"
                        + "                                    <h3 class=\"name\">\n"
                        + "                                        <a href=\"UserController/ProductDetail/" + o.getProductID() + "\">" + o.getProductName() + "</a>\n"
                        + "                                    </h3>\n"
                        + "                                        <h3 class=\"color\">" + o.getDescription() + "</h3>\n"
                        + "                                        <h3 class=\"price\">" + o.getPrice() + " VND</h3>\n"
                        + "                                    </div>\n"
                        + "                            </div>\n"
                        + "                        </div>");
            }
        } catch (Exception ex) {
            Logger.getLogger(SearchByAjax.class.getName()).log(Level.SEVERE, null, ex);
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
//        request.setCharacterEncoding("UTF-8");
//        String txtSearch = request.getParameter("txt");
//        try {
//            ProductDAO dao = new ProductDAO();
//            ArrayList<Products> products = dao.searchByName(txtSearch);
//            PrintWriter out = response.getWriter();
//            for (Products o : products) {
//                out.println("<div class=\"col-sm-6 col-md-4\">\n"
//                        + "                            <div class=\"thumbnail\">\n"
//                        + "                                <a href=\"#\">\n"
//                        + "                                    <div class=\"cont-item\">\n"
//                        + "                                        <img src=\"" + o.getImage() + "\" alt=\"\" />\n"
//                        + "                                    </div>\n"
//                        + "                                    <div class=\"caption\">\n"
//                        + "                                        <h3 class=\"name\">" + o.getProductName() + "</h3>\n"
//                        + "                                        <h3 class=\"color\">" + o.getDescription() + "</h3>\n"
//                        + "                                        <h3 class=\"price\">" + o.getPrice() + " VND</h3>\n"
//                        + "                                    </div>\n"
//                        + "                                </a>\n"
//                        + "                            </div>\n"
//                        + "                        </div>");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(SearchByAjax.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
