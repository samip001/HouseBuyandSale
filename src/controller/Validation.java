/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

/**
 *
 * @author samip
 */
public class Validation {
    
    private SimpleDateFormat sdf;
    private String todayDate;
    
    public Validation(){
        sdf= new SimpleDateFormat("yyyy-MM-dd");
        
    }
    
    public boolean validateTextField(String value){
        Pattern p = Pattern.compile("[a-zA-Z]{3,30}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    
    public boolean validateUsername(String value){
        Pattern p = Pattern.compile("[-a-zA-Z0-9._@]{3,30}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    public boolean validatePassword(String value){
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!&]).{5,15})");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    
    public boolean validateContactNumber(String value){
         Pattern p = Pattern.compile("[0-9-+]{7,15}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    public boolean validateAddress(String value){
        Pattern p = Pattern.compile("[ a-zA-Z0-9_-]{3,30}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    public boolean validateTextArea(String value){
        Pattern p = Pattern.compile("[ a-zA-Z0-9_]{10,300}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    
     public boolean validateRoomNumber(String value){
        Pattern p = Pattern.compile("[0-9]{1,2}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    
     public boolean validatePrice(String value){
        Pattern p = Pattern.compile("[0-9]{4,10}");
        Matcher m = p.matcher(value);
        if(m.find() && m.group().equals(value)){
            return true; 
        }
        else{
                return false;
        }
    }
    public String getTodaydate(){
        Date date =new Date();
        return sdf.format(date);
    }
    
    public int getDateDifferenceinYear(String datevalue){
        int i=0;
        Date date = new Date();
        todayDate = sdf.format(date);
    
            try {
                 if (datevalue== null) {
                    return i;
                } 
                 else{
                    Date today = sdf.parse(this.todayDate);
                    Date choosendate = sdf.parse(datevalue);
                    DateTime d1 = new DateTime(today);
                    DateTime d2 = new DateTime(choosendate);
                    Period p = new Period(d2, d1);
                    i= p.getYears();
                   
                 }
                
            } catch (ParseException ex) {
                Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                return i;
            }
            return i;
     }
    
   
    public int getDateDifferenceinDay(String datevalue){
        int i=0;
        Date date = new Date();
        todayDate = sdf.format(date);
   
                     
          try {
              Date today = sdf.parse(this.todayDate);
              Date choosendate = sdf.parse(datevalue);
              DateTime d1 = new DateTime(today);
              DateTime d2 = new DateTime(choosendate);
              Days d = Days.daysBetween(d1, d2);
              i = d.getDays();
                
            } catch (ParseException ex) {
                return i;
            }
            catch(NullPointerException ex){
                return 0;
            }
        return i;
     }
    
    public String makeCapitalLetter(String words){
        return words.substring(0, 1).toUpperCase() + words.substring(1).toLowerCase();
    }
  
}
