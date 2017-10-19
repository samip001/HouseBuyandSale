/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.NotificationUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author samip
 */
public class NotificationBLL {
    DatabaseConnection db;
    
    public NotificationBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    
    //insert into notification fromuser action automatically
    public void sendNotification(NotificationUser nfu){
        Connection conn;
            conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO notification_user ( touser, notification_type, fromuser, details, status) "
                    + "         VALUES (?,?,?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1, nfu.getTouser());
                statement.setInt(2, nfu.getNotificationType());
                statement.setString(3,nfu.getFromuser());
                statement.setString(4,nfu.getDetails());
                statement.setString(5, nfu.getStatus());
                
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
         
     }
    
    //update unseen to seen notification status
    public void updateUnseenToSeenNotifcations(String user){
          Connection conn;
          conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE notification_user SET status=? where touser=? ";
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
    
    //list all the notificatio that are received by logged in user
    public ResultSet getAllNotificationList(String user) {
        Connection conn;
       conn = db.connectDB();
        String sql = " SELECT nu.fromuser,nu.details,nu.status,ud.profile_name "
                + "FROM notification_user as nu,user_description as ud "
                + "WHERE nu.fromuser=ud.username and nu.touser=? ORDER BY status DESC";
        PreparedStatement stat;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user);
            rs = stat.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
       }
    
    //show number in landing if new unseennotifications are found
    public int getNewNotificatiosNumber(String user){
         int i=0;
         Connection conn;
         conn = db.connectDB();
        String sql = "SELECT COUNT(touser) as newnotification_number FROM notification_user where touser=? and status =? ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            stat.setString(2, "Unseen");
            rs = stat.executeQuery();

            while (rs.next()) {
                i=rs.getInt("newnotification_number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return i;
      }
}
