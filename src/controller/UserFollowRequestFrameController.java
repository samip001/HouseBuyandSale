/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import model.FolllowRequestBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class UserFollowRequestFrameController implements Initializable {

    @FXML
    private Label requestedLabel;
    @FXML
    private JFXListView<AnchorPane> userFollowRequestList;
    @FXML
    private MaterialDesignIconView notFoundGlyph;
    @FXML
    private Label infoLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listFollowRequestedUser();
        
    }
    
    private void listFollowRequestedUser(){
        FolllowRequestBLL fr =new FolllowRequestBLL();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = fr.listFollowRequestedUser(Routing.USERNAME);

        AnchorPane ac = null;
        try {
            if (!rs.isBeforeFirst()) {
                userFollowRequestList.setVisible(false);
                infoLbl.setText("Following Request Not Found");
                notFoundGlyph.setVisible(true);
            } else {
                     userFollowRequestList.setVisible(true);
                    infoLbl.setVisible(false);
                    notFoundGlyph.setVisible(false);
                int count = 0;
                while (rs.next()) {
                    count++;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.FOLLOWREQUEST));
                        ac = (AnchorPane) loader.load();

                        UserFollowRequestController ufrc = (UserFollowRequestController) loader.getController();

                        Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                        ImagePattern im = new ImagePattern(image);

                        ufrc.userProfileCircle.setFill(im);
                        ufrc.usernameLabel.setText(rs.getString("requested_user"));
                        if (rs.getString("status").equals("Unseen")) {
                            ufrc.newequestLabel.setText("New");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(NotificationsFrameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userFollowRequestList.getItems().add(ac);
                }
                requestedLabel.setText(String.valueOf(count) + " Request Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserFollowRequestFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
        //update unseen to seen status
        fr.updateUnseenToSeenFollowRequest(Routing.USERNAME);
    }

    
   
    
}
