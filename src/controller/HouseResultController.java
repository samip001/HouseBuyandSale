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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import data.InterestedUser;
import data.NotificationUser;
import model.ApartmentBLL;
import model.FolllowRequestBLL;
import model.HouseBLL;
import model.InterestedUserBLL;
import model.NotificationBLL;
import model.RoomBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class HouseResultController implements Initializable {

    @FXML
    public Circle imageCircle;
    @FXML
    public Label usernameLabel;
    @FXML
    public Label posteddate;
    @FXML
    public Label statusLabel;
    @FXML
    public Label visibilityLabel;
    @FXML
    public JFXButton viewDetailsBtn;
    @FXML
    public Label locationLabel;
    @FXML
    public Label houseTypeLabel;
    @FXML
    public Label id;
    
    //for Room Pane
    @FXML
    private AnchorPane rooomPane;
    @FXML
    private TextArea roomDescription;
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
    private Label roomUserTypeLabel;
    @FXML
    private Label roomUsername;
    @FXML
    private JFXButton roomShowIntrestedBtn;
    @FXML
    private JFXButton roomBackBtn;
    @FXML
    
    //for House Pane
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
    private JFXButton houseShowIntrestedBtn;
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
    private JFXButton apartmentshowIntrestedBtn;
    @FXML
    private JFXButton apartmentBackBtn;
    @FXML
    private AnchorPane showInfoPane;
    
    //final pane after intrested
    @FXML
    private AnchorPane intrestedPane;
    @FXML
    private JFXButton backafterIntrestedBtn;
    @FXML
    private Label intrestedLabel;
    @FXML
    private Label apartmentType;
    
    private InterestedUserBLL iu;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iu =new InterestedUserBLL();
    }    

    @FXML
    private void viewDetailsAction(ActionEvent event) {
//        Query query =new Query();
        String housetype = houseTypeLabel.getText();
        int houseid =Integer.parseInt(id.getText().trim());
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        ResultSet rs =null;
        
        InterestedUserBLL i =new InterestedUserBLL();
        
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
                    
                    //change for validation tommorroq
                    if (i.checkUserPrevoiusIntrested(Routing.USERNAME,houseTypeLabel.getText(), Integer.valueOf(id.getText()))) {
                        roomShowIntrestedBtn.setText("Shown Intrested");
                        roomShowIntrestedBtn.setDisable(true);
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
                    
                    //change in interested table
                   if (i.checkUserPrevoiusIntrested(Routing.USERNAME,houseTypeLabel.getText(), Integer.valueOf(id.getText()))) {
                        houseShowIntrestedBtn.setText("Intrested Done");
                        houseShowIntrestedBtn.setDisable(true);
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
                    
                    //check in interestd able
                    if (i.checkUserPrevoiusIntrested(Routing.USERNAME,houseTypeLabel.getText(), Integer.valueOf(id.getText()))) {
                        apartmentshowIntrestedBtn.setText("Intrested Done");
                        apartmentshowIntrestedBtn.setDisable(true);
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
    private void roomIntrested(ActionEvent event) {

//        Query query = new Query();
//        query.userInterestedinRoom(riu);

        InterestedUser interesteduser = new InterestedUser();
        interesteduser.setUsername(Routing.USERNAME);
        interesteduser.setHouseTypeName(houseTypeLabel.getText());
        interesteduser.setHouseId(Integer.valueOf(id.getText()));
        interesteduser.setInterestedDate(java.sql.Date.valueOf(new Validation().getTodaydate()));

         iu.userInterestedInHouse(interesteduser);
        //notifying posted room information user
        notifeduser();

        intrestedLabel.setText("Your intrest for this " + houseTypeLabel.getText() + " has been recorded");
        roomShowIntrestedBtn.setVisible(false);
        animatePane(rooomPane, intrestedPane);
    }

    @FXML
    private void roomBackToShowPane(ActionEvent event) {
        animatePane(rooomPane, showInfoPane);
        
    }

    @FXML
    private void houseIntrested(ActionEvent event) {
          InterestedUser interesteduser =new InterestedUser();
          interesteduser.setUsername(Routing.USERNAME);
          interesteduser.setHouseTypeName(houseTypeLabel.getText());
          interesteduser.setHouseId(Integer.valueOf(id.getText()));
          interesteduser.setInterestedDate(java.sql.Date.valueOf(new Validation().getTodaydate()));
          
        
         iu.userInterestedInHouse(interesteduser);
        
        //notifying posted room information user
        notifeduser();
        
        intrestedLabel.setText("Your intrest for this "+houseTypeLabel.getText()+" has been recorded");
        houseShowIntrestedBtn.setVisible(false);
        
        animatePane(housePane, intrestedPane);
    }

    @FXML
    private void houseBackToShowPane(ActionEvent event) {
        animatePane(housePane, showInfoPane);
        
    }

    @FXML
    private void apartmentIntrested(ActionEvent event) {
          InterestedUser interesteduser =new InterestedUser();
          interesteduser.setUsername(Routing.USERNAME);
          interesteduser.setHouseTypeName(houseTypeLabel.getText());
          interesteduser.setHouseId(Integer.valueOf(id.getText()));
          interesteduser.setInterestedDate(java.sql.Date.valueOf(new Validation().getTodaydate()));
          
          iu.userInterestedInHouse(interesteduser);
        
        
        //notifying posted room information user
        notifeduser();
        
        intrestedLabel.setText("Your intrest for this "+houseTypeLabel.getText()+" has been recorded");
        apartmentshowIntrestedBtn.setVisible(false);
        
        animatePane(apartmentPane, intrestedPane);
     }

    @FXML
    private void apartmentBackToShowPane(ActionEvent event) {
       animatePane(apartmentPane,showInfoPane);
            
    }

    @FXML
    private void IntrerstedBacktoShowPane(ActionEvent event) {
        animatePane(intrestedPane, showInfoPane);
    }
    
    //for n in otification send house by current login user
    private void notifeduser(){
        NotificationUser nfu =new NotificationUser();
        nfu.setFromuser(Routing.USERNAME);
        nfu.setTouser(usernameLabel.getText().trim());
        nfu.setNotificationType(3);
        nfu.setDetails("has Shown in intrested in Your "+houseTypeLabel.getText()+" Post");
        nfu.setStatus("Unseen");
        
        new NotificationBLL().sendNotification(nfu);
        
    }
    
     private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
}
