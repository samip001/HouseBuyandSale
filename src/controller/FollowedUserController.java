/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import data.FollowedUser;
import data.NotificationUser;
import model.ApartmentBLL;
import model.BookedBLL;
import model.FollowedUserBLL;
import model.HouseBLL;
import model.NotificationBLL;
import model.RoomBLL;
import model.UserBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class FollowedUserController implements Initializable {

    @FXML
    private AnchorPane tablePane;
    @FXML
    private TableView<FollowedUser> table;
    @FXML
    private TableColumn<FollowedUser,String> usernameColumn;
    @FXML
    private TableColumn<FollowedUser,Date> followingDateCoulmn;
    @FXML
    private TableColumn<FollowedUser,String> permissionColumn;
    @FXML
    private AnchorPane profileDetailsPane;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXComboBox<String> permissionCombo;
    @FXML
    private AnchorPane noFollowerPane;
    @FXML
    private JFXButton refreshNoFollowerBtn;
    @FXML
    private AnchorPane userFollowedPane;
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
    private JFXButton changePermissionBtn;
    
    String name;
    final ObservableList<FollowedUser> data = FXCollections.observableArrayList();
    @FXML
    private Label profileBlocknfoLabel;
    @FXML
    private JFXButton requestBtn;
    @FXML
    private AnchorPane profileBlockPane;
    @FXML
    private Label activeLabel;
    @FXML
    private MaterialDesignIconView activeGlyph;
    @FXML
    private MaterialDesignIconView inactiveGlyph;
    
    
     private FollowedUserBLL fu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fu =new FollowedUserBLL();
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("followingUsername"));
        followingDateCoulmn.setCellValueFactory(new PropertyValueFactory<>("followedDate"));
        permissionColumn.setCellValueFactory(new PropertyValueFactory<>("permission"));
        loadDataInTable();
        
        permissionCombo.getItems().addAll("Allow","Block");
    }    

    @FXML
    private void profileToTablePane(ActionEvent event) {
        name=null;
        animatePane(profileDetailsPane, tablePane);
    }

    @FXML
    private void refreshNoFollwedPane(ActionEvent event) {
       loadDataInTable();
    }

    @FXML
    private void changePermissionForUser(ActionEvent event) {
        //update allow to block
        
        String permission = permissionCombo.getSelectionModel().getSelectedItem();
        fu.updateUserPermission(permission, Routing.USERNAME, name);
        
        animatePane(profileDetailsPane, tablePane);
        name=null;
        //for imediate change
        loadDataInTable();
    }

    
    @FXML
    private void getSelectedUsername(MouseEvent event) {
        loadSelectedUserProfile();
        animatePane(tablePane, profileDetailsPane);
        
     }
    
    private void loadDataInTable(){
        permissionCombo.setValue("Allow");
        data.clear();
        
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        ResultSet rs = fu.getUserFollwedList(Routing.USERNAME);
        try {
            if (!rs.isBeforeFirst()) {
                noFollowerPane.setVisible(true);
                tablePane.setVisible(false);

            } else {
                //for refreshnig table if no data found after adding post
                if (noFollowerPane.isVisible()) {
                    noFollowerPane.setVisible(false);
                    tablePane.setVisible(true);
                }
                while (rs.next()) {
                    FollowedUser followuser =new FollowedUser();
                    followuser.setFollowingUsername(rs.getString("following_username"));
                    followuser.setFollowedDate(rs.getDate("followed_date"));
                    followuser.setPermission(rs.getString("permission"));
                    
                    data.add(followuser);

                    table.setItems(data);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);
    }
    
     private void loadSelectedUserProfile(){
         name=table.getSelectionModel().getSelectedItem().getFollowingUsername();
        
         if (name != null) {
             animatePane(tablePane, profileDetailsPane);
             UserBLL u=new UserBLL();
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
                 requestBtn.setVisible(true);
             }
            else{
                 profileBlockPane.setVisible(false);
             }
             
             if(new BookedBLL().getUserStatus(name) >0){
                 activeLabel.setText("Booked");
                 inactiveGlyph.setVisible(true);
                 activeLabel.setTextFill(Paint.valueOf("#eb0d0d"));
                 
             }
             else{
                 activeLabel.setText("Available");
                 activeLabel.setTextFill(Paint.valueOf("#3fb033"));
                 inactiveGlyph.setVisible(false);
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
        } 
    
     }
    
     private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void requestToChangePermission(ActionEvent event) {
            NotificationUser nfu =new NotificationUser();
            nfu.setFromuser(Routing.USERNAME);
            nfu.setTouser(name);
            nfu.setNotificationType(6);
            nfu.setDetails("has asked you to change permission to allow for "+Routing.USERNAME);
            nfu.setStatus("Unseen");

            name=null;
            
            new NotificationBLL().sendNotification(nfu);
            animatePane(profileDetailsPane, tablePane);
            
    }
}
