/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samip
 */
public class UserTypeBLLTest {
    
    public UserTypeBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of listUserType method, of class UserTypeBLL.
     */
    @Test
    public void testListUserType() {
        System.out.println("listUserType");
        UserTypeBLL instance = new UserTypeBLL();
        List<String> result = instance.listUserType();
        assertTrue(result.size()>0);
    }
    
}
