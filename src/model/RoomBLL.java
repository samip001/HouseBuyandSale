/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Room;
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
public class RoomBLL {
    DatabaseConnection db;
    
    public RoomBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    //insert into room when new room details are posted
    public void postRoomDetails(Room room){
        Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO room ( username, user_type_id, searching, location, room_no, posted_date, "
                    + "     end_date, price, paid_type, room_description, status, visibility) "
                    + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1, room.getUsername());
                statement.setInt(2, room.getUserTypeId());
                statement.setString(3, room.getSearching());
                statement.setString(4, room.getRoomLocation());
                statement.setInt(5, room.getRoomNo());
                statement.setDate(6, (java.sql.Date) room.getPostedDate());
                statement.setDate(7, (java.sql.Date) room.getEndDate());
                statement.setFloat(8, room.getPrice());
                statement.setString(9, room.getPaidType());
                statement.setString(10, room.getRoomDescription());
                statement.setString(11, room.getRoomStatus());
                statement.setString(12, room.getRoomVisibility());
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
      }
    
    //update room details
    public void updateRoomDetails(Room room) {
        Connection conn;
       conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE room set location=?,room_no=?,end_date=?,price=?,paid_type=?,room_description=?,status=?,visibility=? "
               + "WHERE room_id=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, room.getRoomLocation());
          statement.setInt(2, room.getRoomNo());
          statement.setDate(3, (Date) room.getEndDate());
          statement.setFloat(4, room.getPrice());
          statement.setString(5, room.getPaidType());
          statement.setString(6, room.getRoomDescription());
          statement.setString(7, room.getRoomStatus());
          statement.setString(8, room.getRoomVisibility());
          statement.setInt(9, room.getRoomId());

           //throws false
           statement.execute();
       } catch (SQLException ex) {
           System.out.println("Error " + ex.getMessage());
       }
       db.disconnect(conn);
    }
    
    //get end date time finish
    public ResultSet getPostedRoomEndDate(String user){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT room_id,end_date FROM room WHERE username=?";
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
    
    //to show user posted number on romm from selected user
    public int getPosetdRoomNumber(String username){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT COUNT(username) as number from room Where username=?; ";
        PreparedStatement stat;
        ResultSet rs;
        int i=0;
         
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,username);
            
            rs = stat.executeQuery();

            while (rs.next()) {
                i=rs.getInt("number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return i;
     }
    
    //used in listown posted Room List for Managing
    public ResultSet postedRoomList(String username){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT * FROM room WHERE username=?;";
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
           //set values
            statement.setString(1, username);
           
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }
    //return true or false after checking apartment booked or not
    public String getStatus(int houseid){
        Connection conn;
        String result=null;
        conn = db.connectDB();
        String sql = "SELECT status from room Where room_id=?; ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setInt(1,houseid);
            rs = stat.executeQuery();
            while (rs.next()) {
               result =rs.getString("status");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return result;
    }
    
     //return room details 
    public ResultSet getRoomDetails(int id){
       Connection conn;
        conn = db.connectDB();
        String sql = "SELECT r.username,ut.user_type_name,r.searching,r.location,r.room_no,r.price,r.posted_date,"
                + "r.end_date,r.paid_type,r.status,r.visibility,r.room_description "
                + "FROM room AS r,user_type AS ut "
                + "WHERE r.user_type_id=ut.user_type_id AND r.room_id=? ";
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
           //set values
            statement.setInt(1, id);
           
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }
   
    //updating room staus after booked
    public void updateRoomStatus(String status,int id){
        Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE room SET status=? WHERE room_id=? ";
            try { 
                statement = conn.prepareStatement(sql);
                statement.setString(1, status);
                statement.setInt(2, id);
                 //throws false
                statement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(RoomBLL.class.getName()).log(Level.SEVERE, null, ex);
            }     
            db.disconnect(conn);
    }
    
    //used when searching room type
    public ResultSet showSearchedRoom(String location){
        Connection conn;
        conn = db.connectDB();
        String sql ="SELECT r.room_id,r.username,r.searching,r.location,r.posted_date,r.end_date,r.status,r.visibility,ut.profile_name "
                + "FROM room as r,user_description as ut WHERE "
                + "r.username = ut.username AND r.visibility=? AND r.location LIKE ? ";
        
        PreparedStatement stat;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, "Public");
            stat.setString(2, location+"%");
            
            rs = stat.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
       }

}
