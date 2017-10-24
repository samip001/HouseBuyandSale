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
import data.Booked;
import data.InterestedUser;
import data.NotificationUser;
import data.Room;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ApartmentBLL;
import model.BookedBLL;
import model.FollowedUserBLL;
import model.HouseBLL;
import model.InterestedUserBLL;
import model.NotificationBLL;
import model.RoomBLL;
import model.UserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class RoomManageController implements Initializable {

    @FXML
    private TableView<Room> tableRoomData;
    @FXML
    private TableColumn<Room,Integer> idColumn;
    @FXML
    private TableColumn<Room,String> locationColumn;
    @FXML
    private TableColumn<Room,Integer> roomRequiredColumn;
    @FXML
    private TableColumn<Room,Date> postedDate;
    @FXML
    private TableColumn<Room,Date> postEndDateColumn;
    @FXML
    private TableColumn<Room,Float> priceColumn;
    
    @FXML
    private TableColumn<Room,String> paidTypeColumn;
    
    final ObservableList<Room> data = FXCollections.observableArrayList();
    final ObservableList<InterestedUser> interestedUser = FXCollections.observableArrayList();
    //used in combo box of intersted user
    final ObservableList<String> username = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane tablePane;
    @FXML
    private Label InfoLabel;
    @FXML
    private AnchorPane noresultPane;
    @FXML
    private JFXButton viewDetailBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane updateRoomPane;
    @FXML
    private Label updateSearchingRoomLabel;
    @FXML
    private JFXTextField updateRoomLocationTxt;
    @FXML
    private JFXTextField updateRoonNoTxt;
    @FXML
    private JFXDatePicker updateEndDateRoom;
    @FXML
    private JFXTextField updatePriceRoom;
    @FXML
    private JFXComboBox<String> updatePaidTypeRent;
    @FXML
    private JFXTextArea updateRoomDescriptionTxt;
    @FXML
    private JFXComboBox<String> statusUpdateProfile;
    @FXML
    private JFXComboBox<String> visibilityRoom;
   
    @FXML
    private JFXButton backRoomBtn;
    @FXML
    private JFXButton clearRoomFieldBtn;
    @FXML
    private JFXButton updateRoomInfoBtn;
    @FXML
    private Label errorRoomInfo;
    @FXML
    private JFXSpinner roomSpinner;
    @FXML
    private AnchorPane viewDetailPane;
    @FXML
    private JFXButton viewDetailBack;
    @FXML
    private AnchorPane detailsPane;
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
    
    
    
    int id =-1; // for getting id of room
    @FXML
    private JFXButton noresultRefreshBtn;
    @FXML
    private Label posetdAsLblDtl;
    @FXML
    private Label searchingLblDtl;
    @FXML
    private Label locationLblDtl;
    @FXML
    private Label roomNoLblDtl;
    @FXML
    private Label postedDatLblDTl;
    @FXML
    private Label postEndDateLblDtl;
    @FXML
    private Label priceLblDtl;
    @FXML
    private Label statusLblDtl;
    @FXML
    private Text descriptionLblDtl;
    @FXML
    private Label paidTypeLblDtl;
    
    
    //table of interested user
    @FXML
    private AnchorPane userInteretedProfilePane;
    @FXML
    private TableView<InterestedUser> RoomInterestedUserTable;
    @FXML
    private TableColumn<InterestedUser, String> interestedUsernameColumn;
    @FXML
    private TableColumn<InterestedUser, Date> dateInterestedColumn;
    @FXML
    private TableColumn<InterestedUser, Integer> interestedIdColumn;
    @FXML
    private AnchorPane noIntrestedUserPane;
    
    
    //Booked Pane
    @FXML
    private AnchorPane noselectUserPane;
    @FXML
    private Label noselectUserLbl;
    @FXML
    private ComboBox<String> listOfUserInterestedCombo;
    @FXML
    private JFXButton BookUserBtn;
    @FXML
    private AnchorPane roomInterestedUserPane;
    @FXML
    private Text profileDescription;
    @FXML
    private JFXButton viewInteresteduser;
    @FXML
    private Label selectUserError;
    @FXML
    private JFXSpinner spinnerSelectingUser;
    @FXML
    private Label actualPriceLbl;
    @FXML
    private Label commisionPriceLbl;
    @FXML
    private Label totalPriceLbl;
    @FXML
    private AnchorPane selectUserPane;
    @FXML
    private Label visibiltyLabel;
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
    
    private RoomBLL r;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        r =new RoomBLL();
        // for own room post
        idColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));
        roomRequiredColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        postedDate.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
        postEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        paidTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paidType"));
        setDataInTable();
        
        //for interested user list
        interestedIdColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));
        interestedUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateInterestedColumn.setCellValueFactory(new PropertyValueFactory<>("interestedDate"));
        
        //check if that post gets booked the tabpane is disable
        
        //settiing value in update profile coombo
        updatePaidTypeRent.getItems().addAll("Weekly","Monthly","Yearly");
        statusUpdateProfile.getItems().addAll("Available","Unavailable","Booked");
        visibilityRoom.getItems().addAll("Public","Private");
        
    }    

    @FXML
    private void viewDetails(MouseEvent event) {
        errorLabel.setText("");
        id = tableRoomData.getSelectionModel().getSelectedItem().getRoomId();
    }
    
    @FXML
    private void refreshForNoResultPane(ActionEvent event) {
        setDataInTable();
    }

    private void refreshFromTablePane(ActionEvent event) {
        setDataInTable();
    }
    
    
    private void setDataInTable(){
        data.clear();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = r.postedRoomList(Routing.USERNAME);
        try {
            if (!rs.isBeforeFirst()) {
                noresultPane.setVisible(true);
                tablePane.setVisible(false);

            } else {
                //for refreshnig table if no data found after adding post
                if(noresultPane.isVisible()){
                    noresultPane.setVisible(false);
                    tablePane.setVisible(true);
                }
                int count = 0;
                while (rs.next()) {
                    count++;
                    Room room = new Room();
                    room.setRoomId(rs.getInt("room_id"));
                    room.setRoomLocation(rs.getString("location"));
                    room.setRoomNo(rs.getInt("room_no"));
                    room.setPostedDate(rs.getDate("posted_date"));
                    room.setEndDate(rs.getDate("end_date"));
                    room.setPrice(rs.getFloat("price"));
                    room.setPaidType(rs.getString("paid_type"));
                   
                    data.add(room);

                    tableRoomData.setItems(data);
                }
                InfoLabel.setText(count + " Posted Information Found For Room");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);
    }

    @FXML
    private void tableToViewActivity(ActionEvent event) {
         if(id<0 || id==0){
            errorLabel.setText("Select Room for Detail Viewing");
        }
         else{
            //set all data in  view
             animatePane(tablePane, viewDetailPane);
             setDataInOtherPane();
             setIntestedUserListInTable();
          }
    }

    @FXML
    private void tableToUpdatePane(ActionEvent event) {
        if(id<0 || id  ==0 ){
            errorLabel.setText("Select Room for Updating");
        }
        else{
             //check it is booked or not
              if("Booked".equals(new RoomBLL().getStatus(id))){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.initModality(Modality.APPLICATION_MODAL);
                  alert.setTitle("Update Information");
                  alert.setHeaderText(null);
                  alert.setContentText("Selected Room is Booked.\n\nEdit service is unavailable");

                  Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                  alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));
                  alertstage.show();

              }
              else if("Validity Finish".equals(new RoomBLL().getStatus(id))){
                  animatePane(tablePane, updateRoomPane);  
                  setDataInOtherPane();
                  errorInRoom("Invalid End Date. Please select Valid date");
              }
              else{
                  // set al data in field
                  setDataInOtherPane();
                  errorInRoom("");
                  animatePane(tablePane, updateRoomPane);
              }
        }
        
    }

    @FXML
    private void updateToTablePane(ActionEvent event) {
        id=0;
        animatePane(updateRoomPane, tablePane);
    }

    @FXML
    private void clearRoomField(ActionEvent event) {
        updateRoomLocationTxt.setText(null);
        updateRoonNoTxt.setText(null);
        updatePriceRoom.setText(null);
        updateEndDateRoom.setValue(null);
        updatePaidTypeRent.getSelectionModel().clearSelection();
        updateRoomDescriptionTxt.setText(null);
        updateRoomLocationTxt.requestFocus();
        
    }

    @FXML
    private void updatePostRoomDetails(ActionEvent event) {
        errorRoomInfo.setText(null);
        roomSpinner.setVisible(true);
       PauseTransition pt =new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished((e)->{
                UpdateRoom();
                setDataInTable();
        });
        
        pt.play();
     }

    @FXML
    private void viewToTablePane(ActionEvent event) {
        id=0;
        animatePane(viewDetailPane, tablePane);
    }
    
    
    private void setDataInOtherPane(){
        statusUpdateProfile.getItems().clear();
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = r.getRoomDetails(id);
        try {
            while (rs.next()) {                
                //setting value in Form Pane for updating
                updateSearchingRoomLabel.setText(rs.getString("searching"));
                updateRoomLocationTxt.setText(rs.getString("location"));
                updateRoonNoTxt.setText(rs.getString("room_no"));
                java.sql.Date e = (java.sql.Date)rs.getDate("end_date");
                LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(e) );
                updateEndDateRoom.setValue(localDate);
                updatePriceRoom.setText(rs.getString("price"));
                actualPriceLbl.setText(rs.getString("price"));
                visibiltyLabel.setText(rs.getString("visibility"));
                
                switch (rs.getString("paid_type")) {
                    case "Weekly":
                        updatePaidTypeRent.setValue("Weekly");
                        break;
                    case "Monthly":
                        updatePaidTypeRent.setValue("Monthly");
                        break;
                    default:
                        updatePaidTypeRent.setValue("Yearly");
                        break;
                }
                
                updateRoomDescriptionTxt.setText(rs.getString("room_description"));
                
                switch (rs.getString("status")) {
                    case "Available":
                        statusUpdateProfile.getSelectionModel().clearSelection();
                        statusUpdateProfile.getItems().addAll("Available","Unavailable");
                        statusUpdateProfile.setValue("Available");
                        break;
                    case "Unavailable":
                        statusUpdateProfile.getSelectionModel().clearSelection();
                        statusUpdateProfile.getItems().addAll("Available","Unavailable");
                        statusUpdateProfile.setValue("Unavailable");
                        break;
                    
                    case "Waiting":
                        statusUpdateProfile.getSelectionModel().clearSelection();
                        statusUpdateProfile.setValue("Waiting");
                        //user cannot select other user for booking
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("You have already selected one User.\n\n\tWait For him to Respond");
                        
                        break;
                    
                    case "Booked":
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("This Posted information has been booked");
                        break;
                  
                    case "Validity Finish":
                        statusUpdateProfile.getSelectionModel().clearSelection();
                        statusUpdateProfile.getItems().addAll("Available","Unavailable");
                        statusUpdateProfile.setValue("Available");
                        break;
                        
                     default:
                        statusUpdateProfile.getSelectionModel().clearSelection();
                        statusUpdateProfile.getItems().addAll("Available","Unavailable");
                        statusUpdateProfile.setValue("Validity Finish");
                        break;
                }
                
                if(rs.getString("visibility").equals("Private")){
                    visibilityRoom.setValue("Private");
                }
                else{
                      visibilityRoom.setValue("Public");
                }
                //setting in Details Pane
                posetdAsLblDtl.setText(rs.getString("user_type_name"));
                searchingLblDtl.setText(rs.getString("searching"));
                locationLblDtl.setText(rs.getString("location"));
                roomNoLblDtl.setText(rs.getString("room_no"));
                postedDatLblDTl.setText(rs.getDate("posted_date").toString());
                postEndDateLblDtl.setText(rs.getDate("end_date").toString());
                priceLblDtl.setText(rs.getString("price"));
                paidTypeLblDtl.setText(rs.getString("paid_type"));
                statusLblDtl.setText(rs.getString("status"));
                
                if (rs.getString("room_description").equals("")) {
                    descriptionLblDtl.setText("Descritpion Not Found");
                }
                else{
                    descriptionLblDtl.setText(rs.getString("room_description"));
                }  
                //calculation for labeling in price
                Float actuaPrice = rs.getFloat("price");
                Float commision = (actuaPrice*15)/100;
                commisionPriceLbl.setText(String.valueOf(commision));
                Float total = actuaPrice+commision;
                totalPriceLbl.setText(String.valueOf(total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }
    
    private void setIntestedUserListInTable(){
        interestedUser.clear();
        username.clear();
        
        InterestedUserBLL iu =new InterestedUserBLL();
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = iu.getUserInterestedinHouseType(id,"Room");
        try {
            if (!rs.isBeforeFirst()) {
                noIntrestedUserPane.setVisible(true);
                roomInterestedUserPane.setVisible(false);
                noselectUserPane.setVisible(true);
                noselectUserLbl.setText("No user found for booking selected Post");
                selectUserPane.setVisible(false);

            } else {
                if (noIntrestedUserPane.isVisible()) {
                    noIntrestedUserPane.setVisible(false);
                    roomInterestedUserPane.setVisible(true);
                    noselectUserPane.setVisible(false);
                    selectUserPane.setVisible(true);
                }
                while (rs.next()) {
                    InterestedUser userroom = new InterestedUser();
                    userroom.setHouseId(rs.getInt("house_id"));
                    userroom.setUsername(rs.getString("username"));
                    userroom.setInterestedDate(rs.getDate("interested_date"));
                    interestedUser.add(userroom);

                    RoomInterestedUserTable.setItems(interestedUser);
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
    
    private void animatePane(AnchorPane pane1,AnchorPane pane2){
        FadeAnimation fd =new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void bookesUserForPostedRoom(ActionEvent event) {
        String name = getUsernameFromCombo();
        //alert box if time bho bhane
        if(name !=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("House Book Request");
            alert.setHeaderText(null);
            alert.setContentText("Are You Sure Wanna Give This House to " + name + "?");

            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));

            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Booked bl = new Booked();
                bl.setPostownername(Routing.USERNAME);
                bl.setBookedusername(name);
                bl.setHouseTypeName("Room");
                bl.setHouseId(id);
                bl.setRequestDate(java.sql.Date.valueOf(new Validation().getTodaydate()));
                bl.setHouseEndDate(java.sql.Date.valueOf(postEndDateLblDtl.getText()));
                bl.setRequestStatus("Waiting");
                bl.setActualPrice(Float.valueOf(actualPriceLbl.getText()));
                bl.setCommisionPrice(Float.valueOf(commisionPriceLbl.getText()));
                bl.setTotalCost(Float.valueOf(totalPriceLbl.getText()));
                bl.setUserStatus("UNKNOWN");

                //booking room
                BookedBLL b = new BookedBLL();
                b.bookedHouse(bl);

                //updating room status
                r.updateRoomStatus("Waiting", id);

                //notify to booked user
                NotificationUser nfu = new NotificationUser();
                nfu.setFromuser(Routing.USERNAME);
                nfu.setTouser(name);
                nfu.setNotificationType(4);
                nfu.setDetails("has asked you to confirm booking of " + Routing.USERNAME + " post about room that you have shown interested");
                nfu.setStatus("Unseen");

                new NotificationBLL().sendNotification(nfu);

                animatePane(viewDetailPane, tablePane);
                id = 0;
            }
            else{
              animatePane(viewDetailPane, tablePane);
                id = 0;  
            }
            
        }
       
        
    }

    @FXML
    private void showInnterestedUserProfile(ActionEvent event) {
        //viewing user interested profile
        getInterstedUserProfile();
        
    }
 
    // use in loading user profile
    private void getInterstedUserProfile(){
        String name = getUsernameFromCombo();

        //check visibilty and followe user permission
        //ahile chai yetikai garchu ma
        if (name != null) {
            userInteretedProfilePane.setVisible(true);
            UserBLL u = new UserBLL();
            FollowedUserBLL fu = new FollowedUserBLL();
            BookedBLL b = new BookedBLL();
  
            
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
    
    public String getUsernameFromCombo(){
        String interesteduser= listOfUserInterestedCombo.getSelectionModel().getSelectedItem();
        if (interesteduser==null) {
            userInteretedProfilePane.setVisible(false);
            selectUserError.setText("Invalid User Selection");
            return null;
        }
        else{
            return interesteduser;
        }
        
    }
    
    private void UpdateRoom(){
        Validation vd =new Validation();
        if(!vd.validateAddress(updateRoomLocationTxt.getText())){
            errorInRoom("* Invalid Room Location");
        }
        else if(!vd.validateRoomNumber(updateRoonNoTxt.getText().trim())){
            errorInRoom("*Enter Valid Room Number");
        }
        else if(updateEndDateRoom.getValue() == null){
            errorInRoom("*End Date Cannot be Empty ");
        }
        else if(vd.getDateDifferenceinDay(updateEndDateRoom.getValue().toString()) == 0 || 
                vd.getDateDifferenceinDay(updateEndDateRoom.getValue().toString()) < 0){
            errorInRoom("*Invalid Date. Choose new Date.");
        }
        else if(!vd.validatePrice(updatePriceRoom.getText().trim())){
            errorInRoom("*Enter Valid Price For Room ");
        }
        else if(updatePaidTypeRent.getSelectionModel().isEmpty()){
            errorInRoom("*Invalid Payment Method");
        }
        else if(!vd.validateTextArea(updateRoomDescriptionTxt.getText().trim())){
            errorInRoom("*Invalid Room Description");
        }
        else if (statusUpdateProfile.getSelectionModel().isEmpty()){
            errorInRoom("*Invalid Status Visibility");
        }
        else if(visibilityRoom.getSelectionModel().isEmpty()){
            errorInRoom("*Invalid Room Visibilty");
        }
        else{
            //
            errorRoomInfo.setText("");
            roomSpinner.setVisible(false);
            Room room =new Room();
            room.setRoomId(id);
            room.setRoomLocation(updateRoomLocationTxt.getText());
            room.setRoomNo(Integer.parseInt(updateRoonNoTxt.getText()));
            room.setEndDate(java.sql.Date.valueOf(updateEndDateRoom.getValue()));
            room.setPrice(Float.parseFloat(updatePriceRoom.getText()));
            room.setPaidType(updatePaidTypeRent.getSelectionModel().getSelectedItem());
            room.setRoomDescription(updateRoomDescriptionTxt.getText());
            room.setRoomStatus(statusUpdateProfile.getSelectionModel().getSelectedItem());
            room.setRoomVisibility(visibilityRoom.getSelectionModel().getSelectedItem());
            
            r.updateRoomDetails(room);  
            animatePane(updateRoomPane, tablePane);
        }
    }

    private void errorInRoom(String error) {
             errorRoomInfo.setText(error);
              roomSpinner.setVisible(false);
    }
    @FXML
    private void hideProfilePane(ActionEvent event) {
         if (userInteretedProfilePane.isVisible() || profileBlockPane.isVisible()) {
            userInteretedProfilePane.setVisible(false);
            profileBlockPane.setVisible(false);
            BookUserBtn.setVisible(false);
        }
    }
    
}
