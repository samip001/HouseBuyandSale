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
import model.NotificationBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class NotificationsFrameController implements Initializable {

    @FXML
    private JFXListView<AnchorPane> listNotifications;
    @FXML
    private Label notificationsLabel;
    @FXML
    private MaterialDesignIconView notfoundNotificationGlyph;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      listNotifications();
        
    }    
    
    private void listNotifications(){
//        Query query =new Query();
        NotificationBLL nf =new NotificationBLL();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        
        Connection conn = db.connectDB();
        ResultSet rs = nf.getAllNotificationList(Routing.USERNAME);
        AnchorPane ap = null;
         try {
              if (!rs.isBeforeFirst()) {
                 listNotifications.setVisible(false);
                 notificationsLabel.setText("Notifications Not Found");
                 notfoundNotificationGlyph.setVisible(true);

             } else {
                 while (rs.next()) {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.NOTIFICATIONLIST));
                     try {
                         ap = (AnchorPane) loader.load();

                         NewNotificationsController nnc = (NewNotificationsController) loader.getController();

                         Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                         ImagePattern im = new ImagePattern(image);

                         nnc.circle.setFill(im);
                         nnc.usernameText.setText(rs.getString("fromuser"));
                         if (rs.getString("status").equals("Unseen")) {
                             nnc.newLabel.setText("New");
                         }
                         nnc.notifcationsDescription.setText(rs.getString("details"));

                     } catch (IOException ex) {
                         Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     listNotifications.getItems().add(ap);
                 }
             }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationsFrameController.class.getName()).log(Level.SEVERE, null, ex);
        }
         db.disconnect(conn);
         
        //Updates unseen to seen
        nf.updateUnseenToSeenNotifcations(Routing.USERNAME);
        
    }
    
}
