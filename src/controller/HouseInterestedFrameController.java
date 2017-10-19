/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import model.InterestedUserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class HouseInterestedFrameController implements Initializable {

    @FXML
    private JFXComboBox<String> houseTypeCombo;
    @FXML
    private JFXButton loadBtn;
    @FXML
    private AnchorPane noresultPane;
    @FXML
    private Text houeTypeLabel;
    @FXML
    private AnchorPane resultPane;
    @FXML
    private Label resultFoundLabel;
    @FXML
    private JFXListView<AnchorPane> listInterestedHouse;
    @FXML
    private Label errorInfo;
    
    private InterestedUserBLL iu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iu =new InterestedUserBLL();
        houseTypeCombo.getItems().addAll("Room","House","Apartment");
        
    }    

    @FXML
    private void listInterestedHouseActivity(ActionEvent event) {
        noresultPane.setVisible(false);
        resultPane.setVisible(false);
       
        String housetype=null;
        housetype=houseTypeCombo.getSelectionModel().getSelectedItem();
        listInterestedHouse.getItems().clear();
        AnchorPane ap = null;
        
        if (housetype == null) {
            errorInfo.setText("Select Proper House Type To View Your Interest List");
        }
        else{
            errorInfo.setText(null);
            int count = 0;
            String id=null;
            
            switch(housetype){
                case "Room":
                    id = "room_id";
                    break;
                case "House":
                    id="house_id";
                    break;
                case "Apartment":
                    id = "apartment_id";
                    break;
            }
            
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();
            ResultSet rs=iu.listUserInterstInHouse(Routing.USERNAME,housetype);
            try {
                if (!rs.isBeforeFirst()) {
                    houeTypeLabel.setText("'" + housetype + "'");
                    noresultPane.setVisible(true);
                } else {
                    if(noresultPane.isVisible()){
                        resultPane.setVisible(true);
                        noresultPane.setVisible(false);
                    }
                    resultPane.setVisible(true);
                    while (rs.next()) {
                        count++;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.LISTINTERESTEDUSER));
                        try {
                            ap = (AnchorPane) loader.load();
                            ListInterestedHouseController lihc = (ListInterestedHouseController) loader.getController();

                            Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                            ImagePattern im = new ImagePattern(image);
                            
                            lihc.imageCircle.setFill(im);
                            lihc.usernameLabel.setText(rs.getString("username"));
                            lihc.posteddate.setText(rs.getDate("posted_date").toString());
                            lihc.interestedDate.setText(rs.getDate("interested_date").toString());
                            lihc.idLabel.setText(String.valueOf(rs.getInt(id)));
                            lihc.locationLabel.setText(rs.getString("location"));
                            lihc.houseTypeLabel.setText(houseTypeCombo.getSelectionModel().getSelectedItem());
                            lihc.visibilityLabel.setText(rs.getString("visibility"));

                            if (new Validation().getDateDifferenceinDay(rs.getDate("end_date").toString()) < 0) {
                                lihc.statusLabel.setText("Post Validity Finished");
                            } else {
                                lihc.statusLabel.setText(rs.getString("status"));
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        listInterestedHouse.getItems().add(ap);
                    }
                    resultFoundLabel.setText("You have shown " +count+" interest in "+housetype); 
                }
                    
            } catch (SQLException ex) {
                Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
        
        }
    }
    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
}
