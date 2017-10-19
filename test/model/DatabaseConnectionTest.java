/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
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
public class DatabaseConnectionTest {
    public DatabaseConnectionTest() {
        
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
     * Test of getInstanceofDB method, of class DatabaseConnection.
     */
    @Test
    public void testGetInstanceofDB() {
        System.out.println("getInstanceofDB");
        DatabaseConnection expResult = DatabaseConnection.getInstanceofDB();
        DatabaseConnection result = DatabaseConnection.getInstanceofDB();
        assertEquals(expResult, result);
    }

    /**
     * Test of connectDB method, of class DatabaseConnection.
     */
    @Test
    public void testConnectDB() {
        System.out.println("connectDB");
        DatabaseConnection instance = DatabaseConnection.getInstanceofDB();
        Connection result = instance.connectDB();
        assertTrue(result != null);
    }

    /**
     * Test of disconnect method, of class DatabaseConnection.
     */
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        DatabaseConnection instance = DatabaseConnection.getInstanceofDB();
        Connection conn = instance.connectDB();
        instance.disconnect(conn);
    }
}
