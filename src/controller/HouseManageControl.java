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
import data.Booked;
import data.House;
import data.InterestedUser;
import data.NotificationUser;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ApartmentBLL;
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
public class HouseManageControl implements Initializable {

    @FXML
    private AnchorPane noresultPane;
    @FXML
    private JFXButton noresultRefreshBtn;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private Label InfoLabel;
    @FXML
    private TableView<House> tableHouseData;
    @FXML
    private TableColumn<House, Integer> idColumn;
    @FXML
    private TableColumn<House, String> locationColumn;
    @FXML
    private TableColumn<House, String> houseNoColumn;
    @FXML
    private TableColumn<House, String> houseForColumn;
    @FXML
    private TableColumn<House, Date> postedDate;
    @FXML
    private TableColumn<House, Date> postEndDateColumn;
    @FXML
    private TableColumn<House, Integer> priceColumn;
    @FXML
    private TableColumn<House, String> paidTypeColumn;
    @FXML
    private JFXButton viewDetailBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane houseupdatePane;
    @FXML
    private Label searchHouse;
    @FXML
    private JFXTextField houseNumTxt;
    @FXML
    private JFXTextField houseLocationTxt;
    @FXML
    private JFXComboBox<String> houseForCombo;
    @FXML
    private JFXDatePicker endDateHouse;
    @FXML
    private JFXTextField priceHouseTxt;
    @FXML
    private JFXComboBox<String> paidTypeHouse;
    @FXML
    private JFXTextArea descriptionHouseTxt;
    @FXML
    private JFXComboBox<String> statusofHouseCombo;
    @FXML
    private JFXComboBox<String> visbilityHouse;
    @FXML
    private JFXButton backHouseBtn;
    @FXML
    private JFXButton clearHouseFieldBtn;
    @FXML
    private JFXButton updateHouseDetailsBtn;
    @FXML
    private Label errorHouseInfo;
    @FXML
    private JFXSpinner houseSpinner;
    @FXML
    private AnchorPane viewDetailPane;
    @FXML
    private JFXButton viewDetailBack;
    @FXML
    private AnchorPane detailsPane;
    @FXML
    private Label posetdAsLblDtl;
    @FXML
    private Label searchingLblDtl;
    @FXML
    private Label locationLblDtl;
    @FXML
    private Label houseNoLblDtl;
    @FXML
    private Label postedDatLblDTl;
    @FXML
    private Label postEndDateLblDtl;
    @FXML
    private Label priceLblDtl;
    @FXML
    private Label paidTypeLblDtl;
    @FXML
    private Label statusLblDtl;
    @FXML
    private Label houseforLbl;
    @FXML
    private Text descriptionLblDtl;
    @FXML
    private AnchorPane noIntrestedUserPane;
    @FXML
    private TableView<InterestedUser> houseInterestedUserTable;
    @FXML
    private TableColumn<InterestedUser, String> interestedUsernameColumn;
    @FXML
    private TableColumn<InterestedUser, Date> dateInterestedColumn;
    @FXML
    private TableColumn<InterestedUser, Integer> interestedIdColumn;
    @FXML
    private AnchorPane noselectUserPane;
    @FXML
    private Label noselectUserLbl;
    @FXML
    private ComboBox<String> listOfUserInterestedCombo;
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
    private Label actualPriceLbl;
    @FXML
    private Label commisionPriceLbl;
    @FXML
    private Label totalPriceLbl;
    @FXML
    private JFXButton viewInteresteduser;
    @FXML
    private Label selectUserError;
    @FXML
    private JFXSpinner spinnerSelectingUser;

    int id;
    final ObservableList<House> data = FXCollections.observableArrayList();
    //change from room to House interes
    final ObservableList<InterestedUser> interestedUser = FXCollections.observableArrayList();
    //used in combo box of intersted user
    final ObservableList<String> username = FXCollections.observableArrayList();
    @FXML
    private AnchorPane houseInterestedUserPane;
    @FXML
    private AnchorPane selectUserPane;
    @FXML
    private Label visibilityLabel;
    @FXML
    private AnchorPane profileBlockPane;
    @FXML
    private Label profileBlocknfoLabel;
    @FXML
    private Label activeLabel;
    @FXML
    private MaterialDesignIconView activeGlyph;
    @FXML
    private MaterialDesignIconView inactiveGlyph;
    
    private HouseBLL h;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        h =new HouseBLL();
        // TODO
        //for table pane
        idColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        houseNoColumn.setCellValueFactory(new PropertyValueFactory<>("houseNo"));
        houseForColumn.setCellValueFactory(new PropertyValueFactory<>("houseFor"));
        postedDate.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
        postEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        paidTypeColumn.setCellValueFactory(new PropertyValueFactory<>("paidType"));

