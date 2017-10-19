/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import data.Apartment;
import data.Booked;
import data.InterestedUser;
import data.NotificationUser;
import javafx.animation.PauseTransition;
import model.ApartmentBLL;
import model.ApartmentTypeBLL;
import model.BookedBLL;
import model.FollowedUserBLL;
import model.HouseBLL;
import model.InterestedUserBLL;
import model.NotificationBLL;
import model.RoomBLL;
import model.UserBLL;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class ApartmentManageController implements Initializable {

    @FXML
    private Label InfoLabel;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private TableView<Apartment> tableApartmentData;
    @FXML
    private TableColumn<Apartment, Integer> idColumn;
    @FXML
    private TableColumn<Apartment, String> apartmentNameColumn;
    @FXML
    private TableColumn<Apartment, String> locationColumn;
    @FXML
    private TableColumn<Apartment, Date> postedDate;
    @FXML
    private TableColumn<Apartment, Date> postEndDateColumn;
    @FXML
    private TableColumn<Apartment, Float> priceColumn;
    @FXML
    private TableColumn<Apartment, String> paidTypeColumn;
   @FXML
    private AnchorPane noresultPane;
    @FXML
    private JFXButton noresultRefreshBtn;
    @FXML
    private JFXButton viewDetailBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private Label errorLabel;
    
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
    private JFXButton backApartmentBtn;
    @FXML
    private JFXButton clearApartmentField;
    @FXML
    private Label errorApartmentInfo;
    @FXML
    private JFXSpinner apartmentSpinner;
    @FXML
    private Label searchApartment;
    @FXML
    private JFXTextField apartmentNameTxt;
    @FXML
    private JFXComboBox<String> statusofApartment;
    @FXML
    private JFXButton updateApartmentBtn;
    @FXML
    private JFXComboBox<String> visibilityApartment;
   
    @FXML
    private AnchorPane viewDetailPane;
    @FXML
    private AnchorPane detailsPane;
    @FXML
    private Label apartmentNameLbl;
    @FXML
    private Label apartmentTypeLbl;
    @FXML
    private Label loocatioonLbl;
    @FXML
    private Label searchingLbl;
    @FXML
    private Label postedDateLbl;
    @FXML
    private Label postEndDateLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private Label paidTypeLbl;
    @FXML
    private Label statusLbl;
    @FXML
    private Label postedAsLbl;
    @FXML
    private Text apartmentDescriptioonTxt;
    @FXML
    private AnchorPane noIntrestedUserPane;
    @FXML
    private TableView<InterestedUser> apartmentInterestedUserTable;
    @FXML
    private TableColumn<InterestedUser,String> interestedUsernameColumn;
    @FXML
    private TableColumn<InterestedUser,Date> dateInterestedColumn;
    @FXML
    private TableColumn<InterestedUser,Integer> interestedIdColumn;
    @FXML
    private AnchorPane noselectUserPane;
    @FXML
    private Label noselectUserLbl;
    @FXML
    private AnchorPane selectUserPane;
    @FXML
    private ComboBox<String> listOfUserInterestedCombo;
    @FXML
    private Label actualPriceLbl;
    @FXML
    private Label selectUserError;
    @FXML
    private JFXButton viewInteresteduser;
    @FXML
    private JFXButton BookUserBtn;
    @FXML
    private AnchorPane userInteretedProfilePane;
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
    private Text profileDescription;
    @FXML
    private Label commisionPriceLbl;
    @FXML
    private Label totalPriceLbl;
    @FXML
    private JFXSpinner spinnerSelectingUser;
    @FXML
    private AnchorPane apartmentInterestedUserPane;
    @FXML
    private JFXButton viewBackBtn;
    
   @FXML
    private Label visibilityLabel;
    @FXML
    private Label activeLabel;
    @FXML
    private MaterialDesignIconView activeGlyph;
    @FXML
    private MaterialDesignIconView inactiveGlyph;
    @FXML
    private AnchorPane profileBlockPane;
    @FXML
    private Label profileBlocknfoLabel;
    
    
   //for apartment Id  
   int id;
   final ObservableList<Apartment> data = FXCollections.observableArrayList();
    final ObservableList<InterestedUser> interestedUser = FXCollections.observableArrayList();
    //used in combo box of intersted user
    final ObservableList<String> username = FXCollections.observableArrayList();
    
   private  ApartmentBLL a;
        
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         a =new ApartmentBLL();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        apartmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("apartmentName"));
        postedDate.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
        postEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        paidTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paidType"));
        
        
        setDataInTable();
        
        interestedIdColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));
        interestedUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateInterestedColumn.setCellValueFactory(new PropertyValueFactory<>("interestedDate"));

        paidTypeApartment.getItems().addAll("Weekly", "Monthly", "Yearly");
        visibilityApartment.getItems().addAll("Private","Public");
        
        
    } 
    
    @FXML
    private void refreshForNoResultPane(ActionEvent event) {
        setDataInTable();
    }
    
    private void refreshFromTablePane(ActionEvent event) {
        setDataInTable();
        
    }
    
     @FXML
    private void getIdofApartment(MouseEvent event) {
        id=tableApartmentData.getSelectionModel().getSelectedItem().getApartmentId();
    }

    
     @FXML
    private void viewToTablePane(ActionEvent event) {
         id = 0;
        animatePane(viewDetailPane, tablePane);
    }
    
    private void apartmentBackAction(ActionEvent event) {
         id = 0;
        animatePane(apartmentPane, tablePane);
    }
    
    private void setDataInTable(){
         data.clear();
         DatabaseConnection db = DatabaseConnection.getInstanceofDB();
         Connection conn = db.connectDB();
        
        ResultSet rs = a.postedApartmentList(Routing.USERNAME);
        try {
            if (!rs.isBeforeFirst()) {
                noresultPane.setVisible(true);
                tablePane.setVisible(false);

            } else {
                 if (noresultPane.isVisible()) {
                    noresultPane.setVisible(false);
                    tablePane.setVisible(true);
                }
                int count=0;
                while (rs.next()) {
                    count++;
                    Apartment apartment =new Apartment();
                    apartment.setApartmentId(rs.getInt("apartment_id"));
                    apartment.setApartmentName(rs.getString("apartment_name"));
                    apartment.setLocation(rs.getString("location"));
                    apartment.setPostedDate(rs.getDate("posted_date"));
                    apartment.setEndDate(rs.getDate("end_date"));
                    apartment.setPrice(rs.getFloat("price"));
                    apartment.setPaidType(rs.getString("paid_type"));
                    
                    data.add(apartment);
                    tableApartmentData.setItems(data);
                }
                InfoLabel.setText(count+" Posted Information Found For Apartment");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);
    }

    

    @FXML
    private void tableToViewActivity(ActionEvent event) {
        if (id < 0 || id == 0) {
            errorLabel.setText("Select Any Room for Detail Viewing");
        } else {
            //set all data in  view
            animatePane(tablePane, viewDetailPane);
            setDataInOtherPane();
            setIntestedUserListInTable();

            //condition check for room status
        }
    }

    @FXML
    private void viewToUpdatePane(ActionEvent event) {
          if (id < 0 || id == 0) {
            errorLabel.setText("Select Any Room for Updating");
        } else {
            // set al data in field
            setDataInOtherPane();
            animatePane(tablePane, apartmentPane);
        }
    }

    @FXML
    private void showInnterestedUserProfile(ActionEvent event) {
        String name = getUsernameFromCombo();
        //check visibilty and followe user permission
        //ahile chai yetikai garchu ma
        if (name != null) {
            userInteretedProfilePane.setVisible(true);
            
            UserBLL u =new UserBLL();
            FollowedUserBLL fu =new FollowedUserBLL();
            BookedBLL b =new BookedBLL();
            
//            Query query = new Query();
            
             if (u.getUserVisibilityStatus(name).equals("Private") ) {
                 profileBlockPane.setVisible(true);
                 profileBlocknfoLabel.setText("This Account is Private.");
             }
             
             else if (fu.getFollowedUserPermission(Routing.USERNAME,name) == null ) {
                 profileBlockPane.setVisible(false);
             }
             else if(fu.getFollowedUserPermission(Routing.USERNAME,name).equals("Block")){
                 profileBlockPane.setVisible(true);
                 profileBlocknfoLabel.setText("Restricted To View Profile");
             }
            else{
                 profileBlockPane.setVisible(false);
             }
             //checks whether user is busy or avaulable
            if(b.getUserStatus(name) >0){
                 activeLabel.setText("Booked");
                 inactiveGlyph.setVisible(true);
                 activeLabel.setTextFill(Paint.valueOf("#eb0d0d"));
                 BookUserBtn.setVisible(false);
             }
             else{
                 activeLabel.setText("Available");
                 activeLabel.setTextFill(Paint.valueOf("#3fb033"));
                 inactiveGlyph.setVisible(false);
                 BookUserBtn.setVisible(true);
             }
            //showing data
             roomLabel.setText(String.valueOf(new RoomBLL().getPosetdRoomNumber(name)));
            houseLabel.setText(String.valueOf(new HouseBLL().getPosetdHouseNumber(name)));
            apartmentLabel.setText(String.valueOf(new ApartmentBLL().getPosetdApartmentNumber(name)));

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
        } else {
            userInteretedProfilePane.setVisible(true);
        }
    }
    
    @FXML
    private void bookedUserForPostedApartment(ActionEvent event) {
        String name = getUsernameFromCombo();
        //alert box if time bho bhane
        if(name !=null){
            Booked bl =new Booked();
            bl.setPostownername(Routing.USERNAME);
            bl.setBookedusername(name);
            bl.setHouseTypeName("Apartment");
            bl.setHouseId(id);
            bl.setRequestDate(java.sql.Date.valueOf(new Validation().getTodaydate()));
            bl.setHouseEndDate(java.sql.Date.valueOf(postEndDateLbl.getText()));
            bl.setRequestStatus("Waiting");
            bl.setActualPrice(Float.valueOf(actualPriceLbl.getText()));
            bl.setCommisionPrice(Float.valueOf(commisionPriceLbl.getText()));
            bl.setTotalCost(Float.valueOf(totalPriceLbl.getText()));
            bl.setUserStatus("UNKNOWN");
            
            
            BookedBLL b =new BookedBLL();
            //booking room
            b.bookedHouse(bl);
            
            //updating apartment status
            a.updateApartmentStatus("Waiting",id); // chaneg
            
            //notify to booked user
            NotificationUser nfu =new NotificationUser();
            nfu.setFromuser(Routing.USERNAME);
            nfu.setTouser(name);
            nfu.setNotificationType(4);
            nfu.setDetails("has asked you to confirm booking of "+Routing.USERNAME+" post about Apartment that you have shown interested");
            nfu.setStatus("Unseen");

            new NotificationBLL().sendNotification(nfu);
            
            animatePane(viewDetailPane, tablePane);
            id=0;
    }
 }
    
    private void setDataInOtherPane(){
        loadApartmentType();
        statusofApartment.getItems().clear();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        
        ResultSet rs = a.getApartmentDetails(id);
        try {
            while (rs.next()) {
                apartmentNameLbl.setText(rs.getString("apartment_name"));
                apartmentTypeLbl.setText(rs.getString("apartment_type_name"));
                postedAsLbl.setText(rs.getString("user_type_name"));
                searchingLbl.setText(rs.getString("searching"));
                loocatioonLbl.setText(rs.getString("location"));
                postedDateLbl.setText(rs.getString("posted_date"));
                postEndDateLbl.setText(rs.getString("end_date"));
                priceLbl.setText(rs.getString("price"));
                paidTypeLbl.setText(rs.getString("paid_type"));
                apartmentDescriptioonTxt.setText(rs.getString("apratment_description"));
                statusLbl.setText(rs.getString("status"));
                visibilityLabel.setText(rs.getString("visibility"));
                
                //setting value in form
                apartmentType.setValue(rs.getString("apartment_type_name"));
                apartmentNameTxt.setText(rs.getString("apartment_name"));
                apartmentLocationTxt.setText(rs.getString("location"));
                java.sql.Date e = (java.sql.Date) rs.getDate("end_date");
                LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(e));
                endDateApartment.setValue(localDate);
                priceApartmentTxt.setText(rs.getString("price"));
                descriptionApartmentTxt.setText(rs.getString("apratment_description"));
                
                switch (rs.getString("paid_type")) {
                    case "Weekly":
                        paidTypeApartment.setValue("Weekly");
                        break;
                    case "Monthly":
                        paidTypeApartment.setValue("Monthly");
                        break;
                    default:
                        paidTypeApartment.setValue("Yearly");
                        break;
                }
                 
                switch (rs.getString("status")) {
                    case "Available":
                        statusofApartment.getSelectionModel().clearSelection();
                        statusofApartment.getItems().addAll("Available","Unavailable");
                        statusofApartment.setValue("Available");
                        break;
                    case "Unavailable":
                        statusofApartment.getSelectionModel().clearSelection();
                        statusofApartment.getItems().addAll("Available","Unavailable");
                        statusofApartment.setValue("Unavailable");
                        break;
                    case "Waiting":
                        statusofApartment.getSelectionModel().clearSelection();
                        statusofApartment.setValue("Waiting");
                        
                        //user cannot select other user for booking
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("You have already selected one User.\n Wait For him to Respond");
                        
                        break;
                        
                   case "Booked":
                        statusofApartment.getSelectionModel().clearSelection();
                        statusofApartment.setValue("Booked");
                        statusofApartment.setDisable(true);
                        
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("This Posted information has been booked");
                        
                        break;
                        
                     default:
                        statusofApartment.getSelectionModel().clearSelection();
                        statusofApartment.setValue("Validity Finish");
                        statusofApartment.getItems().addAll("Available","Unavailable");
                        break;

                }
                
               if(rs.getString("visibility").equals("Private") ){
                   visibilityApartment.setValue("Private");
               }
               else{
                   visibilityApartment.setValue("Public");
               }
                //setting value in booking pane
                Float actuaPrice = rs.getFloat("price");
                actualPriceLbl.setText(rs.getString("price"));
                Float commision = (actuaPrice * 15) / 100;
                commisionPriceLbl.setText(String.valueOf(commision));
                Float total = actuaPrice + commision;
                totalPriceLbl.setText(String.valueOf(total));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApartmentManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }
    
    private void setIntestedUserListInTable() {
        interestedUser.clear();
        username.clear();
        InterestedUserBLL iu =new InterestedUserBLL();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = iu.getUserInterestedinHouseType(id,"Apartment");
        try {
            if (!rs.isBeforeFirst()) {
                noIntrestedUserPane.setVisible(true);
                apartmentInterestedUserPane.setVisible(false);
                noselectUserPane.setVisible(true);
                noselectUserLbl.setText("No user found for booking selected Post");
                selectUserPane.setVisible(false);

            } else {
                 if (noIntrestedUserPane.isVisible()) {
                    noIntrestedUserPane.setVisible(false);
                    apartmentInterestedUserPane.setVisible(true);
                    noselectUserPane.setVisible(false);
                    selectUserPane.setVisible(true);
                }
                while (rs.next()) {
                    InterestedUser userroom = new InterestedUser();
                    userroom.setHouseId(rs.getInt("house_id"));
                    userroom.setUsername(rs.getString("username"));
                    userroom.setInterestedDate(rs.getDate("interested_date"));
                    interestedUser.add(userroom);

                    apartmentInterestedUserTable.setItems(interestedUser);
                    //store all USERNAME of interes ted user here and show in combo box
                    username.add(rs.getString("username"));
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);
        listOfUserInterestedCombo.setItems(username);
        
    }
    
    private void loadApartmentType(){
        ApartmentTypeBLL at =new ApartmentTypeBLL();
        ObservableList<String> aprtmentype = FXCollections.observableArrayList();
        for (String ap : at.listApartmentType()) {
            aprtmentype.add(ap);
        }
        apartmentType.setItems(aprtmentype);        
    }
    
    public int getApartmentType(){
        int i = apartmentType.getSelectionModel().getSelectedIndex();
        i++;
        return i;
    }
 
    public String getUsernameFromCombo() {
        String interesteduser =  listOfUserInterestedCombo.getSelectionModel().getSelectedItem();
        if (interesteduser == null) {
            userInteretedProfilePane.setVisible(false);
            selectUserError.setText("Invalid User Selection");
            return null;
        } else {
            return interesteduser;
        }
    }

    private void errorInApartment(String error) {
        errorApartmentInfo.setText(error);
        apartmentSpinner.setVisible(false);
    }

    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void apartmentBackActioon(ActionEvent event) {
        id = 0;
        animatePane(apartmentPane, tablePane);
    }

    @FXML
    private void clearApartmentField(ActionEvent event) {
        apartmentType.getSelectionModel().clearSelection();
        apartmentNameTxt.setText(null);
        apartmentLocationTxt.setText(null);
        endDateApartment.setValue(null);
        priceApartmentTxt.setText(null);
        paidTypeApartment.getSelectionModel().clearSelection();
        descriptionApartmentTxt.setText(null);
         if (!statusofApartment.isDisable()) {
            statusofApartment.getSelectionModel().clearSelection();
        }
        apartmentType.requestFocus();
                
    }

    @FXML
    private void updateApartmentDetails(ActionEvent event) {
        errorApartmentInfo.setText(null);
        apartmentSpinner.setVisible(true);
//        Query query = new Query();
       PauseTransition pt =new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished((e)->{
                UpdateApartment();
                setDataInTable();
        });
        
        pt.play();
        
   }
    
    private void UpdateApartment(){
         Validation vd = new Validation();
        if (apartmentType.getSelectionModel().isEmpty()) {
            errorInApartment("*Select Valid Type of Apartment");
        } else if (!vd.validateAddress(apartmentNameTxt.getText())) {
            errorInApartment("*Invalid Apartment Name");
        } else if (!vd.validateAddress((apartmentLocationTxt.getText()))) {
            errorInApartment("*Invalid Apartment Location");
        } else if (endDateApartment.getValue() == null) {
            errorInApartment("Please Choose End Date for Post");
        } else if (vd.getDateDifferenceinDay(endDateApartment.getValue().toString()) == 0
                || vd.getDateDifferenceinDay(endDateApartment.getValue().toString()) < 0) {
            errorInApartment("*Invalid Date. Choose new Date");
        } else if (!vd.validatePrice(priceApartmentTxt.getText().trim())) {
            errorInApartment("*Invalid Price");
        } else if (paidTypeApartment.getSelectionModel().isEmpty()) {
            errorInApartment("*Invalid Payment Method");
        } else if (!vd.validateTextArea(descriptionApartmentTxt.getText().trim())) {
            errorInApartment("*Invalid Apartment Description");
        } else if (statusofApartment.getSelectionModel().isEmpty()) {
            errorInApartment("*Invalid Post Status");
        } 
         else if(visibilityApartment.getSelectionModel().isEmpty()){
            errorInApartment("*Invalid Apartment Visibilty");
        }else {
             errorApartmentInfo.setText("");
             apartmentSpinner.setVisible(false);
            Apartment apartment = new Apartment();
            apartment.setApartmentTypeId(getApartmentType());
            apartment.setApartmentName(apartmentNameTxt.getText());
            apartment.setLocation(apartmentLocationTxt.getText());
            apartment.setEndDate(java.sql.Date.valueOf(endDateApartment.getValue().toString()));
            apartment.setPrice(Float.parseFloat(priceApartmentTxt.getText().trim()));
            apartment.setPaidType(paidTypeApartment.getSelectionModel().getSelectedItem());
            apartment.setApratmentDescription(descriptionApartmentTxt.getText());
            apartment.setStatus(statusofApartment.getSelectionModel().getSelectedItem());
            apartment.setVisibility(visibilityApartment.getSelectionModel().getSelectedItem());
            apartment.setApartmentId(id);
            // query for insert
            a.updateApartmentDetails(apartment);
            
            Notifications nf = Notifications.create()
                    .title("Apartment Details")
                    .text("Updated Apartment Details Sucessfully")
                    .position(Pos.BOTTOM_RIGHT)
                    .hideAfter(Duration.seconds(4))
                    .graphic(null);
            nf.show();
            id=0;
            animatePane(apartmentPane, tablePane);
        } 
    }


   
}
