/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import data.FollowedUser;
import data.NotificationUser;
import model.FolllowRequestBLL;
import model.FollowedUserBLL;
import model.NotificationBLL;
import model.UserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class UserFollowRequestController implements Initializable {

    @FXML
    private AnchorPane userProfilePane;
    @FXML
    private TextArea profileDescription;
    @FXML
    private Circle profileCircle;
    @FXML
    private Label profileUsername;
    @FXML
    private Label profilename;
    @FXML
    private Label profileDOB;
    @FXML
    private Label profileAddress;
    @FXML
    private Label roomLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label apartmentLabel;
    @FXML
    private JFXButton profileBackBtn;
    @FXML
    private AnchorPane viewPane;
    @FXML
    public Circle userProfileCircle;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label newequestLabel;
    @FXML
    private JFXButton viewProfileBtn;
    @FXML
    private JFXButton acceptBtn;
    @FXML
    private JFXButton rejectedBtn;
    @FXML
    private AnchorPane rejectedPane;
    @FXML
    private AnchorPane accpetedPane;
    @FXML
    private Label acceptedLabel;
    @FXML
    private JFXButton acceptedBackBtn;
    
    private FolllowRequestBLL fr;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        profileUsername.setText(usernameLabel.getText().trim());
        fr =new FolllowRequestBLL();
           
    }    

    @FXML
    private void backToViewRequestPane(ActionEvent event) {
        animatePane(userProfilePane, viewPane);
        
    }

    @FXML
    private void viewProfileActivity(ActionEvent event) {
        String name= usernameLabel.getText().trim();
        
        animatePane(viewPane, userProfilePane);
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        
        ResultSet rs = new UserBLL().showMoreUserDetail(name);
        try {
            while (rs.next()) {
                Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                ImagePattern im = new ImagePattern(image);
                profileCircle.setFill(im);

                profileUsername.setText(rs.getString("username"));
                profilename.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                profileDOB.setText(rs.getString("dob"));
                profileAddress.setText(rs.getString("address"));
                if (rs.getString("profile_description").equals("")) {
                    profileDescription.setText("No Profile Description Found");
                } else {
                    profileDescription.setText(rs.getString("profile_description"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
        
    }


    @FXML
    private void rejectRequestActivity(ActionEvent event) {
      //reject and disable accept button
         fr.followRequestRejected(usernameLabel.getText().trim());
        animatePane(viewPane, rejectedPane);
        
        
    }

    @FXML
    private void acceptRequestActivity(ActionEvent event) {
          //follow iin follow request
        fr.followRequestAccepted(usernameLabel.getText().trim());
       
       // insert into follow_user
        FollowedUser fu =new FollowedUser();
        fu.setUsername(Routing.USERNAME);
        fu.setFollowingUsername(usernameLabel.getText().trim());
        fu.setFollowedDate(java.sql.Date.valueOf(new Validation().getTodaydate()));
        fu.setPermission("Allow");
        //inserted into folowing table
         FollowedUserBLL followuser =new FollowedUserBLL(); 
         followuser.startFollowedUser(fu);
       
       //notify that usernamelabel
        NotificationUser nfu =new NotificationUser();
        nfu.setFromuser(Routing.USERNAME);
        nfu.setNotificationType(2);
        nfu.setTouser(usernameLabel.getText().trim());
        nfu.setDetails("has Accepted Your Follow request");
        nfu.setStatus("Unseen");
        
        new NotificationBLL().sendNotification(nfu);
        
        animatePane(viewPane, accpetedPane);
        acceptedLabel.setText("You have Accepted "+usernameLabel.getText().trim()+" Follow Request");
        
    }

    @FXML
    private void acceptedToView(ActionEvent event) {
        animatePane(accpetedPane, viewPane);
        acceptBtn.setVisible(false);
        rejectedBtn.setVisible(false);
    }
    
      private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
    
}
