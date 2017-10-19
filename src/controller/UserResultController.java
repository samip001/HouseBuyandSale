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
import data.NotificationUser;
import model.ApartmentBLL;
import model.FolllowRequestBLL;
import model.HouseBLL;
import model.NotificationBLL;
import model.RoomBLL;
import model.UserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class UserResultController implements Initializable {

    @FXML
    public Circle imageCircle;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label genderLabel;
    @FXML
    public JFXButton viewProfileBtn;
    @FXML
    public JFXButton followBtn;
    @FXML
    public Label visiibiltyLabel;
    
//    private Query query;
   
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
    private JFXButton profileBackBtn;
    @FXML
    private AnchorPane requestPane;
    @FXML
    private JFXButton requestBackBtn;
    @FXML
    private Label requestLabel;
    @FXML
    private AnchorPane showPane;
    @FXML
    private Label roomLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label apartmentLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewProfile(ActionEvent event) {
        UserBLL u =new UserBLL();
        RoomBLL r =new RoomBLL();
        HouseBLL h =new HouseBLL();
        ApartmentBLL a =new ApartmentBLL();
        String name = null;
        name= usernameLabel.getText().trim();
     
        animatePane(showPane, userProfilePane);
        roomLabel.setText(String.valueOf(r.getPosetdRoomNumber(name)));
        houseLabel.setText(String.valueOf(h.getPosetdHouseNumber(name)));
        apartmentLabel.setText(String.valueOf(a.getPosetdApartmentNumber(name)));

        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        ResultSet rs = u.showMoreUserDetail(name);
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
    private void followBtn(ActionEvent event) {
        FolllowRequestBLL fr =new FolllowRequestBLL();
        //for Following request
        fr.requestedTofollowOtherUser(Routing.USERNAME,usernameLabel.getText().trim());
        
        
        //for Notifying requested user by current login user
        NotificationUser nfu =new NotificationUser();
        nfu.setFromuser(Routing.USERNAME);
        nfu.setTouser(usernameLabel.getText().trim());
        nfu.setNotificationType(1);
        nfu.setDetails("has requested you to accept follow request");
        nfu.setStatus("Unseen");
        
        NotificationBLL notification =new NotificationBLL();
        notification.sendNotification(nfu);
        
        requestLabel.setText("Follow Request Sended to "+usernameLabel.getText().trim());
        animatePane(userProfilePane, requestPane);
    }

    @FXML
    private void backToShowPane(ActionEvent event) {
        animatePane(userProfilePane, showPane);
    }

    @FXML
    private void fromRequestToShowPane(ActionEvent event) {
        animatePane(requestPane , showPane);
        followBtn.setText("Request Sent");
        followBtn.setDisable(true);
    }
    
    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
    
}
