/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Validation;
import data.Booked;
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
public class BookedBLL {
    
    DatabaseConnection db;
    
    public BookedBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    
    // used in booking room for user
    public void bookedHouse(Booked bl){
        Connection conn;
         conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO booked_list (postownername,bookedusername, house_type_name, house_id, request_date,house_end_date, actual_price, "
                    + "commision_price, total_cost, request_status,user_status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            try {
                statement = conn.prepareStatement(sql);
                statement.setString(1, bl.getPostownername());
                statement.setString(2, bl.getBookedusername());
                statement.setString(3, bl.getHouseTypeName());
                statement.setInt(4, bl.getHouseId());
                statement.setDate(5, (Date) bl.getRequestDate());
                statement.setDate(6, (Date) bl.getHouseEndDate());
                statement.setFloat(7, bl.getActualPrice());
                statement.setFloat(8, bl.getCommisionPrice());
                statement.setFloat(9, bl.getTotalCost());
                statement.setString(10, bl.getRequestStatus());
                statement.setString(11, bl.getUserStatus());
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                 Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
            }     
            db.disconnect(conn);
    }
         
    //end date of booked House
     public int getEndDateValidofBookedHouse(String user){
         Connection conn;
         conn = db.connectDB();
         String sql ="SELECT house_end_date FROM booked_list WHERE bookedusername=? and user_status=? ";
         int day=0;
         
         PreparedStatement stat;
        ResultSet rs;
        String enddate=null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user);
            stat.setString(2, "PRESENT");
            rs = stat.executeQuery();
            while(rs.next()){
                enddate=rs.getDate("house_end_date").toString();
            }
            day= new Validation().getDateDifferenceinDay(enddate);
            
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return day;
         
     }
     
     //delete if  user do not responds it after 5 days after recieving book request
     public void deleteConfirmBookRequestForTimeExceeds(String user,String housetype,int houseid){
        Connection conn; 
        conn = db.connectDB();
       PreparedStatement statement;
       String sql = "DELETE FROM booked_list WHERE bookedusername=? AND house_id=? AND house_type_name=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, user);
           statement.setInt(2, houseid);
           statement.setString(3, housetype);
           //throws false
           statement.execute();
       } catch (SQLException ex) {
           Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
       }
       db.disconnect(conn);
     }
     
      //delete if  user do not responds it after 5 days after sendng book request
     public void deleteBookRequestofUserTimeExceeds(String user,String housetype,int houseid){
        Connection conn; 
        conn = db.connectDB();
       PreparedStatement statement;
       String sql = "DELETE FROM booked_list WHERE postownername=? AND house_id=? AND house_type_name=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, user);
           statement.setInt(2, houseid);
           statement.setString(3, housetype);
           //throws false
           statement.execute();
       } catch (SQLException ex) {
           Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
       }
       db.disconnect(conn);
     }
     
     //update status if that was booked past
     public void updateBookedListUserStatus(int houseId,String housetype,String user){
       Connection conn;
         conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE booked_list SET user_status=? "
               + "WHERE house_id=? AND house_type_name=? AND bookedusername=? AND user_status=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, "PAST");
           statement.setInt(2, houseId);
           statement.setString(3, housetype);
           statement.setString(4, user);
           statement.setString(5, "UNKNOWN");
           
           //throws false
           statement.execute();
       } catch (SQLException ex) {
           Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
       }
       db.disconnect(conn);
         
     }
     
     // update request status booked for house 
     public void updateBookedListrequestStatus(int houseId,String housetype,String user){
         Connection conn;
         conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE booked_list SET request_status=? "
               + "WHERE house_id=? AND house_type_name=? AND bookedusername=? AND user_status=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, "Booked");
           statement.setInt(2, houseId);
           statement.setString(3, housetype);
           statement.setString(4, user);
           statement.setString(5, "PRESENT");
           
           //throws false
           statement.execute();
       } catch (SQLException ex) {
           Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
       }
       db.disconnect(conn);
         
     }
     
     //send user name in confirm booking
     public void confirmBookingfromRequestedUser(Booked bl){
        Connection conn;
       conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE booked_list SET booked_date=?,request_status=? ,user_status=? "
               + "WHERE house_id=? AND house_type_name=? AND bookedusername=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setDate(1, (Date) bl.getBookedDate());
           statement.setString(2, bl.getRequestStatus());
           statement.setString(3, bl.getUserStatus());
           statement.setInt(4, bl.getHouseId());
           statement.setString(5, bl.getHouseTypeName());
           statement.setString(6, bl.getBookedusername());
           
           
           //throws false
           statement.execute();
       } catch (SQLException ex) {
           Logger.getLogger(BookedBLL.class.getName()).log(Level.SEVERE, null, ex);
       }
       db.disconnect(conn);
     }

     //show list of booked Information of user that are boooked by login user
     public ResultSet getBookedInformatioon(String user,String requeststatus){
         Connection conn;
         conn = db.connectDB();
        String sql = "SELECT * FROM booked_list WHERE bookedusername=? AND request_status=?";
        PreparedStatement stat;
        ResultSet rs=null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            stat.setString(2, requeststatus);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
         
     }
     
     //shoow details in table who send request to confirm book request
     public ResultSet getBookedRequestInformatioon(String user,String requeststatus){
         Connection conn;
         conn = db.connectDB();
        String sql = "SELECT * FROM booked_list WHERE postownername=? AND request_status=?";
        PreparedStatement stat;
        ResultSet rs=null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,user);
            stat.setString(2, requeststatus);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
     }
     
     //to show Owner username and image which are booked
    public ResultSet getBookedPostOwnerUsername(int houseid){
        Connection conn;
         conn = db.connectDB();
        String sql = "SELECT bl.postownername,ud.profile_name FROM booked_list as bl, user_description as ud "
                + "WHERE bl.postownername=ud.username AND bl.house_id=? ";
        PreparedStatement stat;
        ResultSet rs=null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setInt(1, houseid);
            rs = stat.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
         return rs;
     }
    
    //used while selecting user 
    public boolean chekUserselectedBefore(String user,String housetype,int houseid,String requeststatus){
        Connection conn;
         conn = db.connectDB();
         String sql ="SELECT * FROM booked_list "
                 + "WHERE postownername=? AND house_type_name=? AND house_id=? AND request_status=? ";
         boolean found=false;
         PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user);
            stat.setString(2, housetype);
            stat.setInt(3, houseid);
            stat.setString(4, requeststatus);
            rs = stat.executeQuery();
            while(rs.next()){
                found=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
        return found;
    }
  
    //get user status busy or active
    public int getUserStatus(String otherusername){
        Connection conn;
         conn = db.connectDB();
         String sql ="SELECT house_end_date FROM booked_list WHERE bookedusername=? and user_status=? ";
         int day=0;
         
         PreparedStatement stat;
        ResultSet rs;
        String enddate;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, otherusername);
            stat.setString(2, "PRESENT");
            rs = stat.executeQuery();
            while(rs.next()){
                enddate=rs.getDate("house_end_date").toString();
                 day= new Validation().getDateDifferenceinDay(enddate);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
        return day;
     }
}
