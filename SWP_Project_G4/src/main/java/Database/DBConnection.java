/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DBConnection {
    public static Connection conn;
    public static PreparedStatement statement;

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(Config.SERVER);
        ds.setUser(Config.USER);
        ds.setPassword(Config.PASSWORD);
        ds.setPortNumber(Config.PORT);
        ds.setDatabaseName(Config.DATABASE_NAME);
        ds.setEncrypt(false);

        conn = ds.getConnection();
        return conn;
    }

    public static void disconnect() throws SQLException {
        conn.close();
    }
}
