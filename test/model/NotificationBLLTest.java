/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import data.NotificationUser;
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
public class NotificationBLLTest {
    
    public NotificationBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of sendNotification method, of class NotificationBLL.
     */
    @Test
    public void testSendNotification() {
        System.out.println("sendNotification");
        NotificationUser nfu;
        nfu = new NotificationUser(0, "chandler101", 2, "hello13", "Check Notification", "Unseen");
        NotificationBLL instance = new NotificationBLL();
        instance.sendNotification(nfu);
    }

    /**
     * Test of updateUnseenToSeenNotifcations method, of class NotificationBLL.
     */
    @Test
    public void testUpdateUnseenToSeenNotifcations() {
        System.out.println("updateUnseenToSeenNotifcations");
        String user = "hello13";
        NotificationBLL instance = new NotificationBLL();
        instance.updateUnseenToSeenNotifcations(user);
    }

    /**
     * Test of getAllNotificationList method, of class NotificationBLL.
     */
    @Test
    public void testGetAllNotificationList() throws SQLException {
        System.out.println("getAllNotificationList");
        String user = "hello13";
        NotificationBLL instance = new NotificationBLL();
        ResultSet result = instance.getAllNotificationList(user);
        assertTrue(result.next());
    }

    /**
     * Test of getNewNotificatiosNumber method, of class NotificationBLL.
     */
    @Test
    public void testGetNewNotificatiosNumber() {
        System.out.println("getNewNotificatiosNumber");
        String user = "hello13";
        NotificationBLL instance = new NotificationBLL();
        int expResult = 0;
        int result = instance.getNewNotificatiosNumber(user);
        assertEquals(expResult, result);
    }
    
}
