/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.User;
import data.UserDescription;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UserDescriptionBLLTest {
    
    public UserDescriptionBLLTest() {
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
     * Test of addProfileDescription method, of class UserDescriptionBLL.
     */
    @Test
    public void testAddProfileDescription() {
        System.out.println("addProfileDescription");
        User u =new User("hello");
        UserDescription ud = new UserDescription(null, u,"check.jpg","");
        UserDescriptionBLL instance = new UserDescriptionBLL();
        instance.addProfileDescription(ud);
    }

    /**
     * Test of updateProfileDescription method, of class UserDescriptionBLL.
     */
    @Test
    public void testUpdateProfileDescription() {
        System.out.println("updateProfileDescription");
        User user =new User("edkjasdhj87");
        UserDescription userdesc = new UserDescription(null, user, "male.png", "profileDescription");
        UserDescriptionBLL instance = new UserDescriptionBLL();
        instance.updateProfileDescription(userdesc);
    }

    /**
     * Test of getUserDescription method, of class UserDescriptionBLL.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetUserDescription() throws SQLException {
        System.out.println("getUserDescription");
        String username = "hello13";
        UserDescriptionBLL instance = new UserDescriptionBLL();
        ResultSet result = instance.getUserDescription(username);
        assertTrue("Return value must be greter than 0",result.next());
    }

    /**
     * Test of getProfileName method, of class UserDescriptionBLL.
     */
    @Test
    public void testGetProfileName() {
        System.out.println("getProfileName");
        String username = "hello13";
        UserDescriptionBLL instance = new UserDescriptionBLL();
        String expResult = "other.png";
        String result = instance.getProfileName(username);
        assertEquals(expResult, result);
    }
    
}
