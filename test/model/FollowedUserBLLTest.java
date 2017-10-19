/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.FollowedUser;
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
public class FollowedUserBLLTest {
    
    public FollowedUserBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of startFollowedUser method, of class FollowedUserBLL.
     */
//    @Test
//    public void testStartFollowedUser() {
//        System.out.println("startFollowedUser");
//        FollowedUser fu = null;
//        FollowedUserBLL instance = new FollowedUserBLL();
//        instance.startFollowedUser(fu);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of unFollowUser method, of class FollowedUserBLL.
     */
    @Test
    public void testUnFollowUser() {
        System.out.println("unFollowUser");
        String username = "";
        String followingUser = "";
        FollowedUserBLL instance = new FollowedUserBLL();
        instance.unFollowUser(username, followingUser);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserFollwedList method, of class FollowedUserBLL.
     */
    @Test
    public void testGetUserFollwedList() throws SQLException {
        System.out.println("getUserFollwedList");
        String user = "hello1";
        FollowedUserBLL instance = new FollowedUserBLL();
        ResultSet result = instance.getUserFollwedList(user);
        assertFalse(result.next());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserPermission method, of class FollowedUserBLL.
     */
    @Test
    public void testUpdateUserPermission() {
        System.out.println("updateUserPermission");
        String permission = "";
        String user = "";
        String followinguser = "";
        FollowedUserBLL instance = new FollowedUserBLL();
        instance.updateUserPermission(permission, user, followinguser);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getFollowingUserList method, of class FollowedUserBLL.
     */
    @Test
    public void testGetFollowingUserList() throws SQLException {
        System.out.println("getFollowingUserList");
        String user = "hello13";
        FollowedUserBLL instance = new FollowedUserBLL();
        ResultSet result = instance.getFollowingUserList(user);
        assertTrue(result.next());
    }

    /**
     * Test of getFollowedUserPermission method, of class FollowedUserBLL.
     */
    @Test
    public void testGetFollowedUserPermission() {
        System.out.println("getFollowedUserPermission");
        String meuser="hello1";
        String owneruser = "hello13";
        FollowedUserBLL instance = new FollowedUserBLL();
        String expResult = null;
        String result = instance.getFollowedUserPermission(meuser,owneruser);
        assertEquals(expResult, result);
    }
}
