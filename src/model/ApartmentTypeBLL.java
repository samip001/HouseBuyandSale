/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samip
 */
public class ApartmentTypeBLL {
    DatabaseConnection db;
    
    public ApartmentTypeBLL(){
        db =DatabaseConnection.getInstanceofDB();
    }
    
      public List<String> listApartmentType(){
        List<String> apartmentType=new ArrayList<>();
        Connection conn;
        conn = db.connectDB();
        String sql = "SELECT apartment_type_name FROM apartment_type";
        PreparedStatement stat;
        ResultSet rs;
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            
            while(rs.next()){
                apartmentType.add(rs.getString("apartment_type_name"));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    return apartmentType;
    }
    
}
