/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ApartmentBLL;
import model.BookedBLL;
import model.DatabaseConnection;
import model.FolllowRequestBLL;
import model.HouseBLL;
import model.InterestedUserBLL;
import model.NotificationBLL;
import model.RoomBLL;
import model.UserBLL;
import model.UserDescriptionBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton postHouseBtn;
    @FXML
    private JFXButton manageHouseBtn;
    @FXML
    private JFXButton followingUsrBtn;
    @FXML
    private JFXButton followedUsrBtn;
    @FXML
    private JFXButton confirmBooingRequestBtn;
    @FXML
    private JFXButton interestedHouseListBtn;
    @FXML
    private JFXButton bookedHouseList;
    @FXML
    private JFXButton followRequestBtn;
    @FXML
    private JFXButton notificationBtn;
    @FXML
    private AnchorPane landiingPane;
    @FXML
    private JFXButton landingManageProfileBtn;
    @FXML
    private JFXButton landingNotificationBtn;
    @FXML
    private JFXButton landingPostHouseBtn;
    @FXML
    private JFXButton landingFollowRequestBtn;
    @FXML
    private JFXButton landingBookedListBtn;
    @FXML
    private JFXButton landingInterestedListBtn;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label houseNumberLabel;
    @FXML
    private Label apartmentNumberLabel;

    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Button LogOutBtn;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Label lblUsrName;

    @FXML
    private AnchorPane activityPane;

    @FXML
    private AnchorPane profileInfoPane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private JFXButton searchUsrBtn;
    @FXML
    private JFXButton searchHouseBtn;
    @FXML
    private AnchorPane postInfoPane;
    @FXML
    private AnchorPane managePane;
    @FXML
    private JFXButton manageRoomBtn;
    @FXML
    private JFXButton manageApartmentBtn;
    @FXML
    private AnchorPane followingInfoPane;
    @FXML
    private AnchorPane followersInfoPane;
    @FXML
    private AnchorPane bookrequestInfoPane;
    @FXML
    private AnchorPane interestedListInfoPane;
    @FXML
    private AnchorPane bookedListInfoPane;
    @FXML
    private AnchorPane followrequestInfoPane;
    @FXML
    private AnchorPane notificationInfoPane;
    @FXML
    private Label notificationLabel;
    @FXML
    private JFXButton manageBtn;

    private AnchorPane profile, postHouseInfo, notifications, searchUser, searchHouse, followrequest,
            manageRoom, manageHouse, manageApartment, houseInterested, follwers, following, bookingrequest, booked;
    @FXML
    private Label lblFullname;
    @FXML
    private Label lblDob;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblGender;
    @FXML
    private JFXButton helpBtn;
    @FXML
    private AnchorPane helpInfoPane;
    @FXML
    private Label indcateTxt;
 
    private File usermanual;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //setting content in all the component
        setContentinDashBoard();
        
        //poost that validity fiiniished update too unavailable
        checkPostValidiity();
        
        //Booookiing request send to user and exceed more than 5 days
        //checkiing own sending reuqest too other
        checkOwnRequestTimesExceed();
       
         //Booookiing request recieve  and exceeds more than 5 days
        //checking booking request date exceeds or not
        checkGettiingRequestTimeExcedd();
        
}

    private void setContentinDashBoard() {
        UserDescriptionBLL udbll = new UserDescriptionBLL();
        NotificationBLL nbll = new NotificationBLL();
        FolllowRequestBLL fr = new FolllowRequestBLL();
        RoomBLL r = new RoomBLL();
        HouseBLL h = new HouseBLL();
        ApartmentBLL a = new ApartmentBLL();

        //setting data in top left of dashboard
        String imagename=udbll.getProfileName(Routing.USERNAME);
        Image images=null;
        try{
            images = new Image(Routing.USERIMAGES + imagename);
            //filling images in circle
            ImagePattern im = new ImagePattern(images);
            imgUsrTop.setFill(im);
            circleImgUsr.setFill(im);

        }
        catch(IllegalArgumentException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System Eror");
            alert.setHeaderText(null);
            alert.setContentText("Currently System is Busy. Please try later ");

            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));
            alertstage.show();
            
            PauseTransition pt =new PauseTransition(Duration.seconds(3));
            pt.setOnFinished(e->{
                                System.exit(0);
            });
            pt.play();
        }
        //labeling user
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet userdata = new UserBLL().getUserProfileDetails(Routing.USERNAME);
        try {
            while (userdata.next()) {
                lblUsrName.setText(userdata.getString("first_name")+" " +userdata.getString("last_name"));
                lblFullname.setText(userdata.getString("username"));
                lblDob.setText(userdata.getDate("dob").toString());
                lblAddress.setText(userdata.getString("address"));
                lblGender.setText(userdata.getString("gender"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
        
        //Used to find Number of Notifications and Follow Request
        int notificationNumber = nbll.getNewNotificatiosNumber(Routing.USERNAME);
        int requestNumber = fr.getNewFollowRequestNumber(Routing.USERNAME);
        if (notificationNumber < 1) {
            landingNotificationBtn.setText("Notification");
        } else {
            landingNotificationBtn.setText(String.valueOf(notificationNumber) + " Notification");
        }
        if (requestNumber < 1) {
            landingFollowRequestBtn.setText("Follow Request");
        } else {
            landingFollowRequestBtn.setText(String.valueOf(requestNumber) + " Unseen Follow Request");
        }
        //setting room, house and apartment number
        roomNumberLabel.setText(String.valueOf(r.getPosetdRoomNumber(Routing.USERNAME)));
        houseNumberLabel.setText(String.valueOf(h.getPosetdHouseNumber(Routing.USERNAME)));
        apartmentNumberLabel.setText(String.valueOf(a.getPosetdApartmentNumber(Routing.USERNAME)));

    }

    private void checkGettiingRequestTimeExcedd() {
       //checking requested date for booking exceed more than 5 days
        BookedBLL b = new BookedBLL();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = b.getBookedInformatioon(Routing.USERNAME, "Waiting");
        try {
            while (rs.next()) {
                String housetype = rs.getString("house_type_name");
                int houseId = rs.getInt("house_id");

                String requestedDate = rs.getDate("request_date").toString();
                
                if (new Validation().getDateDifferenceinDay(requestedDate) < -5) {
                    //update the house type from waiting to available again
                    switch (housetype) {
                        case "Room":
                            new RoomBLL().updateRoomStatus("Available", houseId);
                            break;
                        case "House":
                            new HouseBLL().updateHouseStatus("Available", houseId);
                            break;
                        case "Apartment":
                            new ApartmentBLL().updateApartmentStatus("Available", houseId);
                            break;
                    }
                    //delete the booking request if user doesn't repond
                    b.deleteConfirmBookRequestForTimeExceeds(Routing.USERNAME, housetype, houseId);
                    
                    //delet from interested list too
                    new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME,houseId, housetype);
                }
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkOwnRequestTimesExceed() {
       //checking requested date for booking exceed more than 5 days
        BookedBLL b = new BookedBLL();
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = b.getBookedRequestInformatioon(Routing.USERNAME, "Waiting");
        try {
            while (rs.next()) {
                String housetype = rs.getString("house_type_name");
                int houseId = rs.getInt("house_id");

                String requestedDate = rs.getDate("request_date").toString();
                
                if (new Validation().getDateDifferenceinDay(requestedDate) < -5) {
                    //update the house type from waiting to available again
                    switch (housetype) {
                        case "Room":
                            new RoomBLL().updateRoomStatus("Available", houseId);
                            break;
                        case "House":
                            new HouseBLL().updateHouseStatus("Available", houseId);
                            break;
                        case "Apartment":
                            new ApartmentBLL().updateApartmentStatus("Available", houseId);
                            break;
                    }
                    //delete the booking request if user doesn't repond
                    b.deleteConfirmBookRequestForTimeExceeds(Routing.USERNAME, housetype, houseId);
                    
                    //delet from interested list too
                    new InterestedUserBLL().cancelUserInterestedInHouse(Routing.USERNAME,houseId, housetype);
                }
            }
            db.disconnect(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //check post end date validation and updated to validity finished
    private void checkPostValidiity(){
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();
        RoomBLL r =new RoomBLL();
        HouseBLL h =new HouseBLL();
        ApartmentBLL a =new ApartmentBLL();
        //case rooom
        ResultSet roomdata = r.getPostedRoomEndDate(Routing.USERNAME);
        try {
            while(roomdata.next()){
                String enddate = roomdata.getDate("end_date").toString();
                 if (new Validation().getDateDifferenceinDay(enddate) < 0) {
                     r.updateRoomStatus("Validity Finish", roomdata.getInt("room_id"));
                 }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //case House
        ResultSet housedata = h.getPostedHouseEndDate(Routing.USERNAME);
        try {
            while(housedata.next()){
                String enddate = housedata.getDate("end_date").toString();
                 if (new Validation().getDateDifferenceinDay(enddate) < 0) {
                     h.updateHouseStatus("Validity Finish", housedata.getInt("house_id"));
                 }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //case House
        ResultSet apartmentdata = a.getPostedApartmentEndDate(Routing.USERNAME);
        try {
            while(apartmentdata.next()){
                String enddate = apartmentdata.getDate("end_date").toString();
                 if (new Validation().getDateDifferenceinDay(enddate)< 0) {
                     a.updateApartmentStatus("Validity Finish", apartmentdata.getInt("apartment_id"));
                 }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect(conn);
    }
    
    @FXML
    private void showProfileInfo(MouseEvent event) {
        profileInfoPane.setVisible(true);
    }

    @FXML
    private void hideProfileInfo(MouseEvent event) {
        profileInfoPane.setVisible(false);
    }

    @FXML
    private void hideSearchInfo(MouseEvent event) {
            searchPane.setVisible(false);
    }

    @FXML
    private void showSearchInfo(MouseEvent event) {
            searchPane.setVisible(true);
    }

    @FXML
    private void hdePostInfo(MouseEvent event) {
        postInfoPane.setVisible(false);
    }

    @FXML
    private void showPostInfo(MouseEvent event) {
        postInfoPane.setVisible(true);
    }

    @FXML
    private void hideManageHouseInfo(MouseEvent event) {
        managePane.setVisible(false);
    }

    @FXML
    private void showManageHouseInfo(MouseEvent event) {
        managePane.setVisible(true);
    }

    @FXML
    private void hideFollowingInfo(MouseEvent event) {
        followingInfoPane.setVisible(false);
    }

    @FXML
    private void showFollowingInfo(MouseEvent event) {
        followingInfoPane.setVisible(true);
    }

    @FXML
    private void showFollowersInfo(MouseEvent event) {
        followersInfoPane.setVisible(true);
    }

    @FXML
    private void hideFollowersInfo(MouseEvent event) {
        followersInfoPane.setVisible(false);
    }

    @FXML
    private void hideBookedRequestInfo(MouseEvent event) {
        bookrequestInfoPane.setVisible(false);
    }

    @FXML
    private void showBookedRequestInfo(MouseEvent event) {
        bookrequestInfoPane.setVisible(true);
    }

    @FXML
    private void hideInterestedListInfo(MouseEvent event) {
        interestedListInfoPane.setVisible(false);
    }

    @FXML
    private void showInterestedListInfo(MouseEvent event) {
        interestedListInfoPane.setVisible(true);
    }

    @FXML
    private void hideBookedListInfo(MouseEvent event) {
        bookedListInfoPane.setVisible(false);
    }

    @FXML
    private void showBookedListInfo(MouseEvent event) {
        bookedListInfoPane.setVisible(true);
    }

    @FXML
    private void hideFollowRequestInfo(MouseEvent event) {
        followrequestInfoPane.setVisible(false);
    }

    @FXML
    private void showFollowRequestInfo(MouseEvent event) {
        followrequestInfoPane.setVisible(true);
    }

    @FXML
    private void hideNotificationInfo(MouseEvent event) {
        notificationInfoPane.setVisible(false);
    }

    @FXML
    private void showNotificationInfo(MouseEvent event) {
        //check new notification and then show
        NotificationBLL n =new NotificationBLL();
        int newNotification = n.getNewNotificatiosNumber(Routing.USERNAME);
        if(newNotification> 0){
            notificationLabel.setText(String.valueOf(newNotification)+" New Notification");
        }
        notificationInfoPane.setVisible(true);
    }

    @FXML
    private void landingToManageUserProfile(ActionEvent event) {
        try {
            profile = FXMLLoader.load(getClass().getResource(Routing.PROFILE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(profile);
        indcateTxt.setText(Routing.USERNAME+" Profile");
    }

    @FXML
    private void landingToNotification(ActionEvent event) {
        try {
            notifications = FXMLLoader.load(getClass().getResource(Routing.NOTIFICATIONSFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(notifications);
        indcateTxt.setText(Routing.USERNAME+" Notification");
    }

    @FXML
    private void landingToPostHouse(ActionEvent event) {
        try {
            postInfoPane = FXMLLoader.load(getClass().getResource(Routing.HOUSE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(postInfoPane);
        indcateTxt.setText("Post House Information");
    }

    @FXML
    private void landingToFollowRequest(ActionEvent event) {
        try {
            followrequest = FXMLLoader.load(getClass().getResource(Routing.FOLLOWREQUESTFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(followrequest);
        indcateTxt.setText(Routing.USERNAME+" Follow Request");
    }

    @FXML
    private void landingToBookedList(ActionEvent event) {
        try {
            booked = FXMLLoader.load(getClass().getResource(Routing.BOOKEDUSRERFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(booked);
        indcateTxt.setText("Booked");
    }

    @FXML
    private void landingToInterestedList(ActionEvent event) {
        try {
            houseInterested = FXMLLoader.load(getClass().getResource(Routing.HOUSEINTERESTED));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(houseInterested);
        indcateTxt.setText("Interested House");
    }

    @FXML
    private void LogOutAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText(null);
        alert.setContentText("Are You Sure Wanna Logout " + Routing.USERNAME + "?");

        Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));

        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            String storeUserandPass = "user=\npassword=";
            //file user and password must be ket empty
            try {
                try (FileWriter fw = new FileWriter(Routing.HELPER+"user.properties")) {
                    fw.write(storeUserandPass);
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Parent root = FXMLLoader.load(this.getClass().getResource(Routing.LOGIN));
                Scene sc = new Scene(root);

                Stage s = (Stage) rootPane.getScene().getWindow();
                s.setScene(sc);
                s.setTitle("Housing Management System");
                s.show();

            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            alert.close();
        }
    }

    @FXML
    private void searchAction(ActionEvent event) {
        showAndhidePane(searchPane);
    }

    @FXML
    private void searchUserAction(ActionEvent event) {
        //for searching user
        try {
            searchUser = FXMLLoader.load(getClass().getResource(Routing.SEARCHUSER));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(searchUser);
        indcateTxt.setText("Search User");

        showAndhidePane(searchPane);
    }

    @FXML
    private void searchHomeAction(ActionEvent event) {
        //for searching house
        try {
            searchHouse = FXMLLoader.load(getClass().getResource(Routing.SEARCHHOUSE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(searchHouse);
        indcateTxt.setText("Search House");
        
        showAndhidePane(searchPane);
    }

    @FXML
    private void manageAction(ActionEvent event) {
        showAndhidePane(managePane);
    }

    @FXML
    private void manageRoomAction(ActionEvent event) {
        //action that open house in activity pane
        try {
            manageRoom = FXMLLoader.load(getClass().getResource(Routing.ROOMMANAGE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(manageRoom);
        indcateTxt.setText("Manage Room");
        
        showAndhidePane(managePane);
    }

    @FXML
    private void manageHouseAction(ActionEvent event) {
        try {
            manageHouse = FXMLLoader.load(getClass().getResource(Routing.HOUSEMANAGE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(manageHouse);
        indcateTxt.setText("Manage House");
        
        showAndhidePane(managePane);
    }

    @FXML
    private void manageApartmentAction(ActionEvent event) {
        try {
            manageApartment = FXMLLoader.load(getClass().getResource(Routing.APARTMENTMANAGE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(manageApartment);
        indcateTxt.setText("Manage Apartment");
        
        showAndhidePane(managePane);
    }

    //Set selected node to a content holder
    public void setNode(Node node) {
        
        if (landiingPane.isVisible()) {
            landiingPane.setVisible(false);
        }
        
        activityPane.getChildren().clear();
        activityPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    //used to show search and manage pane
    private void showAndhidePane(AnchorPane pane) {
        if (pane.isVisible()) {
            pane.setVisible(false);
        } else {
            pane.setVisible(true);
        }
    }

    @FXML
    private void viewuserProfile(ActionEvent event) {
        try {
            profile = FXMLLoader.load(getClass().getResource(Routing.PROFILE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(profile);
        indcateTxt.setText(Routing.USERNAME);
        
    }

    @FXML
    private void postAboutHouseInfo(ActionEvent event) {
        try {
            postHouseInfo = FXMLLoader.load(getClass().getResource(Routing.HOUSE));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(postHouseInfo);
        indcateTxt.setText("Post House Information");
    }

    @FXML
    private void viewMeFollowingOtherUser(ActionEvent event) {
        try {
            following = FXMLLoader.load(getClass().getResource(Routing.FOLLWINGUSERFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(following);
        indcateTxt.setText("Follow User");

    }

    @FXML
    private void viewOtherFollwMe(ActionEvent event) {
        try {
            follwers = FXMLLoader.load(getClass().getResource(Routing.FOLLOWEDUSER));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(follwers);
        indcateTxt.setText(" Following");
    }

    @FXML
    private void viewBookingRequest(ActionEvent event) {
        try {
            bookingrequest = FXMLLoader.load(getClass().getResource(Routing.BOOKEDREQUEST));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(bookingrequest);
        indcateTxt.setText("Request House to Confirm Book");
    }

    @FXML
    private void viewInterestedList(ActionEvent event) {
        try {
            houseInterested = FXMLLoader.load(getClass().getResource(Routing.HOUSEINTERESTED));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(houseInterested);
        indcateTxt.setText("House Interested List");
    }

    @FXML
    private void viewBookedList(ActionEvent event) {
        try {
            booked = FXMLLoader.load(getClass().getResource(Routing.BOOKEDUSRERFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(booked);
        indcateTxt.setText(Routing.USERNAME+"Booked House");
    }

    @FXML
    private void viewFollowRequest(ActionEvent event) {
        try {
            followrequest = FXMLLoader.load(getClass().getResource(Routing.FOLLOWREQUESTFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(followrequest);
        indcateTxt.setText("Follow Request");
    }

    @FXML
    private void viewNotifications(ActionEvent event) {
        try {
            notifications = FXMLLoader.load(getClass().getResource(Routing.NOTIFICATIONSFRAME));
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(notifications);
        indcateTxt.setText("Notifications");
    }

    @FXML
    private void helpUsermanualActivity(ActionEvent event) {
        //works in windows not in linux
       //opening pdf file
        usermanual  =new File(Routing.HELPER+"HouseBuyandSaleManual.pdf");
        try {
            Desktop.getDesktop().open(usermanual);
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void hideHelpInfo(MouseEvent event) {
     helpInfoPane.setVisible(false);
    }

    @FXML
    private void showHelpInfo(MouseEvent event) {
        helpInfoPane.setVisible(true);
    }

    @FXML
    private void searchPaneInvisible(MouseEvent event) {
        searchPane.setVisible(false);
    }

    @FXML
    private void searchPaneVisible(MouseEvent event) {
        searchPane.setVisible(true);
    }

    @FXML
    private void managePaneInvisible(MouseEvent event) {
        managePane.setVisible(false);
    }

    @FXML
    private void managePaneVisible(MouseEvent event) {
        managePane.setVisible(true);
    }

    @FXML
    private void usernamelabelUndeline(MouseEvent event) {
        lblUsrName.setUnderline(true);
    }

    @FXML
    private void usernamelabelremoveUndeline(MouseEvent event) {
        lblUsrName.setUnderline(false);
    }
    @FXML
    private void landingPaneShow(MouseEvent event) {
        //not used
//        if (!landiingPane.isVisible()) {
//            System.out.println("landing pane is false nad invisibbble");
//            new FadeAnimation().fadeOut(activityPane, landiingPane);
//            landiingPane.setVisible(true);
//            setContentinDashBoard();
//            indcateTxt.setText("Dashboard");
//        }

        //change in for showing activity pane
    }

    

}
