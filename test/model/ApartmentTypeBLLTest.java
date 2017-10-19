/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samiprai
 */
public class ApartmentTypeBLLTest {
    
    public ApartmentTypeBLLTest() {
    }
    
    /**
     * Test of listApartmentType method, of class ApartmentTypeBLL.
     */
    @Test
    public void testListApartmentType() {
        System.out.println("listApartmentType");
        ApartmentTypeBLL instance = new ApartmentTypeBLL();
        List<String> result = instance.listApartmentType();
        assertTrue(result.size()>0);
    }
    
}
