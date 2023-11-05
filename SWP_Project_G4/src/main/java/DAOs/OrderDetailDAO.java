/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Order;
import Models.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO {
     PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection;
    
    public void AddNewOrderDetail(OrderDetail order) throws ClassNotFoundException{
         String query = "INSERT INTO OrderDetail (Quantity,Price,ProductID,OrderID,Size) VALUES (?,?,?,?,?)";
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getQuantity());
            preparedStatement.setInt(2, order.getPrice());
            preparedStatement.setInt(3, order.getProductID());
            preparedStatement.setInt(4, order.getOrderID());
            preparedStatement.setInt(5, order.getSize());           
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
