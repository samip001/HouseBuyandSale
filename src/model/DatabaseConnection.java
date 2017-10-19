package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    Connection conn;
    private static DatabaseConnection db = new DatabaseConnection();

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstanceofDB() {
        return db;
    }

    public Connection connectDB() {
        String driver;
        String url;
        String user;
        String password;
        try {
            //driver class
            driver = "org.gjt.mm.mysql.Driver";
            //Database locationa and name
            url = "jdbc:mysql://localhost:3306/housemanagement"; 
            //database admin name
            user = "root";
            //password for admin 
            password = "";
            //loading  driver for databse connectivity
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("Exception " + ex.getMessage());
        }
        return conn;
    }

    public void disconnect(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Exception in disconnecting " + ex.getMessage());
            }
        }

    }

}
