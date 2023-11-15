/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    private Connection conn = null;
    static PreparedStatement statement;

    public ProductDAO() throws Exception {
        conn = DBConnection.Connect();
    }

    public boolean existCourse(int ID) {
        boolean ok = false;
        try {

            statement = conn.prepareStatement("select ProductID from Products where ProductID = ?");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("ProductID") == ID) {
                    ok = true;
                }
            }

            //disconnect to database
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        //return result
        return ok;
    }

    public Products getProduct(int ProductID) {
        Products product = null;
        try {
            statement = conn.prepareStatement("select * from Products where ProductID = ?");
            statement.setInt(1, ProductID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product = new Products(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("Image"),
                        resultSet.getString("ProductName"),
                        resultSet.getInt("CategoryID"),
                        resultSet.getString("BrandID"),
                        resultSet.getInt("Price"),
                        resultSet.getString("Description"));

            }
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;
    }

    public int UpdateProduct(Products newpr) {
        int ketqua = 0;
        String sql = "update Products set Image=?, ProductName=?, Price=?, Description=? where ProductID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newpr.getImage());
            ps.setString(2, newpr.getProductName());
            ps.setInt(3, newpr.getPrice());
            ps.setString(4, newpr.getDescription());
            ps.setInt(5, newpr.getProductID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> products = new ArrayList<>();
        try {
            statement = conn.prepareStatement("select * from Products");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("Image"),
                        resultSet.getString("ProductName"),
                        resultSet.getInt("CategoryID"),
                        resultSet.getString("BrandID"),
                        resultSet.getInt("Price"),
                        resultSet.getString("Description"));
                products.add(product);

            }
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return products;
    }

    public ArrayList<Products> searchByName(String txtSearch) {
        ArrayList<Products> products = new ArrayList<>();
        try {
            statement = conn.prepareStatement("select * from Products where ProductName like ?");
            statement.setString(1, "%" + txtSearch + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("Image"),
                        resultSet.getString("ProductName"),
                        resultSet.getInt("CategoryID"),
                        resultSet.getString("BrandID"),
                        resultSet.getInt("Price"),
                        resultSet.getString("Description"));
                products.add(product);
            }
        } catch (Exception e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return products;
    }

  public static void main(String[] args) throws Exception {
    ProductDAO dao = new ProductDAO();
    Products pr = dao.getProduct(3);
    String image = pr.getImage();
    
    if (image != null) {
        System.out.println("Image: " + image);
    } else {
        System.out.println("Không có dữ liệu hình ảnh.");
    }
}

}
