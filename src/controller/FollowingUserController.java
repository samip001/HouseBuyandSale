/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.FollowedUserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class FollowingUserController implements Initializable {

    @FXML
    private AnchorPane showPane;
    @FXML
    public Circle imageCircle;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label followingDateLbl;
    @FXML
    private JFXButton unfollowBtn;
    @FXML
    private AnchorPane unfollowPane;
    @FXML
    private Label infoLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void unfollowActivity(ActionEvent event) {
        String followingUsername = usernameLabel.getText();
        FollowedUserBLL fu =new FollowedUserBLL();
        fu.unFollowUser(Routing.USERNAME, followingUsername);
        animatePane(showPane, unfollowPane);
        
        infoLabel.setText("You just Unfollow "+followingUsername);
        
    }
    
     private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
    
    
    
}
