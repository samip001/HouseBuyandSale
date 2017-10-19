/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import model.FollowedUserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class FollowingUserFrameController implements Initializable {

    @FXML
    private AnchorPane noresultPane;
    @FXML
    private AnchorPane resultPane;
    @FXML
    private Label numberOfFollowingUser;
    @FXML
    private JFXListView<AnchorPane> listfollowingUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setFollowingUser();
    }    
    
    private void setFollowingUser(){
        listfollowingUser.getItems().clear();
        AnchorPane ap = null;
        int count = 0;
//        Query query =new Query();
        FollowedUserBLL fu =new FollowedUserBLL();
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();
            ResultSet rs =fu.getFollowingUserList(Routing.USERNAME);
            try {
                if (!rs.isBeforeFirst()) {
                    resultPane.setVisible(false);
                    noresultPane.setVisible(true);
                  } else {
                    if (noresultPane.isVisible()) {
                         resultPane.setVisible(true);
                    }
                    while (rs.next()) {
                        count++;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.FOLLWINGUSER));
                        try {
                            ap = (AnchorPane) loader.load();
                            
                            FollowingUserController urc = (FollowingUserController)loader.getController();
                            
                            Image image = new Image(Routing.USERIMAGES+rs.getString("profile_name"));
                            ImagePattern im = new ImagePattern(image);
                            
                            urc.imageCircle.setFill(im);
                            
                            urc.usernameLabel.setText(rs.getString("username"));
                            urc.followingDateLbl.setText(rs.getDate("followed_date").toString());
                            //date   
                        } catch (IOException ex) {
                            Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        listfollowingUser.getItems().add(ap);

                    }
                    numberOfFollowingUser.setText(" You are Following " +count+" User");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
        }

    
    
}
