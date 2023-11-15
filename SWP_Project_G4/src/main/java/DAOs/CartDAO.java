/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Cart;
import Models.Products;
import Models.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class CartDAO {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection;

    public int addCart(Cart cart) throws ClassNotFoundException {
        int kq = 0;
        String query = "INSERT INTO Cart (AccountID, ProductID, Quantity, Size, Price, TotalPrice) VALUES (?,?,?,?,?,?)";
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cart.getAccountID());
            preparedStatement.setInt(2, cart.getProductID());
            preparedStatement.setInt(3, cart.getQuantity());
            preparedStatement.setInt(4, cart.getSize());
            preparedStatement.setInt(5, cart.getPrice());
            preparedStatement.setInt(6, cart.getTotalPrice());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return kq;
    }

    public List<Cart> getCartsByUserID(int userID) throws ClassNotFoundException {
        List<Cart> list = new ArrayList<>();
        String query = "SELECT * FROM Cart where AccountID = ?";
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7)
                );
                list.add(cart);
            }
            connection.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public Cart GetCartByID(int cartID) {
        Cart cart = null;
        try {
            connection = new DBConnection().Connect();
            preparedStatement = connection.prepareStatement("select * from Cart where cartID = ?");
            preparedStatement.setInt(1, cartID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cart = new Cart(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6));
            }
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return cart;
    }

    public boolean deleteCart(int cartID) throws ClassNotFoundException {
        String query = "DELETE FROM Cart WHERE CartID=?";
        boolean deleted = false;
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                deleted = true; // Xóa thành công
            }
            connection.close();
        } catch (SQLException e) {
        }
        return deleted;
    }

    public void updateCart(Cart cart) throws ClassNotFoundException {
        String query = "UPDATE Cart SET  Quantity = ? ,TotalPrice = ?  WHERE AccountId = ? and ProductId = ?";
        try {
            connection = new DBConnection().Connect();//mo ket noi voi sql
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cart.getQuantity());
            preparedStatement.setInt(2, cart.getTotalPrice());
            preparedStatement.setInt(3, cart.getAccountID());
            preparedStatement.setInt(4, cart.getProductID());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
        }
    }

    public List<Size> getSizeList() {

        List<Size> sizeList = new ArrayList<>();

        String query = "Select * from Size";

        try {
            connection = new DBConnection().Connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sizeList.add(new Size(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
            connection.close();
        } catch (Exception e) {
        }
        return sizeList;
    }

    ///This function need to update
    public List<Size> getSizeListByProductID(int productID) {

        List<Size> sizeList = new ArrayList<>();

        String query = "Select * from Size where ProductID=?";
        try {
            connection = new DBConnection().Connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sizeList.add(new Size(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
            connection.close();
        } catch (Exception e) {
        }
        return sizeList;
    }

    public void UpdateSizeQuantity(int numSize, int ProductID, int Quantity) throws ClassNotFoundException {
        try {
            connection = new DBConnection().Connect();
            String query = "UPDATE Size SET Quantity = ? WHERE ProductID = ? AND NumSize = ?";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, Quantity); // Set giá trị cho tham số thứ nhất
                preparedStatement.setInt(2, ProductID); // Set giá trị cho tham số thứ hai
                preparedStatement.setInt(3, numSize);

                int rowsUpdated = preparedStatement.executeUpdate(); // Thực hiện cập nhật dữ liệu
                if (rowsUpdated > 0) {
                    System.out.println("Dữ liệu đã được cập nhật thành công.");
                } else {
                    System.out.println("Không có bản ghi nào được cập nhật.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProductQuantity(int numSize, int productID) {
        int quantity = 0;
        String query = "select Quantity from Size where numSize=? and ProductID=?";

        try {
            connection = new DBConnection().Connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numSize);
            preparedStatement.setInt(2, productID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantity = resultSet.getInt("Quantity");
            }
            connection.close();
        } catch (Exception e) {
        }
        return quantity;
    }

    public static void main(String[] args) throws Exception {
        CartDAO dao = new CartDAO();

        // Gọi phương thức AddToCart
        // Tạo một đối tượng Cart với thông tin giả
        List<Cart> cartItem = dao.getCartsByUserID(3);

        for (Cart cart : cartItem) {
            System.out.println(cart);
        }

    }
}
