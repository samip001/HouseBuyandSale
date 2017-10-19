/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Routing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author samip
 */
public class FolllowRequestBLL {
    DatabaseConnection db;
    
    public FolllowRequestBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    //requested to follow
     public void requestedTofollowOtherUser(String username,String requestedUser){
         Connection conn;
         conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO follow_request (username, requested_user,activity ,status) VALUES (?,?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1,requestedUser);
                statement.setString(2, username);
                statement.setString(3, "Requested");
                statement.setString(4, "Unseen");
                
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
         
     }
     
     //checks whether the request is accepted or not
     public String checkPreviousFollowActivity(String username,String choosenuser){
         Connection conn;
         conn = db.connectDB();
        String sql;
        sql = "SELECT activity FROM follow_request WHERE requested_user=? AND username=?";
        String status = null;
        PreparedStatement statement;
        ResultSet rs;
        try {
            statement = conn.prepareStatement(sql);
            //set values
            statement.setString(1, username);
            statement.setString(2,choosenuser);
            //execute query
            rs = statement.executeQuery();

            while (rs.next()) {
               status = rs.getString("activity");
            }
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return status;
     }
     
     //used in landing page for getting to new request number
     public int getNewFollowRequestNumber(String user){
         Connection conn;
          int i=0;
         conn = db.connectDB();
        String sql = "SELECT COUNT(username) as newfollowrequest_number FROM follow_request where username=? and activity =? ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            stat.setString(2, "Requested");
            rs = stat.executeQuery();

            while (rs.next()) {
                i=rs.getInt("newfollowrequest_number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return i;
      }
     
     //used in showing all the list in frame
    public ResultSet listFollowRequestedUser(String user) {
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT fr.requested_user,fr.status,ud.profile_name "
                + "FROM follow_request as fr, user_description as ud "
                + "WHERE fr.requested_user= ud.username and fr.username=? and fr.activity=? ";
        PreparedStatement stat;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user);
            stat.setString(2, "Requested");
            rs = stat.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
    }
    
    //request accepted 
    public void followRequestAccepted(String name){
        Connection conn;
         conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE follow_request SET activity=? WHERE username=?  and requested_user=?";
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, "Accepted");
                statement.setString(2, Routing.USERNAME);
                statement.setString(3, name);
                
                //throws false
                  statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
     }
    
    //request rejected
     public void followRequestRejected(String name){
         Connection conn;
         conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE follow_request SET activity=? WHERE username=? and requested_user=?";
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, "Rejected");
                statement.setString(2, Routing.USERNAME);
                statement.setString(3, name);
            
                //throws false
                  statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
     }
     
     //updates status from unseen to seen
     public void updateUnseenToSeenFollowRequest(String user){
         Connection conn;
          conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE follow_request SET status=? WHERE username=? ";
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, "Seen");
                statement.setString(2, user);
            
                //throws false
                  statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
      }
     
}
