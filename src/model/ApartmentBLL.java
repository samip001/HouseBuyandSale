/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Apartment;
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
public class ApartmentBLL {
    DatabaseConnection db;
    
    public ApartmentBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    //post about apartment by  any user
    public void postApartmentDetails(Apartment  apartment){
        Connection conn;
        conn = db.connectDB();
        PreparedStatement statement;
        String sql = "INSERT INTO apartment ( username, user_type_id, searching, apartment_type_id, apartment_name, "
                + "location, price, posted_date, end_date, paid_type, apratment_description, status, visibility) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, apartment.getUsername());
            statement.setInt(2, apartment.getUserTypeId());
            statement.setString(3, apartment.getSearching());
            statement.setInt(4, apartment.getApartmentTypeId());
            statement.setString(5, apartment.getApartmentName());
            statement.setString(6, apartment.getLocation());
            statement.setFloat(7, apartment.getPrice());
            statement.setDate(8, (java.sql.Date) apartment.getPostedDate());
            statement.setDate(9, (java.sql.Date) apartment.getEndDate());
            statement.setString(10, apartment.getPaidType());
            statement.setString(11, apartment.getApratmentDescription());
            statement.setString(12, apartment.getStatus());
            statement.setString(13, apartment.getVisibility());

            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
      }
    
    //updates apartment details
    public void updateApartmentDetails(Apartment apartment){
        Connection conn;
         conn = db.connectDB();
       PreparedStatement statement;
       String sql = "UPDATE apartment SET apartment_type_id=?,location=?,apartment_name=?,end_date=?,"
               + "price=?,paid_type=?,apratment_description=?,status=? ,visibility=?"
               + "WHERE apartment_id=?";
       try {
           statement = conn.prepareStatement(sql);
           statement.setInt(1, apartment.getApartmentTypeId());
           statement.setString(2, apartment.getLocation());
           statement.setString(3, apartment.getApartmentName());
           statement.setDate(4, (Date) apartment.getEndDate());
           statement.setFloat(5, apartment.getPrice());
           statement.setString(6, apartment.getPaidType());
           statement.setString(7, apartment.getApratmentDescription());
           statement.setString(8, apartment.getStatus());
           statement.setString(9, apartment.getVisibility());
           statement.setInt(10, apartment.getApartmentId());
        //throws false
           statement.execute();
       } catch (SQLException ex) {
           System.out.println("Error " + ex.getMessage());
       }
       db.disconnect(conn);
    }
    
    //return number of apartment posted
    public int getPosetdApartmentNumber(String username){
        Connection conn;
         int i=0;
         conn = db.connectDB();
        String sql = "SELECT COUNT(username) as number from apartment Where username=?; ";
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
   
    //return apartment posted list with details
    public ResultSet postedApartmentList(String username){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT * FROM apartment WHERE username=?;";
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
    
    //return true or false after checking apartment booked or not
    public String getStatus(int houseid){
        Connection conn;
        String result=null;
        conn = db.connectDB();
        String sql = "SELECT status from apartment Where apartment_id=?; ";
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
    
    //apartment details to users
    public ResultSet getApartmentDetails(int apartmentid){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT a.username,a.apartment_name,att.apartment_type_name,ut.user_type_name,a.searching,"
                + "a.location,a.posted_date,a.end_date,a.price,a.paid_type,a.apratment_description,a.status,a.visibility "
                + "FROM apartment AS a,user_type AS ut,apartment_type AS att "
                + "WHERE a.user_type_id=ut.user_type_id AND a.apartment_type_id = att.apartment_type_id AND a.apartment_id=?";
        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
           //set values
            statement.setInt(1, apartmentid);
           
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }
    
    //for if end date time finish
    public ResultSet getPostedApartmentEndDate(String user){
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT apartment_id,end_date FROM apartment WHERE username=?";
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
    
    //updates only apartment status
     public void updateApartmentStatus(String status,int apartmentid){
         Connection conn;
        conn = db.connectDB();
            PreparedStatement statement;
            String sql = "UPDATE apartment SET status=? WHERE apartment_id=? ";
            try { 
                statement = conn.prepareStatement(sql);
                statement.setString(1, status);
                statement.setInt(2, apartmentid);
                 //throws false
                statement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(ApartmentBLL.class.getName()).log(Level.SEVERE, null, ex);
            }     
            db.disconnect(conn);
    }
     
     //lreturn all the searched apartment 
     public ResultSet showSearchedApartment(String location){
        Connection conn;
         conn = db.connectDB();
        String sql ="SELECT r.apartment_id,r.username,r.searching,r.location,r.posted_date,r.end_date,r.status,r.visibility,ut.profile_name "
                + "FROM apartment as r,user_description as ut "
                + "WHERE r.username = ut.username AND r.visibility=? AND r.location LIKE ? ";
        
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
