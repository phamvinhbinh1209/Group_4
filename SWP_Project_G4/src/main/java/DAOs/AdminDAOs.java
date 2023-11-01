package DAOs;

import Models.Account;
import Models.ImportSource;
import Models.Order;
import Models.Product;
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
            String sql = "Select * From OrderList";
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

//    public void deleteOrders(List<Integer> orderIds) throws SQLException, ClassNotFoundException {
//        try {
//            String sql = "DELETE FROM OrderList WHERE order_id IN (";
//            for (int i = 0; i < orderIds.size(); i++) {
//                sql += "?";
//                if (i < orderIds.size() - 1) {
//                    sql += ", ";
//                }
//            }
//            sql += ")";
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            for (int i = 0; i < orderIds.size(); i++) {
//                ps.setInt(i + 1, orderIds.get(i));
//            }
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public int Delete(int id) {
        String sql = "delete from OrderList where id=?";
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
        String sql = "Select * From OrderList where ID=?";
        Order od = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                od = new Order(rs.getInt("ID"), rs.getDate("Date"), rs.getString("Address"), rs.getString("Phone"), rs.getString("Product"), rs.getInt("Price"), rs.getDate("DeliveryDate"), rs.getString("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return od;
    }

    public Account GetAccount(int id) {
        String sql = "Select * From Account where Account_ID=?";
        Account acc = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account(rs.getInt("Account_ID"), rs.getString("avatar"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getDate("birthday"), rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public int DeleteAccount(int id) {
        String sql = "delete from Account where Account_ID=?";
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
        String sql = "update Account set avatar=?, username=?, password=?, email=?, firstName=?, lastName=?, gender=?, birthday=?, role=? where Account_ID=?";
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
        String sql = "update OrderList set Date=?, Address=?, Phone=?, Product=?, Price=?, DeliveryDate=?, Status=? where ID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, (Date) newod.getDate());
            ps.setString(2, newod.getAddress());
            ps.setString(3, newod.getPhone());
            ps.setString(4, newod.getProduct());
            ps.setInt(5, newod.getPrice());
            ps.setDate(6, (Date) newod.getDeliveryDate());
            ps.setString(7, newod.getStatus());
            ps.setInt(8, newod.getID());
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
            ps.setString(3, acc.getPassword());
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

    public ResultSet GetAllProduct() throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            String sql = "Select * From Product";
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
            PreparedStatement ps = conn.prepareStatement("delete from Product where ID=?");
            ps.setInt(1, ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int AddNewProduct(Product pro) {
        String sql = "insert into Product values(?, ?, ?, ?, ?, ?, ?)";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pro.getID());
            ps.setString(2, pro.getProductName());
            ps.setString(3, pro.getBrand());
            ps.setString(4, pro.getColor());
            ps.setInt(5, pro.getSize());
            ps.setInt(6, pro.getQuantity());
            ps.setInt(7, pro.getPrice());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateProduct(Product pro) {
        String sql = "update Product set ProductName=?, Brand=?, Color=?, Size=?, Quantity=?, Price=? where ID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pro.getProductName());
            ps.setString(2, pro.getBrand());
            ps.setString(3, pro.getColor());
            ps.setInt(4, pro.getSize());
            ps.setInt(5, pro.getQuantity());
            ps.setInt(6, pro.getPrice());
            ps.setInt(7, pro.getID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public Product GetProduct(int ID) {
        String sql = "Select * From Product where ID=?";
        Product pr = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pr = new Product(rs.getInt("ID"), rs.getString("ProductName"), rs.getString("Brand"), rs.getString("Color"), rs.getInt("Size"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getString("Picture"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }

    public ResultSet GetImportSource() throws ClassNotFoundException {
        ResultSet rs = null;
        try {
            String sql = "Select * From ImportSource";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int DeleteImportSource(int ID) {
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from ImportSource where ID=?");
            ps.setInt(1, ID);
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int AddNewImportSource(ImportSource ip) {
        String sql = "insert into ImportSource values(?, ?, ?, ?, ?, ?, ?, ?)";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ip.getID());
            ps.setString(2, ip.getProductName());
            ps.setString(3, ip.getBrand());
            ps.setString(4, ip.getColor());
            ps.setInt(5, ip.getSize());
            ps.setInt(6, ip.getQuantity());
            ps.setInt(7, ip.getPrice());
            ps.setDate(8, (Date) ip.getDateBuy());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public int UpdateImportSource(ImportSource ip) {
        String sql = "update ImportSource set ProductName=?, Brand=?, Color=?, Size=?, Quantity=?, Price=?, DateBuy=? where ID=?";
        int ketqua = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ip.getProductName());
            ps.setString(2, ip.getBrand());
            ps.setString(3, ip.getColor());
            ps.setInt(4, ip.getSize());
            ps.setInt(5, ip.getQuantity());
            ps.setInt(6, ip.getPrice());
            ps.setDate(7, (Date) ip.getDateBuy());
            ps.setInt(8, ip.getID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

    public ImportSource GetImportSource(int ID) {
        String sql = "Select * From ImportSource where ID=?";
        ImportSource ip = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ip = new ImportSource(rs.getInt("ID"), rs.getString("ProductName"), rs.getString("Brand"), rs.getString("Color"), rs.getInt("Size"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getDate("DateBuy"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAOs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }
}
