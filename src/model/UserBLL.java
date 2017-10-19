/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Routing;
import data.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samip
 */
public class UserBLL {
    
    DatabaseConnection db;
    
    public UserBLL(){
        db =DatabaseConnection.getInstanceofDB();
       
    }
    //checks same username user exist or not during resgistration
     public boolean checkRepeatedUsername(User user){
        Connection conn = db.connectDB();
        String sql = "SELECT * FROM user WHERE username=? ";
        boolean result =false;
        PreparedStatement statement;
        ResultSet rs;
        try {
            statement = conn.prepareStatement(sql);
            //set values
            statement.setString(1, user.getUsername());
            //execute query
            rs = statement.executeQuery();

            while (rs.next()) {
                result =true;
            }
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        db.disconnect(conn);
        return result;
     }
     
     //user registration
     public void saveUser(User user){
            Connection conn = db.connectDB();
         PreparedStatement statement;
         String sql = "INSERT INTO user (username, first_name, last_name, "
                 + "password, dob, contact_no,address, gender,visibility, terms_conditions) "
                 + "         VALUES (?,?,?,?,?,?,?,?,?,?);";
         try {
             statement = conn.prepareStatement(sql);

             statement.setString(1, user.getUsername());
             statement.setString(2, user.getFirstName());
             statement.setString(3, user.getLastName());
             statement.setString(4, user.getPassword());
             statement.setDate(5, (java.sql.Date) user.getDob());
             statement.setString(6, user.getContactNo());
             statement.setString(7, user.getAddress());
             statement.setString(8, user.getGender());
             statement.setString(9, user.getVisibility());
             statement.setString(10, user.getTermsConditions());

             statement.execute();
         } catch (SQLException ex) {
             System.out.println("Error " + ex.getMessage());
         }
         db.disconnect(conn);
     }
     
     //checks user nama nd password when user verification is used
     public boolean isValidUserandPassword(String username,String password){
        Connection conn = db.connectDB();
        String sql = "SELECT * FROM user WHERE username =? AND password = ?";
        PreparedStatement stat;
        ResultSet rs;
        boolean found=false;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            stat.setString(2, password);
            rs = stat.executeQuery();

            while (rs.next()) {
                found = true;
                Routing.USERNAME= username;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
        return found;
     }
     
     //update user profile
     public void updateProfileDetailse(User user){
       Connection conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE user set first_name=?,last_name=?,dob=?,contact_no=?,address=?,gender=?,visibility=? "
               + "WHERE username=?";
       try {
           statement = conn.prepareStatement(sql);
          statement.setString(1, user.getFirstName());
          statement.setString(2, user.getLastName());
          statement.setDate(3, (Date) user.getDob());
          statement.setString(4, user.getContactNo());
          statement.setString(5, user.getAddress());
          statement.setString(6, user.getGender());
          statement.setString(7,user.getVisibility());
          statement.setString(8, Routing.USERNAME);

           //throws false
           statement.execute();
       } catch (SQLException ex) {
           System.out.println("Error " + ex.getMessage());
       }
       db.disconnect(conn);
   }
     
     //return user profile details
     public ResultSet getUserProfileDetails(String username){
        Connection conn = db.connectDB();
        String sql = "SELECT * FROM user WHERE username=? ";
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
     
     // return all  user name while searching user 
     public List<User> getAllRegisteredUsername(String username){
        List<User> allusername=new ArrayList<>();
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT username FROM user where username <>? ;";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            rs = stat.executeQuery();
            
            while(rs.next()){
                User user =new User();
                user.setUsername(rs.getString("username"));
                allusername.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return allusername;
    }
     
     //used when searching User from search
     public ResultSet listAllSearchedUser(String username){
         Connection conn;
        conn = db.connectDB();
        String sql = "SELECT u.username,u.first_name,u.last_name,u.gender,u.visibility,ut.profile_name "
                + "FROM user as u,user_description as ut WHERE "
                + "u.username=ut.username AND u.visibility=? AND u.username LIKE ? ;";
        PreparedStatement stat;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, "Public");
            stat.setString(2, username+"%");
            rs = stat.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
       }
     
     //show user details from  list of user when Profile is clicked
     public ResultSet showMoreUserDetail(String username){
         Connection conn;
         conn = db.connectDB();
         String sql = "SELECT u.username,u.first_name,u.last_name,u.dob,u.address,"
                 + "ut.profile_name,ut.profile_description "
                 + "FROM user as u,user_description as ut WHERE "
                 + "u.username=ut.username AND u.username = ? ;";
         PreparedStatement stat;
         ResultSet rs = null;
         try {
             stat = conn.prepareStatement(sql);
             stat.setString(1, username);
             rs = stat.executeQuery();

         } catch (SQLException ex) {
             System.out.println("Error " + ex.getMessage());
         }
         return rs;
       }
     
     //return user visibility
     public String getUserVisibilityStatus(String username){
        String visibilty = null;
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT visibility FROM user WHERE username=? ";

        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, username);
            rs = stat.executeQuery();
            while (rs.next()) {
                visibilty = rs.getString("visibility");
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
        return visibilty;
    }
    
}
