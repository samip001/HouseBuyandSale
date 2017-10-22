/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import model.ApartmentBLL;
import model.HouseBLL;
import model.RoomBLL;

/**
 *
 * @author samip
 */
public class SearchHouseController implements Initializable{

    @FXML
    private ComboBox<String> houseType;
    @FXML
    private TextField locationTxt;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private Label errorInfo;
    @FXML
    private AnchorPane resultPane;
    @FXML
    private JFXListView<AnchorPane> listHouse;
    @FXML
    private AnchorPane noresultPane;
    @FXML
    private Text seacrhLocationLabel;
    @FXML
    private Label resultFoundLabel;
    
    int initialcheckload=0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        houseType.getItems().addAll("Room","House","Apartment");
        if (initialcheckload == 1) {
            searchAction();
        }
    }


    @FXML
    private void searchHouseActivity(ActionEvent event) {
        initialcheckload=1;
        searchAction();
        
     }

    @FXML
    private void houseTypeChange(ActionEvent event) {
        noresultPane.setVisible(false);
        resultPane.setVisible(false);
        
        locationTxt.setText(null);
        locationTxt.requestFocus();
    }
    
    private void searchAction(){
        noresultPane.setVisible(false);
        resultPane.setVisible(false);
        
        listHouse.getItems().clear();
        AnchorPane ap = null;
        
        if(houseType.getSelectionModel().getSelectedItem() == null){
            errorInfo.setText("Select Which House Type u want to search ?");
        }
        else if(locationTxt.getText() == null ||
                locationTxt.getText().equals("")){
            errorInfo.setText("Invalid Location Name for "+houseType.getSelectionModel().getSelectedItem());
        }
        else {
            String id = null;
            int count = 0;
            errorInfo.setText(null);
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();
            ResultSet rs = null; 

            switch (houseType.getSelectionModel().getSelectedItem()) {
                case "Room":
                    RoomBLL r =new RoomBLL();
                    id = "room_id";
                    rs = r.showSearchedRoom(locationTxt.getText());
                    break;
                case "House":
                    HouseBLL h =new HouseBLL();
                    id = "house_id";
                    rs = h.showSearchedHouse(locationTxt.getText());
                    break;
                case "Apartment":
                    ApartmentBLL a =new ApartmentBLL();
                    id = "apartment_id";
                    rs = a.showSearchedApartment(locationTxt.getText());
                    break;
                
            }
            try {
                if (!rs.isBeforeFirst()) {
                    noresultPane.setVisible(true);
                    resultPane.setVisible(false);
                    seacrhLocationLabel.setText("'" + locationTxt.getText() + "'");
                    
                } else {
                    if (noresultPane.isVisible()) {
                        noresultPane.setVisible(false);
                        animatePane(noresultPane, resultPane);
                     }
                    resultPane.setVisible(true);
                    while (rs.next()) {
                        count++;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.HOUSERESULT));
                        try {
                            ap = (AnchorPane) loader.load();

                            HouseResultController hrc = (HouseResultController) loader.getController();

                            Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                            ImagePattern im = new ImagePattern(image);

                            if (Routing.USERNAME.equals(rs.getString("username"))) {
                                hrc.viewDetailsBtn.setText("OWN POST");
                                hrc.viewDetailsBtn.setDisable(true);
                            } 
                            hrc.imageCircle.setFill(im);
                            hrc.usernameLabel.setText(rs.getString("username"));
                            hrc.posteddate.setText(rs.getDate("posted_date").toString());
                            hrc.id.setText(String.valueOf(rs.getInt(id)));
                            hrc.locationLabel.setText(rs.getString("location"));
                            hrc.houseTypeLabel.setText(houseType.getSelectionModel().getSelectedItem());
                            hrc.visibilityLabel.setText(rs.getString("visibility"));

                            if (new Validation().getDateDifferenceinDay(rs.getDate("end_date").toString()) < 0) {
                                hrc.statusLabel.setText("Post Validity Finished");
                                hrc.viewDetailsBtn.setText("Disabled");
                                hrc.viewDetailsBtn.setDisable(true);
                            } else {
                                hrc.statusLabel.setText("Available");
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        listHouse.getItems().add(ap);

                    }

                    resultFoundLabel.setText(count + " Result Found for '" +locationTxt.getText()+"'");
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
    

