package DAOs;

import Models.Account;
import Models.ImportSource;
import Models.Order;
import Models.Products;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAOs {

    public Connection conn; // Use the connection from DBConnection

    public AdminDAOs() throws SQLException, ClassNotFoundException {
        // Initialize the connection when creating an instance of AdminDAOs
        conn = Database.DBConnection.Connect();
    }

    public ResultSet GetAllOrder() {
        ResultSet rs = null;
        try {
            String sql = "Select * From [Order]";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet GetAllUser() {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Account";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int Delete(int id) {
        String sql = "delete from [Order] where id=?";
        int ketqua = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public Order GetOrder(int id) {
        String sql = "Select * From [Order] where OrderID=?";
        Order od = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                od = new Order(rs.getInt("OrderID"), rs.getDate("OrderDate"), rs.getString("Address"), rs.getString("Phone"), rs.getInt("TotalPrice"), rs.getDate("DeliveryDate"), rs.getString("Status"), rs.getInt("AccountID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return od;
    }

    public Account GetAccount(int id) {
        String sql = "Select * From Account where AccountID=?";
        Account acc = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account(rs.getInt("AccountID"), rs.getString("avatar"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getDate("birthday"), rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public int DeleteAccount(int id) {
        String sql = "delete from Account where AccountID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateAccount(Account newacc) {
        int ketqua = 0;
        String sql = "update Account set avatar=?, username=?, password=?, email=?, firstName=?, lastName=?, gender=?, birthday=?, role=? where AccountID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newacc.getAvatar());
            ps.setString(2, newacc.getUsername());
            ps.setString(3, newacc.getPassword());
            ps.setString(4, newacc.getEmail());
            ps.setString(5, newacc.getFirstName());
            ps.setString(6, newacc.getLastName());
            ps.setString(7, newacc.getGender());
            ps.setDate(8, (Date) newacc.getBirthday());
            ps.setInt(9, newacc.getRole());
            ps.setInt(10, newacc.getAccountID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateOrder(Order newod) {
        int ketqua = 0;
        String sql = "update [Order] set OrderDate=?, Address=?, Phone=?, TotalPrice=?, DeliveryDate=?, Status=? where OrderID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, (Date) newod.getDate());
            ps.setString(2, newod.getAddress());
            ps.setString(3, newod.getPhone());
            ps.setInt(4, newod.getTotalPrice());
            ps.setDate(5, (Date) newod.getDeliveryDate());
            ps.setString(6, newod.getStatus());
            ps.setInt(7, newod.getOrderID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int AddNew(Account acc) {
        String sql = "Insert into Account values( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int kq = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getAvatar());
            ps.setString(2, acc.getUsername());
            ps.setString(3, Service.MD5.getMd5(acc.getPassword()));//md5 nữa 
            ps.setString(4, acc.getEmail());
            ps.setString(5, acc.getFirstName());
            ps.setString(6, acc.getLastName());
            ps.setString(7, acc.getGender());
            ps.setDate(8, (Date) acc.getBirthday());
            ps.setInt(9, acc.getRole());
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
//=======================================================================================================

    public ResultSet GetAllProduct() throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            String sql = "Select * From Products";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int DeleteProduct(int ID) {
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from Products where ProductID=?");
            ps.setInt(1, ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int AddNewProduct(Products pro) {
        String sql = "insert into Products values(?, ?, ?, ?, ?, ?)";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pro.getImage());
            ps.setString(2, pro.getProductName());
            ps.setInt(3, pro.getCategoryID());
            ps.setString(4, pro.getBrandID());
            ps.setInt(5, pro.getPrice());
            ps.setString(6, pro.getDescription());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateProduct(Products pro) {
        String sql = "update Products set Image=?, ProductName=?, CategoryID=?, BrandID=?, Price=?, Description=? where ProductID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pro.getImage());
            ps.setString(2, pro.getProductName());
            ps.setInt(3, pro.getCategoryID());
            ps.setString(4, pro.getBrandID());
            ps.setInt(5, pro.getPrice());
            ps.setString(6, pro.getDescription());
            ps.setInt(7, pro.getProductID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public Products GetProducts(int ID) {
        String sql = "Select * From Products where ProductID=?";
        Products pr = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pr = new Products(rs.getInt("ProductID"), rs.getString("Image"), rs.getString("ProductName"), rs.getInt("CategoryID"), rs.getString("BrandID"), rs.getInt("Price"), rs.getString("Description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }

   //========================================================================================================================

 public ResultSet GetImportSource() throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            String sql = "Select * From Size";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int AddNewImportSource(ImportSource ip) {
        String sql = "insert into Size values(?, ?, ?)";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ip.getNumSize());
            ps.setInt(2, ip.getProductID());
            ps.setInt(3, ip.getQuantity());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateImportSource(ImportSource ip) {
        String sql = "update Size set NumSize=?, ProductID=?, Quantity=? where SizeID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ip.getNumSize());
            ps.setInt(2, ip.getProductID());
            ps.setInt(3, ip.getQuantity());
            ps.setInt(4, ip.getSizeID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int DeleteImportSource(int SizeID) {
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from Size where SizeID=?");
            ps.setInt(1, SizeID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public ImportSource GetImportSource(int SizeID) {
        String sql = "Select * From Size where SizeID=?";
        ImportSource ip = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, SizeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ip = new ImportSource(rs.getInt("SizeID"), rs.getInt("NumSize"), rs.getInt("ProductID"), rs.getInt("Quantity"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }

    public static void main(String[] args) throws Exception {
        // Tạo đối tượng AdminDAOs với kết nối cơ sở dữ liệu
        AdminDAOs dao = new AdminDAOs();

        // Tạo đối tượng Products với thông tin cần cập nhật
        Products product = new Products();
        product.setProductID(1);  // Thay đổi giá trị tương ứng
        product.setImage("new_image_url");
        product.setProductName("New Product Name");
        product.setCategoryID(2);  // Thay đổi giá trị tương ứng
        product.setBrandID("New Brand ID");
        product.setPrice(50);  // Thay đổi giá trị tương ứng
        product.setDescription("New Description");

        // Gọi hàm UpdateProduct để cập nhật sản phẩm
        int result = dao.UpdateProduct(product);

        if (result > 0) {
            System.out.println("Cập nhật sản phẩm thành công!");
        } else {
            System.out.println("Cập nhật sản phẩm thất bại!");
        }
    }

}
