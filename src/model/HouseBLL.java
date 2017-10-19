/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.House;
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
public class HouseBLL {
    DatabaseConnection db;
    
    public HouseBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    
    //used in inserting house details in system 
    public void postHouseDetails(House  house){
        Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "INSERT INTO house ( username, user_type_id, searching, house_no, location, house_for,"
                    + " posted_date, end_date, price, paid_type, house_description, status, visibility) "
                    + "VALUES (?, ?,?,?,?,?,?, ?, ?, ?,?,?, ?);";
            try {
                statement = conn.prepareStatement(sql);
                
                statement.setString(1, house.getUsername());
                statement.setInt(2, house.getUserTypeId());
                statement.setString(3, house.getSearching());
                statement.setString(4, house.getHouseNo());
                statement.setString(5, house.getLocation());
                statement.setString(6, house.getHouseFor());
                statement.setDate(7, (java.sql.Date) house.getPostedDate());
                statement.setDate(8, (java.sql.Date) house.getEndDate());
                statement.setFloat(9, house.getPrice());
                statement.setString(10, house.getPaidType());
                statement.setString(11, house.getHouseDescription());
                statement.setString(12, house.getStatus());
                statement.setString(13, house.getHouseVisibility());
                
                    //throws false
                    statement.execute();
            } catch (SQLException ex) {
                System.out.println("Error "+ ex.getMessage());
            }     
            db.disconnect(conn);
      }
    
    //get all the house list posted by logged in user
    public ResultSet postedHouseList(String username){
        Connection conn; 
        conn = db.connectDB();
        String sql = "SELECT * FROM house WHERE username=?;";
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
           //set values
            statement.setString(1,username);
           
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }
    
    //updates house details
    public void updateHouseDetails(House h){
        Connection conn;
        conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE house set location=?,house_no=?,house_for=?,end_date=?,price=?,paid_type=?,"
               + "house_description=?,status=? ,visibility=?"
               + "WHERE house_id=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setString(1, h.getLocation());
           statement.setString(2, h.getHouseNo());
           statement.setString(3, h.getHouseFor());
           statement.setDate(4, (Date) h.getEndDate());
           statement.setFloat(5, h.getPrice());
           statement.setString(6, h.getPaidType());
           statement.setString(7, h.getHouseDescription());
           statement.setString(8, h.getStatus());
           statement.setString(9, h.getHouseVisibility());
           statement.setInt(10, h.getHouseId());
        //throws false
           statement.execute();
       } catch (SQLException ex) {
           System.out.println("Error " + ex.getMessage());
       }
       db.disconnect(conn);
    }
    
    //get end date time finish and used fro calculation
    public ResultSet getPostedHouseEndDate(String user){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT house_id,end_date FROM house WHERE username=?";
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
    
    //get number of posted house from system by logged user
    public int getPosetdHouseNumber(String username){
        Connection conn;
         int i=0;
         conn = db.connectDB();
        String sql = "SELECT COUNT(username) as number from house Where username=?; ";
        PreparedStatement stat;
        ResultSet rs;
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
  
    //return house details 
    public ResultSet getHouseDetails(int houseid){
            Connection conn;
        conn = db.connectDB();
        String sql = "SELECT h.username,ut.user_type_name,h.searching,h.location,h.house_no,h.house_for,h.price,"
                + "h.posted_date,h.end_date,h.paid_type,h.status,h.house_description ,h.visibility "
                + "FROM house AS h,user_type AS ut "
                + "WHERE h.user_type_id=ut.user_type_id AND h.house_id=?";
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
           //set values
            statement.setInt(1, houseid);
           
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }
    
    //upates house status 
    public void updateHouseStatus(String status,int houseid){
        Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE house SET status=? WHERE house_id=? ";
            try { 
                statement = conn.prepareStatement(sql);
                statement.setString(1, status);
                statement.setInt(2, houseid);
                 //throws false
                statement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(HouseBLL.class.getName()).log(Level.SEVERE, null, ex);
            }     
            db.disconnect(conn);
    }
     
    //searhing house by user
    public ResultSet showSearchedHouse(String location){
        Connection conn;
        conn = db.connectDB();
        String sql ="SELECT r.house_id,r.username,r.searching,r.location,r.posted_date,r.end_date,r.status,r.visibility,ut.profile_name "
                + "FROM house as r,user_description as ut WHERE r.username = ut.username AND r.visibility=? AND r.location LIKE ? ";
        
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
