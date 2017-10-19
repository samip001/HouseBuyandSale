/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Room;
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
public class RoomBLLTest {
    
    public RoomBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of postRoomDetails method, of class RoomBLL.
     */
    @Test
    public void testPostRoomDetails() {
        System.out.println("postRoomDetails");
        Date today = java.sql.Date.valueOf("2017-09-25");
        Date enddate = java.sql.Date.valueOf("2017-10-25");
        Room room = new Room(null, "hello13", 2, "Room", "new location", 2,
                today, enddate, 2000, "Weekly", "Room Junit", "Available", "Public");
        RoomBLL instance = new RoomBLL();
        instance.postRoomDetails(room);
        
    }

    /**
     * Test of updateRoomDetails method, of class RoomBLL.
     */
    @Test
    public void testUpdateRoomDetails() {
        System.out.println("updateRoomDetails");
        Date today = java.sql.Date.valueOf("2017-09-25");
        Date enddate = java.sql.Date.valueOf("2017-10-25");
        Room room = new Room(9, "hello13", 9, "Room", "new location", 2,
                today, enddate, 2000, "Weekly", "Room Junit", "Available", "Public");
        RoomBLL instance = new RoomBLL();
        instance.updateRoomDetails(room);
    }

    /**
     * Test of getPosetdRoomNumber method, of class RoomBLL.
     */
    @Test
    public void testGetPosetdRoomNumber() {
        System.out.println("getPosetdRoomNumber");
        String username = "chandler101";
        RoomBLL instance = new RoomBLL();
        int expResult = 2;
        int result = instance.getPosetdRoomNumber(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPostedRoomEndDate method, of class RoomBLL.
     */
    @Test
    public void testgetPostedRoomEndDate() throws SQLException {
        System.out.println("getPostedRoomEndDate");
        String username = "hello13";
        RoomBLL instance = new RoomBLL();
        ResultSet result = instance.getPostedRoomEndDate(username);
        assertTrue(result.next());
    }

    /**
     * Test of postedRoomList method, of class RoomBLL.
     */
    @Test
    public void testPostedRoomList() throws SQLException {
        System.out.println("postedRoomList");
        String username = "hello13";
        RoomBLL instance = new RoomBLL();
        ResultSet result = instance.postedRoomList(username);
        assertTrue(result.next());
    }

    /**
     * Test of getRoomDetails method, of class RoomBLL.
     */
    @Test
    public void testGetRoomDetails() throws SQLException {
        System.out.println("getRoomDetails");
        int id = 2;
        RoomBLL instance = new RoomBLL();
        ResultSet expResult = null;
        ResultSet result = instance.getRoomDetails(id);
        assertTrue(result.next());
        
    }

    /**
     * Test of updateRoomStatus method, of class RoomBLL.
     */
    @Test
    public void testUpdateRoomStatus() {
        System.out.println("updateRoomStatus");
        String status = "Unavailable";
        int id = 9;
        RoomBLL instance = new RoomBLL();
        instance.updateRoomStatus(status, id);
    }

    /**
     * Test of showSearchedRoom method, of class RoomBLL.
     */
    @Test
    public void testShowSearchedRoom() throws SQLException {
        System.out.println("showSearchedRoom");
        String location = "a";
        RoomBLL instance = new RoomBLL();
        ResultSet expResult = null;
        ResultSet result = instance.showSearchedRoom(location);
        assertTrue(result.next());
    }
}
