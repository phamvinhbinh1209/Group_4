/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Account;
import Models.Order;
import Models.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class OrderDAO {

    private Connection conn = null;
    static PreparedStatement statement;

    public OrderDAO() throws Exception {
        conn = DBConnection.Connect();
    }

    public List<Order> getAllOrdersByAccountID(int id) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM [Order] WHERE AccountID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order(
                        rs.getInt("OrderID"),
                        rs.getDate("OrderDate"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("TotalPrice"),
                        rs.getDate("DeliveryDate"),
                        rs.getString("Status"),
                        rs.getInt("AccountID")
                );
                orderList.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public List<OrderDetail> getAllOrderDetailByOrderID(int orderID) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail WHERE OrderID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderItemID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getInt("ProductID"),
                        rs.getInt("OrderID"),
                        rs.getInt("Size")
                );
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailList;
    }

    public Order AddOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = new DBConnection().Connect();
            String sql = "INSERT INTO [Order] (OrderDate, Address, Phone, TotalPrice, Status, AccountID) VALUES (?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Đặt các tham số cho câu lệnh SQL
            stmt.setDate(1, new java.sql.Date(order.getDate().getTime()));
            stmt.setString(2, order.getAddress());
            stmt.setString(3, order.getPhone());
            stmt.setInt(4, order.getTotalPrice());
            //stmt.setDate(5, new java.sql.Date(order.getDeliveryDate().getTime()));
            stmt.setString(5, order.getStatus());
            stmt.setInt(6, order.getAccountID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderID = generatedKeys.getInt(1);
                order.setOrderID(orderID); // Cập nhật đối tượng Order với orderID được tự động tăng
            } else {
                throw new SQLException("Creating order failed, no generated key obtained.");
            }

            return order;
        } catch (SQLException e) {
            // Xử lý lỗi
            e.printStackTrace();
            return null;
        } finally {
            conn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        OrderDAO dao = new OrderDAO();
        List<Order> orderList = dao.getAllOrdersByAccountID(21);
        for (Order o : orderList) {
            System.out.println(o);
        }
    }
}

