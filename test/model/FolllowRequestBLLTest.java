/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class FolllowRequestBLLTest {
    
    public FolllowRequestBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of requestedTofollowOtherUser method, of class FolllowRequestBLL.
     */
    @Test
    public void testRequestedTofollowOtherUser() {
        System.out.println("requestedTofollowOtherUser");
        String username = "";
        String requestedUser = "";
        FolllowRequestBLL instance = new FolllowRequestBLL();
        instance.requestedTofollowOtherUser(username, requestedUser);
    }

    /**
     * Test of checkPreviousFollowActivity method, of class FolllowRequestBLL.
     */
    @Test
    public void testcheckPreviousFollowActivity() {
        System.out.println("checkPreviousFollowActivity");
        String username = "hello13";
        String requestedUser = "check1";
        FolllowRequestBLL instance = new FolllowRequestBLL();
        String expResult = null;
        String result = instance.checkPreviousFollowActivity(username, requestedUser);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNewFollowRequestNumber method, of class FolllowRequestBLL.
     */
    @Test
    public void testGetNewFollowRequestNumber() {
        System.out.println("getNewFollowRequestNumber");
        String user = "check1";
        FolllowRequestBLL instance = new FolllowRequestBLL();
        int expResult = 0;
        int result = instance.getNewFollowRequestNumber(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of listFollowRequestedUser method, of class FolllowRequestBLL.
     */
    @Test
    public void testListFollowRequestedUser() throws SQLException {
        System.out.println("listFollowRequestedUser");
        String user = "check1";
        FolllowRequestBLL instance = new FolllowRequestBLL();
        ResultSet result = instance.listFollowRequestedUser(user);
        assertFalse(result.next());
    }

    /**
     * Test of followRequestAccepted method, of class FolllowRequestBLL.
     */
//    @Test
//    public void testFollowRequestAccepted() {
//        System.out.println("followRequestAccepted");
//        String name = "";
//        FolllowRequestBLL instance = new FolllowRequestBLL();
//        instance.followRequestAccepted(name);
//    }

    /**
     * Test of followRequestRejected method, of class FolllowRequestBLL.
     */
//    @Test
//    public void testFollowRequestRejected() {
//        System.out.println("followRequestRejected");
//        String name = "";
//        FolllowRequestBLL instance = new FolllowRequestBLL();
//        instance.followRequestRejected(name);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateUnseenToSeenFollowRequest method, of class FolllowRequestBLL.
     */
    @Test
    public void testUpdateUnseenToSeenFollowRequest() {
        System.out.println("updateUnseenToSeenFollowRequest");
        String user = "";
        FolllowRequestBLL instance = new FolllowRequestBLL();
        instance.updateUnseenToSeenFollowRequest(user);
    }
}
