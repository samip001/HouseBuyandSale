/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import data.Apartment;
import data.House;
import data.Room;
import model.ApartmentBLL;
import model.ApartmentTypeBLL;
import model.HouseBLL;
import model.RoomBLL;
import model.UserTypeBLL;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class NewHouseController implements Initializable {

    @FXML
    private AnchorPane roomPane;
    @FXML
    private JFXTextField roomLocationTxt;
    @FXML
    private JFXTextField roonNoTxt;
    @FXML
    private JFXDatePicker dateRent;
    @FXML
    private JFXTextField priceRoom;
    @FXML
    private JFXComboBox<String> paidTypeRent;
    @FXML
    private JFXTextArea descriptionRentTxt;
    @FXML
    private JFXComboBox<String> visibiltyRentType;
    @FXML
    private JFXButton clearRoomFieldBtn;
    @FXML
    private JFXButton saveRoomInfoBtn;
    @FXML
    private Label errorRentInfo;
    @FXML
    private JFXSpinner rentSpinner;
    @FXML
    private AnchorPane selectPane;
    @FXML
    private JFXComboBox<String> userTypeCombo;
    @FXML
    private JFXComboBox<String> houseTypeCombo;
    @FXML
    private Label houseTypeLabel;
    @FXML
    private JFXButton addBtn;
    @FXML
    private Label pgNo;
    @FXML
    private Label hoNo;
    @FXML
    private Label asNo;
    @FXML
    private Label apNo;
    @FXML
    private Label hsNo;
    @FXML
    private Label hbNo;
    @FXML
    private Label searchingRoom;
    @FXML
    private AnchorPane apartmentPane;
    @FXML
    private JFXComboBox<String> apartmentType;
    @FXML
    private JFXTextField apartmentLocationTxt;
    @FXML
    private JFXDatePicker endDateApartment;
    @FXML
    private JFXTextField priceApartmentTxt;
    @FXML
    private JFXComboBox<String> paidTypeApartment;
    @FXML
    private JFXTextArea descriptionApartmentTxt;
    @FXML
    private JFXComboBox<String> visibiltyApartment;
    @FXML
    private JFXButton clearApartmentField;
    @FXML
    private JFXButton postApartmentBtn;
    @FXML
    private Label errorApartmentInfo;
    @FXML
    private JFXSpinner apartmentSpinner;
    @FXML
    private AnchorPane housePane;
    @FXML
    private Label searchHouse;
    @FXML
    private JFXTextField houseNumTxt;
    @FXML
    private JFXTextField houseLocationTxt;
    @FXML
    private JFXDatePicker endDateHouse;
    @FXML
    private JFXTextField priceHouseTxt;
    @FXML
    private JFXComboBox<String> paidTypeHouse;
    @FXML
    private JFXComboBox<String> houseForCombo;
    @FXML
    private JFXTextArea descriptionHouseTxt;
    @FXML
    private JFXComboBox<String> visibiltyHouse;
    @FXML
    private JFXButton clearHouseFieldBtn;
    @FXML
    private JFXButton postHouseBtn;
    @FXML
    private Label errorHouseInfo;
    @FXML
    private JFXSpinner houseSpinner;
    @FXML
    private JFXSpinner selectSpinner;
    @FXML
    private Label selecterror;
    @FXML
    private JFXButton backRoomBtn;
    @FXML
    private JFXButton backApartmentBtn;
    @FXML
    private JFXButton backHouseBtn;
    @FXML
    private Label searchApartment;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField apartmentNameTxt;
    
    
    
//    private Query query;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //to list user type
        listUserType();
        //to load payment method
        loadPaymentMethod(paidTypeRent);
        loadPaymentMethod(paidTypeHouse);
        loadPaymentMethod(paidTypeApartment);
        
        //to load visibilty of post
        loadVisibility(visibiltyRentType);
        loadVisibility(visibiltyHouse);
        loadVisibility(visibiltyApartment);
        }   
    
    
    
    
    private void listUserType(){
        UserTypeBLL ut =new UserTypeBLL();
        ObservableList<String> usertype = FXCollections.observableArrayList();
        for (String userTpe : ut.listUserType()) {
            usertype.add(userTpe);
        }
        userTypeCombo.setItems(usertype);
    
    }
    
    @FXML
    private void userTypeChange(ActionEvent event) {
        if(userTypeCombo.getSelectionModel().isEmpty()){
            houseTypeCombo.getItems().clear();
        }
        else if(userTypeCombo.getSelectionModel().getSelectedItem().equals("Paying Guest")){
            houseTypeCombo.getItems().clear();
            houseTypeCombo.getItems().addAll("Room","House");
        }
        else if(userTypeCombo.getSelectionModel().getSelectedItem().equals("House Owner")){
            houseTypeCombo.getItems().clear();
            houseTypeCombo.getItems().add("Room");
        }
        else if(userTypeCombo.getSelectionModel().getSelectedItem().equals("House Saler") ||
                userTypeCombo.getSelectionModel().getSelectedItem().equals("House Buyer") ){
            houseTypeCombo.getItems().clear();
            houseTypeCombo.getItems().add("House");
        }
        else{
            houseTypeCombo.getItems().clear();
            houseTypeCombo.getItems().add("Apartment");
        }
    }

    @FXML
    private void chooseUserandHouseBtn(ActionEvent event) {
        selecterror.setText("");
        selectSpinner.setVisible(true);
        String usertype = userTypeCombo.getSelectionModel().getSelectedItem();
        String housetype = houseTypeCombo.getSelectionModel().getSelectedItem();
        PauseTransition pt =new PauseTransition(Duration.seconds(2));
        pt.setOnFinished((e)->{
                FadeAnimation fd =new FadeAnimation();
                if(userTypeCombo.getSelectionModel().isEmpty() || houseTypeCombo.getSelectionModel().isEmpty()){
                    selecterror.setText("InvalidUsertype and House Type");
                    selectSpinner.setVisible(false);
                }
                else if(usertype.equals("Paying Guest") && housetype.equals("Room")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, roomPane);
                    searchingRoom.setText("Room");
                    roomPane.setVisible(true);
                }
                else if(usertype.equals("Paying Guest") && housetype.equals("House")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, housePane);
                    searchHouse.setText("House");
                    houseForCombo.getItems().add("Rent");
                    housePane.setVisible(true);
                }
                else if(usertype.equals("House Owner") && housetype.equals("Room")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, roomPane);
                    searchingRoom.setText("Paying Guest");
                    roomPane.setVisible(true);
                }
                else if(usertype.equals("Apartment Buyer") && housetype.equals("Apartment")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, apartmentPane);
                    searchApartment.setText("Apartment Saler");
                    loadApartmentType();
                    apartmentPane.setVisible(true);
                }
                else if(usertype.equals("Apartment Saler") && housetype.equals("Apartment")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, apartmentPane);
                    searchApartment.setText("Apartment Buyer");
                    loadApartmentType();
                    apartmentPane.setVisible(true);
                }
                else if(usertype.equals("House Buyer") && housetype.equals("House")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, housePane);
                    searchHouse.setText("House Saler");
                    houseForCombo.getItems().add("Buy");
                    housePane.setVisible(true);
                }
                else if(usertype.equals("House Saler") && housetype.equals("House")){
                    selectSpinner.setVisible(false);
                    fd.fadeOut(selectPane, housePane);
                    searchHouse.setText("House Buyer");
                    houseForCombo.getItems().add("Sale");
                    housePane.setVisible(true);
                }
                
                else{
                    System.out.println("else of use and house");
                }
                    
        });
        
        pt.play();
    }

    @FXML
    private void roomBackAction(ActionEvent event) {
        backAction(roomPane, selectPane);
        userTypeCombo.getSelectionModel().clearSelection();
        houseTypeCombo.getSelectionModel().clearSelection();
    }

    @FXML
    private void apartmentBackActioon(ActionEvent event) {
      backAction(apartmentPane, selectPane);
      userTypeCombo.getSelectionModel().clearSelection();
      houseTypeCombo.getSelectionModel().clearSelection();
    }

    @FXML
    private void houseBackAction(ActionEvent event) {
       backAction(housePane, selectPane);
       userTypeCombo.getSelectionModel().clearSelection();
       houseTypeCombo.getSelectionModel().clearSelection();
    }
    
    
    //for all back action with animation
    public void backAction(AnchorPane hidepane,AnchorPane showpane){
        FadeAnimation fd =new FadeAnimation();
        fd.fadeOut(hidepane, showpane);
        hidepane.setVisible(false);
        showpane.setVisible(true);
    }
    
    private void loadApartmentType(){
        ApartmentTypeBLL at =new ApartmentTypeBLL();
        ObservableList<String> aprtmentype = FXCollections.observableArrayList();
        for (String ap : at.listApartmentType()) {
            aprtmentype.add(ap);
        }
        apartmentType.setItems(aprtmentype);
        
    }
    
    public void loadPaymentMethod(JFXComboBox<String> payment){
        payment.getItems().addAll("Weekly","Monthly","Yearly");
    }
    
    public void loadVisibility(JFXComboBox<String> payment){
        payment.getItems().addAll("Private","Public");
    }

    @FXML
    private void clearRoomField(ActionEvent event) {
        rentSpinner.setVisible(true);
        PauseTransition pt =new PauseTransition(Duration.seconds(1));
        pt.setOnFinished((e)->{
            rentSpinner.setVisible(false);
            roomLocationTxt.setText(null);
            roonNoTxt.setText(null);
            dateRent.setValue(null);
            priceRoom.setText(null);
            paidTypeRent.getSelectionModel().clearSelection();
            descriptionRentTxt.setText(null);
            visibiltyRentType.getSelectionModel().clearSelection();
            roomLocationTxt.requestFocus();
            
        });
        pt.play();
        
    }

    @FXML
    private void postRoomDetails(ActionEvent event) {
        errorRentInfo.setText(null);
        rentSpinner.setVisible(true);
        Validation vd =new Validation();
        if(!vd.validateTextField(roomLocationTxt.getText().trim())){
            errorInRoom("* Invalid Room Location");
        }
        else if(!vd.validateRoomNumber(roonNoTxt.getText().trim())){
            errorInRoom("*Enter Valid Room Number");
        }
        else if(dateRent.getValue() == null){
            errorInRoom("*End Date Cannot be Empty ");
        }
        else if(vd.getDateDifferenceinDay(dateRent.getValue().toString()) == 0 || 
                vd.getDateDifferenceinDay(dateRent.getValue().toString()) < 0){
            errorInRoom("*Invalid Date. Choose new Date.");
        }
        else if(!vd.validatePrice(priceRoom.getText().trim())){
            errorInRoom("*Enter Valid Price For Room ");
        }
        else if(paidTypeRent.getSelectionModel().isEmpty()){
            errorInRoom("*Invalid Payment Method");
        }
        else if(!vd.validateTextArea(descriptionRentTxt.getText().trim())){
            errorInRoom("*Invalid Room Description");
        }
        else if (visibiltyRentType.getSelectionModel().isEmpty()){
            errorInRoom("*Invalid Post Visibility");
        }
        else{
            //
            Room room =new Room();
            room.setUsername(Routing.USERNAME);
            room.setUserTypeId(getUsertType());
            room.setSearching(searchingRoom.getText());
            room.setRoomLocation(roomLocationTxt.getText());
            room.setRoomNo(Integer.parseInt(roonNoTxt.getText()));
            room.setPostedDate(java.sql.Date.valueOf(vd.getTodaydate()));
            room.setEndDate((java.sql.Date.valueOf(dateRent.getValue().toString().trim())));
            room.setPrice(Float.parseFloat(priceRoom.getText().trim()));
            room.setPaidType(paidTypeRent.getSelectionModel().getSelectedItem());
            room.setRoomDescription(descriptionRentTxt.getText());
            room.setRoomStatus("Available");
            room.setRoomVisibility(visibiltyRentType.getSelectionModel().getSelectedItem());
            
            RoomBLL r =new RoomBLL();
            r.postRoomDetails(room);
             Notifications nf = Notifications.create()
                            .title("Room Details")
                            .text("Posted Room Details Sucessfully")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
            nf.show();
            
            backAction(roomPane, selectPane);
            userTypeCombo.getSelectionModel().clearSelection();
            houseTypeCombo.getSelectionModel().clearSelection();
            
        }
        
    }

    @FXML
    private void clearApartmentField(ActionEvent event) {
        houseSpinner.setVisible(true);
        PauseTransition pt =new PauseTransition(Duration.seconds(1));
        pt.setOnFinished((e)->{
            houseSpinner.setVisible(false);
             apartmentType.getSelectionModel().clearSelection();
             apartmentNameTxt.setText(null);
            apartmentLocationTxt.setText(null);
            endDateApartment.setValue(null);
            priceApartmentTxt.setText(null);
            paidTypeApartment.getSelectionModel().clearSelection();
            descriptionApartmentTxt.setText(null);
            visibiltyApartment.getSelectionModel().clearSelection();
            apartmentType.requestFocus();
        });
       
        pt.play();
        
    }


    @FXML
    private void addApartmentDetails(ActionEvent event) {
        errorApartmentInfo.setText(null);
        apartmentSpinner.setVisible(true);
        Validation vd =new Validation();
        if(apartmentType.getSelectionModel().isEmpty()){
            errorInApartment("*Select Valid Type of Apartment");
        }
        else if(!vd.validateAddress(apartmentNameTxt.getText())){
            errorInApartment("*Invalid Apartment Name");
        }
        else if(!vd.validateAddress((apartmentLocationTxt.getText()))){
            errorInApartment("*Invalid Apartment Location");
        }
        else if(endDateApartment.getValue() == null){
            errorInApartment("Please Choose End Date for Post");
        }
        else if(vd.getDateDifferenceinDay(endDateApartment.getValue().toString()) == 0 ||
                vd.getDateDifferenceinDay(endDateApartment.getValue().toString()) < 0){
                errorInApartment("*Invalid Date. Choose new Date");
        }
        
        else if (!vd.validatePrice(priceApartmentTxt.getText().toString().trim())){
            errorInApartment("*Invalid Price");
        }
        else if(paidTypeApartment.getSelectionModel().isEmpty()){
            errorInApartment("*Invalid Payment Method");
        }
        else if(!vd.validateTextField(descriptionApartmentTxt.getText().trim())){
            errorInApartment("*Invalid Room Description");
        }
        else if (visibiltyApartment.getSelectionModel().isEmpty()){
            errorInApartment("*Invalid Post Visibility");
        }
        else{
            Apartment apartment  =new Apartment();
            apartment.setUsername(Routing.USERNAME);
            apartment.setUserTypeId(getUsertType());
            apartment.setSearching(searchApartment.getText());
            apartment.setApartmentTypeId(getApartmentType());
            apartment.setApartmentName(apartmentNameTxt.getText());
            apartment.setLocation(apartmentLocationTxt.getText());
            apartment.setPostedDate(java.sql.Date.valueOf(vd.getTodaydate()));
            apartment.setEndDate(java.sql.Date.valueOf(endDateApartment.getValue().toString()));
            apartment.setLocation(apartmentLocationTxt.getText());
            apartment.setPrice(Float.parseFloat(priceApartmentTxt.getText().trim()));
            apartment.setPaidType(paidTypeApartment.getSelectionModel().getSelectedItem());
            apartment.setApratmentDescription(descriptionApartmentTxt.getText());
            apartment.setStatus("Available");
            apartment.setVisibility(visibiltyApartment.getSelectionModel().getSelectedItem());
            
            // query for insert
            ApartmentBLL a =new ApartmentBLL();
            a.postApartmentDetails(apartment);
            Notifications nf = Notifications.create()
                            .title("Apartment Details")
                            .text("Posted Apartment Details Sucessfully")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
            nf.show();
            backAction(apartmentPane, selectPane);
            userTypeCombo.getSelectionModel().clearSelection();
            houseTypeCombo.getSelectionModel().clearSelection();
            
            
        }
    }

    @FXML
    private void cleaHouseField(ActionEvent event) {
        houseSpinner.setVisible(true);
        PauseTransition pt = new PauseTransition(Duration.seconds(1));
        pt.setOnFinished((e)->{
            houseSpinner.setVisible(false);
            houseNumTxt.setText(null);
            houseLocationTxt.setText(null);
            endDateHouse.setValue(null);
            priceHouseTxt.setText(null);
            houseForCombo.getSelectionModel().clearSelection();
            paidTypeHouse.getSelectionModel().clearSelection();
            descriptionHouseTxt.setText(null);
            visibiltyHouse.getSelectionModel().clearSelection();
            houseNumTxt.requestFocus();
            
        });
       pt.play();
    }

    @FXML
    private void addHouseDetails(ActionEvent event) {
        houseSpinner.setVisible(true);
        errorHouseInfo.setText(null);
        Validation vd =new Validation();
        
        if(!vd.validateAddress(houseNumTxt.getText() )){
            errorInHouse("*Invalid House Number");
        }
        else if (!vd.validateAddress(houseLocationTxt.getText() )){
            errorInHouse("*Invalid Location of House");
        }
        else if (houseForCombo.getSelectionModel().isEmpty()){
            errorInHouse("*Select House for "+userTypeCombo.getSelectionModel().getSelectedItem());
        }
        else if(endDateHouse.getValue()== null){
            errorInHouse("End Date Cannot be empty");
        }
        else if(vd.getDateDifferenceinDay(endDateHouse.getValue().toString()) == 0 
                || vd.getDateDifferenceinDay(endDateHouse.getValue().toString()) <0){
            errorInHouse("*Invalid Date. Choose new Date");
        }
        else if(!vd.validatePrice(priceHouseTxt.getText())){
            errorInHouse("*Invalid House Price");
        }
        else if(paidTypeHouse.getSelectionModel().isEmpty()){
            errorInHouse("*Select Valid Payment Method ");
        }
        else if (!vd.validateTextArea(descriptionHouseTxt.getText())){
            errorInHouse("*House Description Not Valid");
        }
        else if(visibiltyHouse.getSelectionModel().isEmpty()){
            errorInHouse("Select Post Visibility");
        }
        else{
            House house =new House();
            house.setUsername(Routing.USERNAME);
            house.setUserTypeId(getUsertType());
            house.setSearching(searchHouse.getText());
            house.setHouseNo(houseNumTxt.getText());
            house.setLocation(houseLocationTxt.getText());
            house.setHouseFor(houseForCombo.getSelectionModel().getSelectedItem());
            house.setPostedDate(java.sql.Date.valueOf(vd.getTodaydate()));
            house.setEndDate(java.sql.Date.valueOf(endDateHouse.getValue().toString()));
            house.setPrice(Float.parseFloat(priceHouseTxt.getText().trim()));
            house.setPaidType(paidTypeHouse.getSelectionModel().getSelectedItem());
            house.setHouseDescription(descriptionHouseTxt.getText());
            house.setStatus("Available");
            house.setHouseVisibility(visibiltyHouse.getSelectionModel().getSelectedItem());
            
            //query
            HouseBLL h =new HouseBLL();
            h.postHouseDetails(house);
            
            Notifications nf = Notifications.create()
                            .title("House")
                            .text("Posted House Details Sucessfully")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
            nf.show();
            backAction(housePane, selectPane);
            userTypeCombo.getSelectionModel().clearSelection();
            houseTypeCombo.getSelectionModel().clearSelection();
            
        }
    }
    
    public void errorInRoom(String error){
        if(rentSpinner.isVisible()){
            errorRentInfo.setText(error);
            rentSpinner.setVisible(false);
        }
    }
    
    public void errorInApartment(String error){
        if(apartmentSpinner.isVisible()){
            errorApartmentInfo.setText(error);
            apartmentSpinner.setVisible(false);
        }
    }
    public void errorInHouse(String error){
        if(houseSpinner.isVisible()){
            errorHouseInfo.setText(error);
            houseSpinner.setVisible(false);
        }
    }
    
    public int getUsertType(){
        int i = userTypeCombo.getSelectionModel().getSelectedIndex();
        i++;
        return i;
    }
    
    public int getHouseType(){
        if(houseTypeCombo.getSelectionModel().getSelectedItem().equals("House")){
            return 1;
        }
        else if (houseTypeCombo.getSelectionModel().getSelectedItem().equals("Apartment")){
            return 2;
        }
        else {
            return 3;
        }
    }
    
    public int getApartmentType(){
        int i = apartmentType.getSelectionModel().getSelectedIndex();
        i++;
        return i;
    }
}