        setDataInTable();

        //for interested user list
        interestedIdColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));
        interestedUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateInterestedColumn.setCellValueFactory(new PropertyValueFactory<>("interestedDate"));

        paidTypeHouse.getItems().addAll("Weekly", "Monthly", "Yearly");
        visbilityHouse.getItems().addAll("Private","Public");
        
    }

    @FXML
    private void refreshForNoResultPane(ActionEvent event) {
        setDataInTable();
    }

    @FXML
    private void viewDetails(MouseEvent event) {
        errorLabel.setText("");
        id = tableHouseData.getSelectionModel().getSelectedItem().getHouseId();
    }

    private void refreshFromTablePane(ActionEvent event) {
        setDataInTable();
    }

    @FXML
    private void tableToViewActivity(ActionEvent event) {
        if (id < 0 || id == 0) {
            errorLabel.setText("Select House for Detail Viewing");
        } else {
            //set all data in  view
            animatePane(tablePane, viewDetailPane);
            setDataInOtherPane();
            setIntestedUserListInTable();
        }
    }

    @FXML
    private void viewToUpdatePane(ActionEvent event) {
        if (id < 0 || id == 0) {
            errorLabel.setText("Select House for Updating");
        } else {
             //check it is booked or not
              if("Booked".equals(new HouseBLL().getStatus(id))){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.initModality(Modality.APPLICATION_MODAL);
                  alert.setTitle("Update Information");
                  alert.setHeaderText(null);
                  alert.setContentText("Selected House is Booked.\n\n Edit service is unavailable");

                  Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                  alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));
                  alertstage.show();

              }
              else if("Validity Finish".equals(new HouseBLL().getStatus(id))){
                  animatePane(tablePane, houseupdatePane);  
                  setDataInOtherPane();
                  errorInHouse("Invalid End Date. Please select Valid date");
              }
              else{
                  // set al data in field
                  setDataInOtherPane();
                  errorInHouse("");
                  animatePane(tablePane, houseupdatePane);
              }
        }
    }

    @FXML
    private void houseBackAction(ActionEvent event) {
        id = 0;
        animatePane(houseupdatePane, tablePane);
    }

    @FXML
    private void cleaHouseField(ActionEvent event) {
        searchHouse.setText(null);
        houseLocationTxt.setText(null);
        houseForCombo.getSelectionModel().clearSelection();
        priceHouseTxt.setText(null);
        houseNumTxt.setText(null);
        endDateHouse.setValue(null);
        priceHouseTxt.setText(null);
        paidTypeHouse.getSelectionModel().clearSelection();
        descriptionHouseTxt.setText(null);
        if (!statusofHouseCombo.isDisable()) {
            statusofHouseCombo.getSelectionModel().clearSelection();
        }
        searchHouse.requestFocus();
    }

    @FXML
    private void uodateHouseDetails(ActionEvent event) {
        errorHouseInfo.setText(null);
        houseSpinner.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished((e) -> {
            UpdateHouse();
            setDataInTable();
        });

        pt.play();
    }

    @FXML
    private void viewToTablePane(ActionEvent event) {
        id = 0;
        animatePane(viewDetailPane, tablePane);
    }

    @FXML
    private void bookesUserForPostedHouse(ActionEvent event) {
        //baki cha
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
                bl.setHouseTypeName("House");
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

                //updating house status
                h.updateHouseStatus("Waiting", id); // chaneg

                //notify to booked user
                NotificationUser nfu = new NotificationUser();
                nfu.setFromuser(Routing.USERNAME);
                nfu.setTouser(name);
                nfu.setNotificationType(4);
                nfu.setDetails("has asked you to confirm booking of " + Routing.USERNAME + " post about house that you have shown interested");
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
         String name = getUsernameFromCombo();

        //check visibilty and followe user permission
        //ahile chai yetikai garchu ma
        if (name != null) {
            userInteretedProfilePane.setVisible(true);
//            Query query = new Query();
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

    private void setDataInTable() {
        data.clear();
//        Query query = new Query();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = h.postedHouseList(Routing.USERNAME);
        try {
            if (!rs.isBeforeFirst()) {
                noresultPane.setVisible(true);
                tablePane.setVisible(false);

            } else {
                //for refreshnig table if no data found after adding post
                if (noresultPane.isVisible()) {
                    noresultPane.setVisible(false);
                    tablePane.setVisible(true);
                }
                int count = 0;
                while (rs.next()) {
                    count++;
                    House house = new House();
                    house.setHouseId(rs.getInt("house_id"));
                    house.setLocation(rs.getString("location"));
                    house.setHouseNo(rs.getString("house_no"));
                    house.setHouseFor(rs.getString("house_for"));
                    house.setPostedDate(rs.getDate("posted_date"));
                    house.setEndDate(rs.getDate("end_date"));
                    house.setPrice(rs.getFloat("price"));
                    house.setPaidType(rs.getString("paid_type"));

                    data.add(house);
                    tableHouseData.setItems(data);
                }
                InfoLabel.setText(count + " Posted Information Found For House");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);
    }

    private void setIntestedUserListInTable() {
        interestedUser.clear();
        username.clear();
//        Query query = new Query();
        InterestedUserBLL iu =new InterestedUserBLL();
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = iu.getUserInterestedinHouseType(id,"House");
        try {
            if (!rs.isBeforeFirst()) {
                noIntrestedUserPane.setVisible(true);
                houseInterestedUserPane.setVisible(false);
                noselectUserPane.setVisible(true);
                noselectUserLbl.setText("No user found for booking selected Post");
                selectUserPane.setVisible(false);

            } else {
                 if (noIntrestedUserPane.isVisible()) {
                    noIntrestedUserPane.setVisible(false);
                    houseInterestedUserPane.setVisible(true);
                    noselectUserPane.setVisible(false);
                    noselectUserLbl.setText("No user found for booking selected Post");
                    statusLblDtl.setText("Available");
                    selectUserPane.setVisible(true);
                }
                while (rs.next()) {
                    InterestedUser userroom = new InterestedUser();
                    userroom.setHouseId(rs.getInt("house_id"));
                    userroom.setUsername(rs.getString("username"));
                    userroom.setInterestedDate(rs.getDate("interested_date"));
                    interestedUser.add(userroom);


                    houseInterestedUserTable.setItems(interestedUser);
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
    
    private void setDataInOtherPane() {
        houseForCombo.getItems().clear();
        statusofHouseCombo.getItems().clear();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = h.getHouseDetails(id);
        try {
            while (rs.next()) {
                //setting value in update Pane for updating
                searchHouse.setText(rs.getString("searching"));
                houseLocationTxt.setText(rs.getString("location"));
                if (rs.getString("house_for").equals("Rent")) {
                    houseForCombo.getSelectionModel().clearSelection();
                    houseForCombo.getItems().add("Rent");
                    houseForCombo.setValue("Rent");
                } else {
                    houseForCombo.getSelectionModel().clearSelection();
                    houseForCombo.getItems().add("Sale");
                    houseForCombo.setValue("Sale");
                }
                houseNumTxt.setText(rs.getString("house_no"));
                java.sql.Date e = (java.sql.Date) rs.getDate("end_date");
                LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(e));
                endDateHouse.setValue(localDate);
                priceHouseTxt.setText(rs.getString("price"));

                if (rs.getString("paid_type").equals("Weekly")) {
                    paidTypeHouse.setValue("Weekly");
                } else if (rs.getString("paid_type").equals("Monthly")) {
                    paidTypeHouse.setValue("Monthly");
                } else {
                    paidTypeHouse.setValue("Yearly");
                }
                
                 if (rs.getString("visibility").equals("Private")) {
                    visbilityHouse.setValue("Private");
                } else {
                    visbilityHouse.setValue("Public");
                }

                descriptionHouseTxt.setText(rs.getString("house_description"));

                switch (rs.getString("status")) {
                    case "Available":
                        statusofHouseCombo.getSelectionModel().clearSelection();
                        statusofHouseCombo.getItems().addAll("Available","Unavailable");
                        statusofHouseCombo.setValue("Available");
                        break;
                    case "Unavailable":
                        statusofHouseCombo.getSelectionModel().clearSelection();
                        statusofHouseCombo.getItems().addAll("Available","Unavailable");
                        statusofHouseCombo.setValue("Unavailable");
                        break;
                   
                    case "Waiting":
                        statusofHouseCombo.getSelectionModel().clearSelection();
                        statusofHouseCombo.setValue("Waiting");
                        
                        //user cannot select other user for booking
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("You have already selected one User.\n \tWait For him to Respond");
                        
                        break;
                        
                   case "Booked":
                        noselectUserPane.setVisible(true);
                        selectUserPane.setVisible(false);
                        noselectUserLbl.setText("This Posted information has been booked");
                        break;
                   case "Validity Finish":
                        statusofHouseCombo.getSelectionModel().clearSelection();
                        statusofHouseCombo.getItems().addAll("Available","Unavailable");
                        statusofHouseCombo.setValue("Available");
                        break;
                     default:
                        statusofHouseCombo.getSelectionModel().clearSelection();
                        statusofHouseCombo.setValue("Validity Finish");
                        statusofHouseCombo.getItems().addAll("Available","Unavailable");
                        break;

                }

                //setting in Details Pane
                posetdAsLblDtl.setText(rs.getString("user_type_name"));
                searchingLblDtl.setText(rs.getString("searching"));
                locationLblDtl.setText(rs.getString("location"));
                houseNoLblDtl.setText(rs.getString("house_no"));
                houseforLbl.setText(rs.getString("house_for"));
                postedDatLblDTl.setText(rs.getDate("posted_date").toString());
                postEndDateLblDtl.setText(rs.getDate("end_date").toString());
                priceLblDtl.setText(rs.getString("price"));
                paidTypeLblDtl.setText(rs.getString("paid_type"));
                statusLblDtl.setText(rs.getString("status"));
                visibilityLabel.setText(rs.getString("visibility"));

                if (rs.getString("house_description").equals("")) {
                    descriptionLblDtl.setText("Descritpion Not Found");
                } else {
                    descriptionLblDtl.setText(rs.getString("house_description"));
                }

                //calculation for labeling in price
                Float actuaPrice = rs.getFloat("price");
                actualPriceLbl.setText(rs.getString("price"));
                Float commision = (actuaPrice * 15) / 100;
                commisionPriceLbl.setText(String.valueOf(commision));
                Float total = actuaPrice + commision;
                totalPriceLbl.setText(String.valueOf(total));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }

    public String getUsernameFromCombo() {
        String interesteduser = listOfUserInterestedCombo.getSelectionModel().getSelectedItem();
        if (interesteduser == null) {
            userInteretedProfilePane.setVisible(false);
            selectUserError.setText("Invalid User Selection");
            return null;
        } else {
            return interesteduser;
        }

    }

    private void errorInHouse(String error) {
        errorHouseInfo.setText(error);
        houseSpinner.setVisible(false);
    }

    private void UpdateHouse(){
        Validation vd =new Validation();
            
         if(!vd.validateAddress(houseNumTxt.getText() )){
            errorInHouse("*Invalid House Number");
        }
        else if (!vd.validateAddress(houseLocationTxt.getText() )){
            errorInHouse("*Invalid Location of House");
        }
        else if (houseForCombo.getSelectionModel().isEmpty()){
            errorInHouse("*Select House for Rent or Sale ");
        }
        else if(endDateHouse.getValue()== null){
            errorInHouse("End Date Cannot be empty");
        }
        else if(vd.getDateDifferenceinDay(endDateHouse.getValue().toString()) == 0 
                || vd.getDateDifferenceinDay(endDateHouse.getValue().toString()) <0){
            errorInHouse("*Invalid Date. Choose Valid Date");
        }
        else if(!vd.validatePrice(priceHouseTxt.getText().toString())){
            errorInHouse("*Invalid House Price");
        }
        else if(paidTypeHouse.getSelectionModel().isEmpty()){
            errorInHouse("*Select Valid Payment Method ");
        }
        else if (!vd.validateTextArea(descriptionHouseTxt.getText().toString())){
            errorInHouse("*House Description Not Valid");
        }
        else if(statusofHouseCombo.getSelectionModel().isEmpty()){
            errorInHouse("*Select Post Status");
        }
         else if(visbilityHouse.getSelectionModel().isEmpty()){
            errorInHouse("*Select Post Visibility");
        }
        else{
             errorHouseInfo.setText("");
            houseSpinner.setVisible(false);
            House house =new House();
            house.setHouseId(id);
            house.setHouseNo(houseNumTxt.getText());
            house.setLocation(houseLocationTxt.getText());
            house.setHouseFor(houseForCombo.getSelectionModel().getSelectedItem());
            house.setEndDate(java.sql.Date.valueOf(endDateHouse.getValue().toString()));
            house.setPrice(Float.parseFloat(priceHouseTxt.getText().trim()));
            house.setPaidType(paidTypeHouse.getSelectionModel().getSelectedItem());
            house.setHouseDescription(descriptionHouseTxt.getText());
            house.setStatus(statusofHouseCombo.getSelectionModel().getSelectedItem());
            house.setHouseVisibility(visbilityHouse.getSelectionModel().getSelectedItem());
            
            //query
            h.updateHouseDetails(house);
            Notifications nf = Notifications.create()
                            .title("House")
                            .text("Updated House Details Sucessfully")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(3))
                            .graphic(null);
            nf.show();
            id=0;
             animatePane(houseupdatePane, tablePane);
         }
    }
    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
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
