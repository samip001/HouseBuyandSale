/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.InterestedUser;
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
public class InterestedUserBLL {

    DatabaseConnection db;

    public InterestedUserBLL() {
        db = DatabaseConnection.getInstanceofDB();
    }
    //insert into when user shows interest in any house type
    public void userInterestedInHouse(InterestedUser iu) {
        Connection conn;
        conn = db.connectDB();
        PreparedStatement statement;
        String sql = "INSERT INTO interested_user (username, house_id, house_type_name, interested_date) "
                + "VALUES (?,?,?,?)";
        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, iu.getUsername());
            statement.setInt(2, iu.getHouseId());
            statement.setString(3, iu.getHouseTypeName());
            statement.setDate(4, (Date) iu.getInterestedDate());

            //throws false
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        db.disconnect(conn);
    }
    
    //cancel interest from previously interested house
    public void cancelUserInterestedInHouse(String user,int house_id, String housetype) {
        Connection conn;
        conn = db.connectDB();
        PreparedStatement statement;
        String sql = "DELETE FROM interested_user WHERE username=? AND house_id=? and house_type_name=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setInt(2, house_id);
            statement.setString(3, housetype);
            //throws false
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(InterestedUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }

    //check if user had shown interst in house
    public boolean checkUserPrevoiusIntrested(String user, String houstype, int id) {
        Connection conn;
        conn = db.connectDB();
        boolean found = false;

        String sql = "SELECT * FROM interested_user where username=? AND house_id=? AND house_type_name=?";

        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, user);
            stat.setInt(2, id);
            stat.setString(3, houstype);

            rs = stat.executeQuery();

            while (rs.next()) {
                found = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error jhfhjfhv" + ex.getMessage());
        }
        return found;
    }

    //show list of user intersted in Hoouse Buyiings
    public ResultSet getUserInterestedinHouseType(int houseid, String housetype) {
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT * FROM interested_user WHERE house_id=? and house_type_name=?";

        PreparedStatement statement;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            //set values
            statement.setInt(1, houseid);
            statement.setString(2, housetype);
            //execute query
            rs = statement.executeQuery();
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return rs;
    }

    //get house list from house where they had shown interest
    public ResultSet listUserInterstInHouse(String user, String housetype) {
        Connection conn;
        conn = db.connectDB();
        String sql = null;
        switch (housetype) {
            case "Room":
                sql = "SELECT r.room_id,r.username,r.posted_date,r.end_date,riu.interested_date,riu.house_type_name,"
                        + "r.status,r.location,r.visibility,ud.profile_name "
                        + "FROM interested_user AS riu,room AS r,user_description AS ud "
                        + "WHERE riu.house_id=r.room_id AND riu.username=ud.username"
                        + " AND riu.house_type_name='Room'  AND riu.username=?";
                break;
            case "House":
                sql = "SELECT h.house_id,h.username,h.posted_date,h.end_date,riu.interested_date,riu.house_type_name,"
                        + "h.status,h.location,h.visibility,ud.profile_name FROM interested_user AS riu,house AS h,user_description AS ud "
                        + "WHERE riu.house_id=h.house_id AND riu.username=ud.username "
                        + "AND riu.house_type_name='House' AND riu.username=?";
                break;
            case "Apartment":
                sql = "SELECT a.apartment_id,a.username,a.posted_date,a.end_date,riu.interested_date,riu.house_type_name,"
                        + "a.status,a.location,a.visibility,ud.profile_name "
                        + "FROM interested_user AS riu,apartment AS a,user_description AS ud "
                        + "WHERE riu.house_id=a.apartment_id AND riu.username=ud.username "
                        + "AND riu.house_type_name='Apartment' AND riu.username=? ";
                break;
            default:
                break;
        }
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

    // get user interested number
    public int getNumberofRoomInterestUser(int roomid) {
        int i = 0;
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT COUNT(house_id) as number from interested_user Where house_id=? AND house_type_name='Room'; ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setInt(1, roomid);
            rs = stat.executeQuery();

            while (rs.next()) {
                i = rs.getInt("number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return i;
    }

    public int getNumberofHouseInterestUser(int houseid) {
        int i = 0;
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT COUNT(house_id) as number from interested_user Where house_id=? AND house_type_name='House'; ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setInt(1, houseid);
            rs = stat.executeQuery();

            while (rs.next()) {
                i = rs.getInt("number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return i;
    }

    public int getNumberofApartmentInterestUser(int apartmentid) {
        Connection conn;
        int i = 0;
        conn = db.connectDB();
        String sql = "SELECT COUNT(house_id) as number from interested_user Where house_id=? AND house_type_name='Apartment'; ";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            stat.setInt(1, apartmentid);
            rs = stat.executeQuery();

            while (rs.next()) {
                i = rs.getInt("number");
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return i;
    }

}
