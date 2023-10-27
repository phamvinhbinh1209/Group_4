package DAOs;

import Models.Account;
import Models.Order;
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
        conn = Database.DBConnection.connect();
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
                acc = new Account(rs.getInt("Account_ID"),rs.getString("avatar"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getDate("birthday"), rs.getInt("role"));
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
}
