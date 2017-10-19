/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author samip
 */
public class FadeAnimation {
        
        
     public boolean fadeOut(AnchorPane pane,AnchorPane pane1){
        pane1.setOpacity(0);
        FadeTransition fadeout =new FadeTransition();
        fadeout.setDuration(Duration.millis(500));
        fadeout.setNode(pane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.setOnFinished((e)->{
            fadeIn(pane1);
        });
        
        fadeout.play();
        return true;
        }
     
     public boolean fadeIn(AnchorPane pane){
         FadeTransition fadein = new FadeTransition();
         fadein.setDuration(Duration.millis(500));
         fadein.setNode(pane);
         fadein.setFromValue(0);
         fadein.setToValue(1);
         fadein.setOnFinished((e) ->{
             pane.setOpacity(1);
         });
         fadein.play();
         return true;
     }
 }    

