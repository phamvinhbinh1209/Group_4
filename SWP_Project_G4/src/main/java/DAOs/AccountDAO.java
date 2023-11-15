/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Database.DBConnection;
import Models.Account;
import Models.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {

    private Connection conn = null;
    static PreparedStatement statement;

    public AccountDAO() throws Exception {
        conn = DBConnection.Connect();
    }

    public boolean isUserExist(String username) {
        boolean ok = false;
        try {
            statement = conn.prepareStatement("select username from Account where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    ok = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public int checkUserExit(String username, String password) {
        int status = -1;
        try {
            statement = conn.prepareStatement("select [password] from [Account] where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // not exist
            if (!resultSet.next()) {
                status = 1;
                // System.out.println("Do not exist username");
            } else {
                String pw = resultSet.getString("password");

                if (pw.equals(password)) {
                    // correct
                    status = 0;
                    // System.out.println("Login success");
                } else {
                    // not correct password
                    status = 2;
                    System.out.println("Username or password is incorrect");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return resutl
        return status;
    }
    public boolean isEmailExist(String email) {
        boolean ok = false;
        try {
            statement = conn.prepareStatement("select email from Account where email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("email").equals(email)) {
                    ok = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public void signUp(String avatar,
            String username,
            String password,
            String email,
            String firstName,
            String lastName,
            String gender,
            Date birthday,
            int role) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            statement = conn.prepareStatement("INSERT INTO Account VALUES (?,?,?,?,?,?,?,?,?)");

            statement.setString(1, avatar);
            statement.setString(2, username);
            statement.setString(3, Service.MD5.getMd5(password));
            statement.setString(4, email);
            statement.setString(5, firstName);
            statement.setString(6, lastName);
            statement.setString(7, gender);
            statement.setString(8, dateFormat.format(birthday));
            statement.setInt(9, role);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
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
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    public Account GetAccountUser(String username) {
        String sql = "select * from Account where username=?";
        Account acc = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username); // lay gia tri id va the vao 1 dau cham hoi
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                acc = new Account(rs.getInt("AccountID"), rs.getString("avatar"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getDate("birthday"), rs.getInt("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
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
            ps.setDate(8, (java.sql.Date) (Date) newacc.getBirthday());
            ps.setInt(9, newacc.getRole());
            ps.setInt(10, newacc.getAccountID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }

  public int checkRole(String username, int role) {
        int status = -1;
        try {
            statement = conn.prepareStatement("select role from [Account] where username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if(!resultSet.next()) {
                status = 1;
            } else {
                int r = resultSet.getInt("role");
                if (r == role) {
                    status = 0;
//                    System.out.println("customer");
                } else {
                    status = 2;
//                    System.out.println("admin");
                }
            }
               
        } catch (Exception e) {
        }
        return status;
    }
}
