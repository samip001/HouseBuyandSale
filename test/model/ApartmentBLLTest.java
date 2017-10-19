/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Apartment;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samip
 */
public class ApartmentBLLTest {
    
    public ApartmentBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of postApartmentDetails method, of class ApartmentBLL.
     */
    @Test
    public void testPostApartmentDetails() {
        System.out.println("postApartmentDetails");
        Date today = java.sql.Date.valueOf("2017-09-25");
        Date enddate = java.sql.Date.valueOf("2017-10-25");
        
        Apartment apartment = new Apartment(null, "hello13", 2, "Apartment Buyer", 6, "test Apartment Name",
                "check location", 10000, today, enddate, "Weekly", "Checking Apartment Description", "Yearly", "Private");
        ApartmentBLL instance = new ApartmentBLL();
        instance.postApartmentDetails(apartment);
    }

    /**
     * Test of updateApartmentDetails method, of class ApartmentBLL.
     */
//    @Test
//    public void testUpdateApartmentDetails() {
//        System.out.println("updateApartmentDetails");
//        Apartment apartment = null;
//        ApartmentBLL instance = new ApartmentBLL();
//        instance.updateApartmentDetails(apartment);
//    }

    /**
     * Test of getPosetdApartmentNumber method, of class ApartmentBLL.
     */
    @Test
    public void testGetPosetdApartmentNumber() {
        System.out.println("getPosetdApartmentNumber");
        String username = "hello13";
        ApartmentBLL instance = new ApartmentBLL();
        int expResult = 4;
        int result = instance.getPosetdApartmentNumber(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of postedApartmentList method, of class ApartmentBLL.
     */
    @Test
    public void testPostedApartmentList() throws SQLException {
        System.out.println("postedApartmentList");
        String username = "hello13";
        ApartmentBLL instance = new ApartmentBLL();
        ResultSet result = instance.postedApartmentList(username);
        assertTrue(result.next());
    }

    /**
     * Test of getApartmentDetails method, of class ApartmentBLL.
     */
    @Test
    public void testGetApartmentDetails() throws SQLException {
        System.out.println("getApartmentDetails");
        int apartmentid = 2;
        ApartmentBLL instance = new ApartmentBLL();
        ResultSet result = instance.getApartmentDetails(apartmentid);
        assertTrue(result.next());
      
    }
     /**
     * Test of getPostedApartmentEndDate method, of class ApartmentBLL.
     */
    @Test
    public void testgetPostedApartmentEndDate() throws SQLException {
        System.out.println("getPostedApartmentEndDate");
        String username = "hello13";
        ApartmentBLL instance = new ApartmentBLL();
        ResultSet result = instance.getPostedApartmentEndDate(username);
        assertTrue(result.next());
    }
    

    /**
     * Test of updateApartmentStatus method, of class ApartmentBLL.
     */
    @Test
    public void testUpdateApartmentStatus() {
        System.out.println("updateApartmentStatus");
        String status = "Unavailable";
        int apartmentid = 2;
        ApartmentBLL instance = new ApartmentBLL();
        instance.updateApartmentStatus(status, apartmentid);
    }

    /**
     * Test of showSearchedApartment method, of class ApartmentBLL.
     * @throws java.sql.SQLException
     */
    @Test
    public void testShowSearchedApartment() throws SQLException {
        System.out.println("showSearchedApartment");
        String location = "gokarna";
        ApartmentBLL instance = new ApartmentBLL();
        ResultSet result = instance.showSearchedApartment(location);
        assertTrue(result.next());
    }
    
}
