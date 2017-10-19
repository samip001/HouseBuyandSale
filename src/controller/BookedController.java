/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.ApartmentBLL;
import model.HouseBLL;
import model.RoomBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class BookedController implements Initializable {

    @FXML
    private AnchorPane showInfoPane;
    @FXML
    public Circle imageCircle;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label bookedDateLabel;
    @FXML
    public Label requestedDateLabel;
    @FXML
    public Label visibilityLabel;
    @FXML
    public Label houseTypeLabel;
    @FXML
    public Label id;
    @FXML
    public Label priceLabel;
    @FXML
    public Label endDateLabel;
    @FXML
    public Label numberLabel;
    @FXML
    public Label remainInfoLabel;
    @FXML
    public Label activeLabel;
    @FXML
    public MaterialDesignIconView activeGlyph;
    @FXML
    public MaterialDesignIconView inactiveGlyph;
    

    @FXML
    private JFXButton viewDetailsBtn;
   
    @FXML
    private AnchorPane rooomPane;
    @FXML
    private TextArea roomDescription;
    @FXML
    private Label roomUsername;
    @FXML
    private Label roomUserTypeLabel;
    @FXML
    private Label roomSearchingLabel;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label roomEndedaDate;
    @FXML
    private Label roomPriceLabel;
    @FXML
    private Label roomPaidtypeLabel;
    @FXML
    private JFXButton roomBackBtn;
    
    @FXML
    private AnchorPane housePane;
    @FXML
    private TextArea houseDescription;
    @FXML
    private Label houseUsername;
    @FXML
    private Label houseUserTypeLabel;
    @FXML
    private Label houseSearchingLabel;
    @FXML
    private Label houseNumberLabel;
    @FXML
    private Label houseEndedaDate;
    @FXML
    private Label housePriceLabel;
    @FXML
    private Label housePaidType;
    @FXML
    private JFXButton houseBackBtn;
    
    @FXML
    private AnchorPane apartmentPane;
    @FXML
    private TextArea apartmentDescription;
    @FXML
    private Label apartmentProfile;
    @FXML
    private Label apartmentUserTypeLabel;
    @FXML
    private Label apartmentNameLabel;
    @FXML
    private Label apartmentSearchingLabel;
    @FXML
    private Label apartmentEndedDate;
    @FXML
    private Label apartmentPriceLabel;
    @FXML
    private Label apartmentPaidtypeLabel;
    @FXML
    private Label apartmentType;
    @FXML
    private JFXButton apartmentBackBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewDetailsAction(ActionEvent event) {
        String housetype = houseTypeLabel.getText();
        int houseid =Integer.parseInt(id.getText().trim());
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        ResultSet rs=null;
        
        if(housetype.equals("Room")){
            RoomBLL r =new RoomBLL();
            rs= r.getRoomDetails(houseid);
            try {
                while(rs.next()){
                    roomUsername.setText(rs.getString("username"));
                    roomUserTypeLabel.setText(rs.getString("user_type_name"));
                    roomSearchingLabel.setText(rs.getString("searching"));
                    roomNumberLabel.setText(String.valueOf(rs.getInt("room_no")));
                    roomEndedaDate.setText(rs.getDate("end_date").toString());
                    roomPriceLabel.setText(String.valueOf(rs.getInt("price")));
                    roomPaidtypeLabel.setText(rs.getString("paid_type"));
                    
                    if(rs.getString("room_description").equals("")){
                        roomDescription.setText("No Room Description Found");
                    }
                    else {
                        roomDescription.setText(rs.getString("room_description"));
                    }
                   
                }
            } catch (SQLException ex) {
                Logger.getLogger(HouseResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
            animatePane(showInfoPane, rooomPane);
            
                
        }
        else if(housetype.equals("House")){
            HouseBLL h = new HouseBLL();
            rs= h.getHouseDetails(houseid);
            try {
                while(rs.next()){
                    houseUsername.setText(rs.getString("username"));
                    houseUserTypeLabel.setText(rs.getString("user_type_name"));
                    houseSearchingLabel.setText(rs.getString("searching"));
                    houseNumberLabel.setText(rs.getString("house_no"));
                    houseEndedaDate.setText(rs.getDate("end_date").toString());
                    housePriceLabel.setText(String.valueOf(rs.getInt("price")));
                    housePaidType.setText(rs.getString("paid_type"));
                    
                    if(rs.getString("house_description").equals("")){
                        houseDescription.setText("No House Description Found");
                    }
                    else {
                        houseDescription.setText(rs.getString("house_description"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(HouseResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            db.disconnect(conn);
            animatePane(showInfoPane, housePane);
        }
        else{
            ApartmentBLL a = new ApartmentBLL();
            rs= a.getApartmentDetails(houseid);
            try {
                while(rs.next()){
                    apartmentProfile.setText(rs.getString("username"));
                    apartmentType.setText(rs.getString("apartment_type_name"));
                    apartmentUserTypeLabel.setText(rs.getString("user_type_name"));
                    apartmentNameLabel.setText(rs.getString("apartment_name"));
                    apartmentSearchingLabel.setText(rs.getString("searching"));
                    apartmentEndedDate.setText(rs.getDate("end_date").toString());
                    apartmentPriceLabel.setText(rs.getString("price"));
                    apartmentPaidtypeLabel.setText(rs.getString("paid_type"));
                    
                    if(rs.getString("apratment_description").equals("")){
                        apartmentDescription.setText("No Apartment Description Found");
                    }
                    else {
                        apartmentDescription.setText(rs.getString("apratment_description"));
                    }   
                }
            } catch (SQLException ex) {
                Logger.getLogger(HouseResultController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            db.disconnect(conn);
            animatePane(showInfoPane, apartmentPane);
        }
    }

    @FXML
    private void roomBackToShowPane(ActionEvent event) {
        animatePane(rooomPane, showInfoPane);
    }


    @FXML
    private void houseBackToShowPane(ActionEvent event) {
         animatePane(housePane, showInfoPane);
    }


    @FXML
    private void apartmentBackToShowPane(ActionEvent event) {
         animatePane(apartmentPane, showInfoPane);
    }

    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
}
