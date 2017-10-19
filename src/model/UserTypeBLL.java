/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samip
 */
public class UserTypeBLL {
    DatabaseConnection db;
    
    public UserTypeBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    
    public List<String> listUserType(){
        List<String> userType=new ArrayList<>();
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT user_type_name FROM user_type; ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            
            while(rs.next()){
                userType.add(rs.getString("user_type_name"));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    return userType;
    }
}
