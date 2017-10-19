/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.Booked;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samiprai
 */
public class BookedBLLTest {
    
    public BookedBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of bookedHouse method, of class BookedBLL.
     */
//    @Test
//    public void testBookedHouse() {
//        System.out.println("bookedHouse");
//        Booked bl = null;
//        BookedBLL instance = new BookedBLL();
//        instance.bookedHouse(bl);
//    }

    /**
     * Test of getEndDateValidofBookedHouse method, of class BookedBLL.
     */
    @Test
    public void testGetEndDateValidofBookedHouse() {
        System.out.println("getEndDateValidofBookedHouse");
        String user = "hello1";
        BookedBLL instance = new BookedBLL();
        int expResult = 0;
        int result = instance.getEndDateValidofBookedHouse(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteConfirmBookRequestForTimeExceeds method, of class BookedBLL.
     */
    @Test
    public void testDeleteConfirmBookRequestForTimeExceeds() {
        System.out.println("deleteConfirmBookRequestForTimeExceeds");
        String user = "";
        String housetype = "";
        int houseid = 0;
        BookedBLL instance = new BookedBLL();
        instance.deleteConfirmBookRequestForTimeExceeds(user, housetype, houseid);
    }

    /**
     * Test of deleteBookRequestofUserTimeExceeds method, of class BookedBLL.
     */
    @Test
    public void testDeleteBookRequestofUserTimeExceeds() {
        System.out.println("deleteBookRequestofUserTimeExceeds");
        String user = "";
        String housetype = "";
        int houseid = 0;
        BookedBLL instance = new BookedBLL();
        instance.deleteBookRequestofUserTimeExceeds(user, housetype, houseid);
    }

    /**
     * Test of updateBookedListUserStatus method, of class BookedBLL.
     */
    @Test
    public void testUpdateBookedListUserStatus() {
        System.out.println("updateBookedListUserStatus");
        int houseId = 0;
        String housetype = "";
        String user = "";
        BookedBLL instance = new BookedBLL();
        instance.updateBookedListUserStatus(houseId, housetype, user);
    }

    /**
     * Test of updateBookedListrequestStatus method, of class BookedBLL.
     */
    @Test
    public void testUpdateBookedListrequestStatus() {
        System.out.println("updateBookedListrequestStatus");
        int houseId = 0;
        String housetype = "";
        String user = "";
        BookedBLL instance = new BookedBLL();
        instance.updateBookedListrequestStatus(houseId, housetype, user);
    }

    /**
     * Test of confirmBookingfromRequestedUser method, of class BookedBLL.
     */
//    @Test
//    public void testConfirmBookingfromRequestedUser() {
//        System.out.println("confirmBookingfromRequestedUser");
//        Booked bl = null;
//        BookedBLL instance = new BookedBLL();
//        instance.confirmBookingfromRequestedUser(bl);
//    }

    /**
     * Test of getBookedInformatioon method, of class BookedBLL.
     */
    @Test
    public void testGetBookedInformatioon() throws SQLException {
        System.out.println("getBookedInformatioon");
        String user = "hello13";
        String requeststatus = "Booked";
        BookedBLL instance = new BookedBLL();
        ResultSet result = instance.getBookedInformatioon(user, requeststatus);
        assertFalse(result.next());
    }

    /**
     * Test of getBookedRequestInformatioon method, of class BookedBLL.
     */
    @Test
    public void testGetBookedRequestInformatioon() throws SQLException {
        System.out.println("getBookedRequestInformatioon");
        String user = "hello13";
        String requeststatus = "Waiting";
        BookedBLL instance = new BookedBLL();
        ResultSet result = instance.getBookedRequestInformatioon(user, requeststatus);
        assertFalse( result.first());
    }

    /**
     * Test of getBookedPostOwnerUsername method, of class BookedBLL.
     */
    @Test
    public void testGetBookedPostOwnerUsername() throws SQLException {
        System.out.println("getBookedPostOwnerUsername");
        int houseid = 0;
        BookedBLL instance = new BookedBLL();
        ResultSet expResult = null;
        ResultSet result = instance.getBookedPostOwnerUsername(houseid);
//        assertEquals(expResult, result);
        assertFalse(result.first());
    }

    /**
     * Test of chekUserselectedBefore method, of class BookedBLL.
     */
    @Test
    public void testChekUserselectedBefore() {
        System.out.println("chekUserselectedBefore");
        String user = "hello13 ";
        String housetype = "Apartment";
        int houseid = 0;
        String requeststatus = "Waiting";
        BookedBLL instance = new BookedBLL();
        boolean expResult = false;
        boolean result = instance.chekUserselectedBefore(user, housetype, houseid, requeststatus);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserStatus method, of class BookedBLL.
     */
    @Test
    public void testGetUserStatus() {
        System.out.println("getUserStatus");
        String otherusername = "hello13";
        BookedBLL instance = new BookedBLL();
        int expResult = 0;
        int result = instance.getUserStatus(otherusername);
        assertEquals(expResult, result);
    }
}
