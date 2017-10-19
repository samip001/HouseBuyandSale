/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.FollowedUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samip
 */
public class FollowedUserBLL {
    
     DatabaseConnection db;
    
    public FollowedUserBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    //after accepting request this query runs
    public  void startFollowedUser(FollowedUser fu) {
       Connection conn;
       conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO followed_user (username, following_username, followed_date,permission) VALUES (?,?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1,fu.getUsername());
                statement.setString(2, fu.getFollowingUsername());
                statement.setDate(3, (Date) fu.getFollowedDate());
                statement.setString(4, fu.getPermission());
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
         
    }
    
    //query is tricky and  followinguser is logged in user and username is selected from table
    public void unFollowUser(String username,String followingUser){
        Connection conn;
       conn = db.connectDB();
            PreparedStatement statement;
            String sql = "DELETE FROM followed_user WHERE username=? AND following_username=?";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1,followingUser);
                statement.setString(2, username);
                   //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
    }
    
    //show list of user that are accepted and show in table
    public ResultSet getUserFollwedList(String user){
        Connection conn;
         conn = db.connectDB();
        String sql = "SELECT * FROM followed_user WHERE username=? ";
        PreparedStatement stat;
        ResultSet rs=null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
    }
    
    //chnage permission of followed User 
    public void updateUserPermission(String permission,String user ,String followinguser) {
        Connection conn;
        conn = db.connectDB();
        PreparedStatement statement;
        String sql = "UPDATE followed_user SET permission=? WHERE username=? AND following_username=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, permission);
            statement.setString(2, user);
            statement.setString(3, followinguser);
            //throws false
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FollowedUserBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }
    
    //used to show profile of user that are followed by user
    public ResultSet getFollowingUserList(String user){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT fu.username,fu.followed_date,ud.profile_name "
                + "FROM followed_user AS fu ,user_description AS ud "
                + "WHERE fu.username= ud.username AND fu.following_username=? ";
        PreparedStatement stat;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return rs;
    }
    
    //get user permiision for viwing profile
    public String getFollowedUserPermission(String meUser,String owneruser){
        Connection conn;
        String permission = null;
        conn = db.connectDB();
        String sql = "SELECT permission FROM followed_user WHERE following_username=? AND username=? ";

        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, meUser);
            stat.setString(2, owneruser);
            rs = stat.executeQuery();
            while (rs.next()) {                
                permission=rs.getString("permission");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);

        return permission;
    }
    
}
