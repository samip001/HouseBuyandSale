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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import data.Booked;
import data.NotificationUser;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ApartmentBLL;
import model.BookedBLL;
import model.HouseBLL;
import model.InterestedUserBLL;
import model.NotificationBLL;
import model.RoomBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class BookedListController implements Initializable {

    @FXML
    private AnchorPane noresultPane;
    @FXML
    private JFXButton noResultRefreshBtn;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private Label infoLabel;
    @FXML
    private TableView<Booked> bookedtable;
    @FXML
    private TableColumn<Booked, String> houseTypeColumn;
    @FXML
    private TableColumn<Booked, Date> requestdateColumn;
    @FXML
    private TableColumn<Booked, String> statusColumn;
    @FXML
    private TableColumn<Booked, Float> priceColumn;
    @FXML
    private TableColumn<Booked, Float> extraCostColumn;
    @FXML
    private TableColumn<Booked, Float> totalCostColumn;
    @FXML
    private TableColumn<Booked, Integer> idColumn;
    @FXML
    private AnchorPane roomDetailsPane;
    @FXML
    private Label RusernameLblDtl;
    @FXML
    private Label RposetdAsLblDtl;
    @FXML
    private Label RsearchingLblDtl;
    @FXML
    private Label RlocationLblDtl;
    @FXML
    private Label RroomNoLblDtl;
    @FXML
    private Label RpostedDatLblDTl;
    @FXML
    private Label RpostEndDateLblDtl;
    @FXML
    private Label RpriceLblDtl;
    @FXML
    private Label RpaidTypeLblDtl;
    @FXML
    private Label RstatusLblDtl;
    @FXML
    private Text RdescriptionLblDtl;
    @FXML
    private JFXButton roomBackBtn;
    @FXML
    private JFXButton roomBookConfirmBtn;
    @FXML
    private AnchorPane houseDetailsPane;
    @FXML
    private Label HusernameLblDtl;
    @FXML
    private Label HhouseforLbl;
    @FXML
    private Label HposetdAsLblDtl;
    @FXML
    private Label HsearchingLblDtl;
    @FXML
    private Label HlocationLblDtl;
    @FXML
    private Label HhouseNoLblDtl;
    @FXML
    private Label HpostedDatLblDTl;
    @FXML
    private Label HpriceLblDtl;
    @FXML
    private Label HpaidTypeLblDtl;
    @FXML
    private Label HstatusLblDtl;
    @FXML
    private Text HdescriptionLblDtl;
    @FXML
    private JFXButton houseBackBtn;
    @FXML
    private JFXButton houseBookConfirmBtn;
    @FXML
    private AnchorPane apartmentDetailsPane;
    @FXML
    private Label AusernameLblDtl;
    @FXML
    private Label apartmentNameLbl;
    @FXML
    private Label apartmentTypeLbl;
    @FXML
    private Label AlocatioonLbl;
    @FXML
    private Label AsearchingLbl;
    @FXML
    private Label ApostedDateLbl;
    @FXML
    private Label ApostEndDateLbl;
    @FXML
    private Label ApriceLbl;
    @FXML
    private Label ApaidTypeLbl;
    @FXML
    private Label AstatusLbl;
    @FXML
    private Label ApostedAsLbl;
    @FXML
    private Text apartmentDescriptioonTxt;
    @FXML
    private JFXButton apartmentBackBtn;
    @FXML
    private JFXButton apartmentBookConfirmBtn;

    final ObservableList<Booked> data = FXCollections.observableArrayList();
    private int houseId;
    private String houseTypeName;
//    private Query query;
    @FXML
    private Label HpostEndDateLblDtl;
    
    private BookedBLL b;
    @FXML
    private JFXButton cancelRoomBtn;
    @FXML
    private JFXButton cancelHouseBtn;
    @FXML
    private JFXButton cancelApartmentBtn;
    @FXML
    private AnchorPane sucessBookPane;
    @FXML
    private Label completeUsername;
    @FXML
    private JFXButton completeBackBtn;
    
    String enddate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        b =new BookedBLL();
        // TODO
        houseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("houseTypeName"));
        requestdateColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("actualPrice"));
        extraCostColumn.setCellValueFactory(new PropertyValueFactory<>("commisionPrice"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));

        loadContentInTable();

    }

    @FXML
    private void refershNoResultPane(ActionEvent event) {
        loadContentInTable();
    }

    @FXML
    private void roomPaneToTablePane(ActionEvent event) {
        houseId = 0;
        houseTypeName = null;
        animatePane(roomDetailsPane, tablePane);
        loadContentInTable();
    }

    @FXML
    private void confirmBookRoomAction(ActionEvent event) {
        ConfirmBooking();
        loadContentInTable();
    }

    @FXML
    private void housePaneToTablePane(ActionEvent event) {
        houseId = 0;
        houseTypeName = null;
        animatePane(houseDetailsPane, tablePane);
        loadContentInTable();
    }

    @FXML
    private void confirmBookHouseAction(ActionEvent event) {
        ConfirmBooking();
        loadContentInTable();

    }

    @FXML
    private void apartmentPaneToTablePane(ActionEvent event) {
        houseId = 0;
        houseTypeName = null;
        animatePane(apartmentDetailsPane, tablePane);
        loadContentInTable();
    }

    @FXML
    private void confirmBookApartmentAction(ActionEvent event) {
        ConfirmBooking();
        loadContentInTable();
     }

    private void loadContentInTable() {
        data.clear();
       
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = b.getBookedInformatioon(Routing.USERNAME,"Waiting");
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
                    Booked bl = new Booked();
                    bl.setHouseTypeName(rs.getString("house_type_name"));
                    bl.setRequestDate(rs.getDate("request_date"));
                    bl.setRequestStatus(rs.getString("request_status"));
                    bl.setActualPrice(rs.getFloat("actual_price"));
                    bl.setCommisionPrice(rs.getFloat("commision_price"));
                    bl.setTotalCost(rs.getFloat("total_cost"));
                    bl.setHouseId(rs.getInt("house_id"));
                    data.add(bl);

                    bookedtable.setItems(data);
                }
                infoLabel.setText(count + " Booked Request Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }

    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void getHousetypeandID(MouseEvent event) {
        roomBookConfirmBtn.setVisible(true);
        houseBookConfirmBtn.setVisible(true);
        apartmentBookConfirmBtn.setVisible(true);
        
        RoomBLL r= new RoomBLL();
        HouseBLL h =new HouseBLL();
        ApartmentBLL a =new ApartmentBLL();
        
        //get house id and house type when booking request is selected
        houseId = bookedtable.getSelectionModel().getSelectedItem().getHouseId();
        houseTypeName = bookedtable.getSelectionModel().getSelectedItem().getHouseTypeName();
        
        //check previous booked or not
        if (b.getEndDateValidofBookedHouse(Routing.USERNAME)> 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Previous Book Found");
            alert.setHeaderText(null);
            alert.setContentText("Currently You Cannot Book Any New "+houseTypeName);
            alert.show();
            Stage alertstage = (Stage)alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES+Routing.ICON));

            switch (houseTypeName) {
                case "Room":
                    roomBookConfirmBtn.setDisable(true);
                    break;
                case "House":
                    houseBookConfirmBtn.setDisable(true);
                    break;
                case "Apartment":
                    apartmentBookConfirmBtn.setDisable(true);
                    break;
            }
        }
        else{
            switch (houseTypeName) {
                case "Room":
                    roomBookConfirmBtn.setDisable(false);
                    break;
                case "House":
                    houseBookConfirmBtn.setDisable(false);
                    break;
                case "Apartment":
                    apartmentBookConfirmBtn.setDisable(false);
                    break;
            }
        }
        //loading content in anchor pane
        if (houseTypeName.equals("Room")) {

            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();

            ResultSet rs = r.getRoomDetails(houseId);
            try {
                while (rs.next()) {
                    //setting in Details Pane
                    RusernameLblDtl.setText(rs.getString("username"));
                    RposetdAsLblDtl.setText(rs.getString("user_type_name"));
                    RsearchingLblDtl.setText(rs.getString("searching"));
                    RlocationLblDtl.setText(rs.getString("location"));
                    RroomNoLblDtl.setText(rs.getString("room_no"));
                    RpostedDatLblDTl.setText(rs.getDate("posted_date").toString());
                    RpostEndDateLblDtl.setText(rs.getDate("end_date").toString()); 
                    RpriceLblDtl.setText(rs.getString("price"));
                    RpaidTypeLblDtl.setText(rs.getString("paid_type"));
                    RstatusLblDtl.setText(rs.getString("status"));

                    //setting end date from room;
                    enddate = rs.getDate("end_date").toString(); 
                    
                            
                    if (rs.getString("room_description").equals("")) {
                        RdescriptionLblDtl.setText("Descritpion Not Found");
                    } else {
                        RdescriptionLblDtl.setText(rs.getString("room_description"));
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);

            animatePane(tablePane, roomDetailsPane);
        } else if (houseTypeName.equals("House")) {
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();

            ResultSet rs = h.getHouseDetails(houseId);
            try {
                while (rs.next()) {

                    //setting in Details Pane
                    HusernameLblDtl.setText(rs.getString("username"));
                    HposetdAsLblDtl.setText(rs.getString("user_type_name"));
                    HsearchingLblDtl.setText(rs.getString("searching"));
                    HlocationLblDtl.setText(rs.getString("location"));
                    HhouseNoLblDtl.setText(rs.getString("house_no"));
                    HhouseforLbl.setText(rs.getString("house_for"));
                    HpostedDatLblDTl.setText(rs.getDate("posted_date").toString());
                    HpostEndDateLblDtl.setText(rs.getDate("end_date").toString());
                    HpriceLblDtl.setText(rs.getString("price"));
                    HpaidTypeLblDtl.setText(rs.getString("paid_type"));
                    HstatusLblDtl.setText(rs.getString("status"));
                    
                    //setting end date from room;
                    enddate = rs.getDate("end_date").toString(); 

                    if (rs.getString("house_description").equals("")) {
                        HdescriptionLblDtl.setText("Descritpion Not Found");
                    } else {
                        HdescriptionLblDtl.setText(rs.getString("house_description"));
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);

            animatePane(tablePane, houseDetailsPane);
        } else {
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();

            ResultSet rs = a.getApartmentDetails(houseId);
            try {
                while (rs.next()) {
                    AusernameLblDtl.setText(rs.getString("username"));
                    apartmentNameLbl.setText(rs.getString("apartment_name"));
                    apartmentTypeLbl.setText(rs.getString("apartment_type_name"));
                    ApostedAsLbl.setText(rs.getString("user_type_name"));
                    AsearchingLbl.setText(rs.getString("searching"));
                    AlocatioonLbl.setText(rs.getString("location"));
                    ApostedDateLbl.setText(rs.getString("posted_date"));
                    ApostEndDateLbl.setText(rs.getString("end_date"));
                    ApriceLbl.setText(rs.getString("price"));
                    ApaidTypeLbl.setText(rs.getString("paid_type"));
                    AstatusLbl.setText(rs.getString("status"));
                    
                    //setting end date from room;
                    enddate = rs.getDate("end_date").toString(); 

                    if (rs.getString("apratment_description").equals("")) {
                        apartmentDescriptioonTxt.setText("Descritpion Not Found");
                    } else {
                        apartmentDescriptioonTxt.setText(rs.getString("apratment_description"));
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(ApartmentManageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
            animatePane(tablePane, apartmentDetailsPane);
        }
    }

    @FXML
    private void cancelRoomActivity(ActionEvent event) {
        String owner = RusernameLblDtl.getText();
        b.deleteBookRequestofUserTimeExceeds(owner, houseTypeName, houseId);
        
        //updating apartment status
            new RoomBLL().updateRoomStatus("Available",houseId); // chaneg
            
            //notify to booked user
            NotificationUser nfu =new NotificationUser();
            nfu.setFromuser(Routing.USERNAME);
            nfu.setTouser(owner);
            nfu.setNotificationType(6);
            nfu.setDetails("has Cancel your Room Book Request ");
            nfu.setStatus("Unseen");

            new NotificationBLL().sendNotification(nfu);
            
            //delete user intersted user
            new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME, houseId, "Room");
        
            animatePane(roomDetailsPane, tablePane);
            loadContentInTable();
    }

    @FXML
    private void cancelHouseActivity(ActionEvent event) {
            String owner = HusernameLblDtl.getText();
            b.deleteBookRequestofUserTimeExceeds(owner, houseTypeName, houseId);
        
            //updating apartment status
            new HouseBLL().updateHouseStatus("Available",houseId); // chaneg
            
            //notify to booked user
            NotificationUser nfu =new NotificationUser();
            nfu.setFromuser(Routing.USERNAME);
            nfu.setTouser(owner);
            nfu.setNotificationType(6);
            nfu.setDetails("has Cancel your House Book Request ");
            nfu.setStatus("Unseen");
            new NotificationBLL().sendNotification(nfu);
            
            //delete user intersted user
            new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME, houseId, "House");
            
            animatePane(houseDetailsPane, tablePane);
            loadContentInTable();
        
    }

    @FXML
    private void cancelApartmentActivity(ActionEvent event) {
            String owner = AusernameLblDtl.getText();
            b.deleteBookRequestofUserTimeExceeds(owner, houseTypeName, houseId);
        
            //updating apartment status
            new ApartmentBLL().updateApartmentStatus("Available",houseId); // change frm wating to available
            
            //notify to booked user
            NotificationUser nfu =new NotificationUser();
            nfu.setFromuser(Routing.USERNAME);
            nfu.setTouser(owner);
            nfu.setNotificationType(6);
            nfu.setDetails("has Cancel your Apartment Book Request ");
            nfu.setStatus("Unseen");

            new NotificationBLL().sendNotification(nfu);
            
            //delete user intersted user
            new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME, houseId, "Apartment");
            
            animatePane(apartmentDetailsPane, tablePane);
            loadContentInTable();
    }

    @FXML
    private void completeToTableActivity(ActionEvent event) {
        animatePane(sucessBookPane, tablePane);
        loadContentInTable();
    }

    private void ConfirmBooking(){
        noresultPane.setVisible(false);
        houseId = bookedtable.getSelectionModel().getSelectedItem().getHouseId();
        houseTypeName = bookedtable.getSelectionModel().getSelectedItem().getHouseTypeName();

         int day = new Validation().getDateDifferenceinDay(enddate);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm "+houseTypeName+" Request");
            alert.setHeaderText(null);
            alert.setContentText("You can't be able to book any house for  "+ String.valueOf(day)+"days.");

            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));

            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                //update PRESENT status to Past;
                b.updateBookedListUserStatus(houseId, houseTypeName, Routing.USERNAME);

                //update request_ status
                b.updateBookedListrequestStatus(houseId, houseTypeName, Routing.USERNAME);

                java.sql.Date date = java.sql.Date.valueOf(new Validation().getTodaydate());
                Booked bl = new Booked();
                bl.setBookedusername(Routing.USERNAME);
                bl.setBookedDate(date);
                bl.setUserStatus("PRESENT");
                bl.setRequestStatus("Booked");
                bl.setHouseId(houseId);
                bl.setHouseTypeName(houseTypeName);

                b.confirmBookingfromRequestedUser(bl);

                //update house type status
                switch (houseTypeName) {
                    case "Room":
                        RoomBLL r = new RoomBLL();
                        r.updateRoomStatus("Booked", houseId);
                        break;
                    case "House":
                        HouseBLL h = new HouseBLL();
                        h.updateHouseStatus("Booked", houseId);
                        break;
                    case "Apartment":
                        ApartmentBLL abll = new ApartmentBLL();
                        abll.updateApartmentStatus("Booked", houseId);
                        break;

                }

                //setting notification after confirm booking
                NotificationUser nfu = new NotificationUser();
                nfu.setFromuser(Routing.USERNAME);
                switch (houseTypeName) {
                    case "Room":
                        nfu.setTouser(RusernameLblDtl.getText());
                        animatePane(roomDetailsPane, sucessBookPane);
                        break;
                    case "House":
                        nfu.setTouser(HusernameLblDtl.getText());
                        animatePane(houseDetailsPane, sucessBookPane);
                        break;
                    case "Apartment":
                        nfu.setTouser(AusernameLblDtl.getText());
                        animatePane(apartmentDetailsPane, sucessBookPane);
                        break;
                }
                //setting username in sucess pane after confirming
                completeUsername.setText(Routing.USERNAME);
                
                nfu.setNotificationType(5);
                nfu.setDetails("has Confrimed Booking of your " + houseTypeName + " Post");
                nfu.setStatus("Unseen");
                new NotificationBLL().sendNotification(nfu);
                
                //deleting interested from interested
                new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME, houseId, houseTypeName);

            }
            else{
                 switch (houseTypeName) {
                    case "Room":
                        animatePane(roomDetailsPane, tablePane);
                        break;
                    case "House":
                        animatePane(houseDetailsPane, tablePane);
                        break;
                    case "Apartment":
                        animatePane(apartmentDetailsPane, tablePane);
                        break;
                }
            }
        
            //changing houseid to 0;
            houseId = 0;
    }
}
