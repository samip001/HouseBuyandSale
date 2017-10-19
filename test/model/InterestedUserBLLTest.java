/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.InterestedUser;
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
public class InterestedUserBLLTest {
    
    public InterestedUserBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of userInterestedInHouse method, of class InterestedUserBLL.
     */
//    @Test
//    public void testUserInterestedInHouse() {
//        System.out.println("userInterestedInHouse");
//        InterestedUser iu = null;
//        InterestedUserBLL instance = new InterestedUserBLL();
//        instance.userInterestedInHouse(iu);
//    }

    /**
     * Test of cancelUserInterestedInHouse method, of class InterestedUserBLL.
     */
    @Test
    public void testCancelUserInterestedInHouse() {
        System.out.println("cancelUserInterestedInHouse");
        String user = "hello13";
        int house_id = 0;
        String housetype = "";
        InterestedUserBLL instance = new InterestedUserBLL();
        instance.cancelUserInterestedInHouse(user,house_id, housetype);
    }

    /**
     * Test of checkUserPrevoiusIntrested method, of class InterestedUserBLL.
     */
    @Test
    public void testCheckUserPrevoiusIntrested() {
        System.out.println("checkUserPrevoiusIntrested");
        String username="hello13";
        String houstype = "Room";
        int id = 2;
        InterestedUserBLL instance = new InterestedUserBLL();
        boolean expResult = false;
        boolean result = instance.checkUserPrevoiusIntrested(username,houstype, id);
        assertEquals(expResult, result);
    }

    /**
     * Test of listUserInterstInHouse method, of class InterestedUserBLL.
     */
    @Test
    public void testListUserInterstInHouse() throws SQLException {
        System.out.println("listUserInterstInHouse");
        String user ="hello13";
        String housetype = "Room";
        InterestedUserBLL instance = new InterestedUserBLL();
        ResultSet result = instance.listUserInterstInHouse(user,housetype);
        assertFalse(result.next());
    }

    /**
     * Test of getNumberofRoomInterestUser method, of class InterestedUserBLL.
     */
    @Test
    public void testGetNumberofRoomInterestUser() {
        System.out.println("getNumberofRoomInterestUser");
        int roomid = 1;
        InterestedUserBLL instance = new InterestedUserBLL();
        int expResult = 0;
        int result = instance.getNumberofRoomInterestUser(roomid);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberofHouseInterestUser method, of class InterestedUserBLL.
     */
    @Test
    public void testGetNumberofHouseInterestUser() {
        System.out.println("getNumberofHouseInterestUser");
        int houseid = 1;
        InterestedUserBLL instance = new InterestedUserBLL();
        int expResult = 2;
        int result = instance.getNumberofHouseInterestUser(houseid);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberofApartmentInterestUser method, of class InterestedUserBLL.
     */
    @Test
    public void testGetNumberofApartmentInterestUser() {
        System.out.println("getNumberofApartmentInterestUser");
        int apartmentid = 1;
        InterestedUserBLL instance = new InterestedUserBLL();
        int expResult = 0;
        int result = instance.getNumberofApartmentInterestUser(apartmentid);
        assertEquals(expResult, result);
    }
    
}
