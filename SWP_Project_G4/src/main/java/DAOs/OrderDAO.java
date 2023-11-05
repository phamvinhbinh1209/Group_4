/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Order;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class OrderDAO {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection;

    public void AddNewOrder(Order order) throws ClassNotFoundException {
        String query = "INSERT INTO [Order] (OrderDate, Address, Phone, TotalPrice, Status, AccountID) VALUES (?,?,?,?,?,?)";
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(order.getDate().getTime()));
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, order.getPhone());
            preparedStatement.setInt(4, order.getTotalPrice());
            // preparedStatement.setTimestamp(5, new java.sql.Timestamp(order.getDeliveryDate().getTime()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setInt(6, order.getAccountID());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
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

    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // String orderDate = formatter.format(date);
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setDate(new Date()); // Đặt ngày đặt hàng
        order.setAddress("123 Main St"); // Đặt địa chỉ
        order.setPhone("555123"); // Đặt số điện thoại
        order.setTotalPrice(500); // Đặt tổng giá
        //order.setDeliveryDate(null); // Đặt ngày giao hàng
        order.setStatus("Pending"); // Đặt trạng thái
        order.setAccountID(4); // Đặt ID tài khoản
        try {
            orderDAO.AddNewOrder(order);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
