/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.House;
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
public class HouseBLLTest {
    
    public HouseBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of postHouseDetails method, of class HouseBLL.
     */
    @Test
    public void testPostHouseDetails() {
        System.out.println("postHouseDetails");
        Date today = java.sql.Date.valueOf("2017-09-25");
        Date enddate = java.sql.Date.valueOf("2017-10-25");
        
        House house = new House(null, "hello", 2, "House", "67hh", "Chautara", "Rent",today , enddate, 10000, "Monthly", "Available", "Public");
        HouseBLL instance = new HouseBLL();
        instance.postHouseDetails(house);
    }

    /**
     * Test of postedHouseList method, of class HouseBLL.
     */
    @Test
    public void testPostedHouseList() throws SQLException {
        System.out.println("postedHouseList");
        String username = "hello13";
        HouseBLL instance = new HouseBLL();
        ResultSet result = instance.postedHouseList(username);
        assertTrue(result.next());
    }

    /**
     * Test of updateHouseDetails method, of class HouseBLL.
     */
    @Test
    public void testUpdateHouseDetails() {
        System.out.println("updateHouseDetails");
        Date enddate = java.sql.Date.valueOf("2017-10-25");
        House h = new House(1, "hello13", 1, "Paying Guest", "6hvb", "location", "Rent", null, enddate, 6660, "Yearly", "Available", "Private");
        h.setHouseDescription("House description setted");
        HouseBLL instance = new HouseBLL();
        instance.updateHouseDetails(h);
    }
    
    /**
     * Test of getPostedHouseEndDate method, of class ApartmentBLL.
     */
    @Test
    public void testgetPostedHouseEndDate() throws SQLException {
        System.out.println("getPostedApartmentEndDate");
        String username = "hello13";
        HouseBLL instance = new HouseBLL();
        ResultSet result = instance.getPostedHouseEndDate(username);
        assertTrue(result.next());
    }

    /**
     * Test of getPosetdHouseNumber method, of class HouseBLL.
     */
    @Test
    public void testGetPosetdHouseNumber() {
        System.out.println("getPosetdHouseNumber");
        String username = "hello13";
        HouseBLL instance = new HouseBLL();
        int expResult = 2;
        int result = instance.getPosetdHouseNumber(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHouseDetails method, of class HouseBLL.
     */
    @Test
    public void testGetHouseDetails() throws SQLException {
        System.out.println("getHouseDetails");
        int houseid = 2;
        HouseBLL instance = new HouseBLL();
//        ResultSet expResult = null;
        ResultSet result = instance.getHouseDetails(houseid);
        assertTrue(result.next());
    }

    /**
     * Test of updateHouseStatus method, of class HouseBLL.
     */
    @Test
    public void testUpdateHouseStatus() {
        System.out.println("updateHouseStatus");
        String status = "Waiting";
        int houseid = 2;
        HouseBLL instance = new HouseBLL();
        instance.updateHouseStatus(status, houseid);
    }

    /**
     * Test of showSearchedHouse method, of class HouseBLL.
     */
    @Test
    public void testShowSearchedHouse() throws SQLException {
        System.out.println("showSearchedHouse");
        String location = "some";
        HouseBLL instance = new HouseBLL();
        ResultSet result = instance.showSearchedHouse(location);
        assertTrue(result.next());
    }
}
