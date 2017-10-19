/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Routing;
import data.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samip
 */
public class UserBLLTest {
    
    public UserBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkRepeatedUsername method, of class UserBLL.
     */
    @Test
    public void testCheckRepeatedUsername() {
        System.out.println("checkRepeatedUsername");
        User user = new User( "hello3");
        UserBLL instance = new UserBLL();
        boolean expResult = false;
        boolean result = instance.checkRepeatedUsername(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveUser method, of class UserBLL.
     */
    @Test
    public void testSaveUser() {
        System.out.println("saveUser");
        Date today = java.sql.Date.valueOf("2017-09-25");
        User user = new User("check", "first", "lastname", "1234556", today, 
                "9765r44", "address"," gender", "visibility"," Agreed") ;
        UserBLL instance = new UserBLL();
        instance.saveUser(user);
    }

    /**
     * Test of updateProfileDetailse method, of class UserBLL.
     */
    @Test
    public void testUpdateProfileDetailse() {
        System.out.println("updateProfileDetailse");
        Date today = java.sql.Date.valueOf("2017-09-25");
        User user = new User("check", "first", "lastname", "1234556", today, "9765r44", "address"," gender", "visibility"," Agreed") ;
        UserBLL instance = new UserBLL();
        instance.updateProfileDetailse(user);
    }

//    /**
//     * Test of isValidUserandPassword method, of class UserBLL.
//     */
    @Test
    public void testIsValidUserandPassword() {
        System.out.println("isValidUserandPassword");
        String username = "";
        String password = "";
        UserBLL instance = new UserBLL();
        boolean expResult = false;
        boolean result = instance.isValidUserandPassword(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserProfileDetails method, of class UserBLL.
     */
    @Test
    public void testGetUserProfileDetails() throws SQLException {
        System.out.println("getUserProfileDetails");
        String username = "hello13";
        UserBLL instance = new UserBLL();
        ResultSet result = instance.getUserProfileDetails(username);
        assertTrue(result.next());
        
    }

    /**
     * Test of getAllRegisteredUsername method, of class UserBLL.
     */
    @Test
    public void testGetAllRegisteredUsername() {
        System.out.println("getAllRegisteredUsername");
        String username = "hello13";
        UserBLL instance = new UserBLL();
        List<User> result = instance.getAllRegisteredUsername(username);
        assertTrue(result.size()>0);
    }

    /**
     * Test of listAllSearchedUser method, of class UserBLL.
     */
    @Test
    public void testListAllSearchedUser() throws SQLException {
        System.out.println("listAllSearchedUser");
        String username = "hello13";
        UserBLL instance = new UserBLL();
        ResultSet result = instance.listAllSearchedUser(username);
        assertTrue(result.next());
    }

    /**
     * Test of showMoreUserDetail method, of class UserBLL.
     */
    @Test
    public void testShowMoreUserDetail() throws SQLException {
        System.out.println("showMoreUserDetail");
        String username = "hello13";
        UserBLL instance = new UserBLL();
        ResultSet expResult = null;
        ResultSet result = instance.showMoreUserDetail(username);
        assertTrue(result.next());
    }

    /**
     * Test of getUserVisibilityStatus method, of class UserBLL.
     */
    @Test
    public void testGetUserVisibilityStatus() {
        System.out.println("getUserVisibilityStatus");
        String username = "hello13";
        UserBLL instance = new UserBLL();
        String expResult = "Public";
        String result = instance.getUserVisibilityStatus(username);
        assertEquals(expResult, result);
    }
    
}
