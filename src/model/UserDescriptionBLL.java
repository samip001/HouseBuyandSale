/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.UserDescription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author samip
 */
public class UserDescriptionBLL {
    
     DatabaseConnection db;
    
    public UserDescriptionBLL(){ db =DatabaseConnection.getInstanceofDB(); }
    
    public void addProfileDescription(UserDescription ud){
        Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO user_description (username, profile_name, profile_description) "
                    + "         VALUES (?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1,ud.getUsername().getUsername());
                statement.setString(2, ud.getProfileName());
                statement.setString(3, ud.getProfileDescription());
                
                //throws false
                 statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
    }
    
    public void updateProfileDescription(UserDescription userdesc){
         Connection conn;
         conn = db.connectDB();
         PreparedStatement statement;
         String sql = "UPDATE user_description SET profile_name=?,profile_description=? WHERE username=?";
         try {
             statement = conn.prepareStatement(sql);
             statement.setString(1, userdesc.getProfileName());
             statement.setString(2, userdesc.getProfileDescription());
             statement.setString(3, userdesc.getProfileName());

             //throws false
             statement.execute();
         } catch (SQLException ex) {
             System.out.println("Error " + ex.getMessage());
         }
         db.disconnect(conn);
   }
    
      public ResultSet getUserDescription(String username){
       Connection conn = db.connectDB();
        String sql = "SELECT * FROM user_description WHERE username=? ";
        PreparedStatement stat;
        ResultSet rs =null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,username);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return  rs;
    }
    
     public String getProfileName(String username){
        String profileName=null;
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT profile_name FROM user_description WHERE username=? ";
        PreparedStatement stat;
        ResultSet rs;
        
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,username);
            rs = stat.executeQuery();

            while (rs.next()) { profileName =rs.getString("profile_name"); }
           
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
        return profileName;
    }
     
     
    
}
