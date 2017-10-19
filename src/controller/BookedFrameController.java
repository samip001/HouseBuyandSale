/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import model.BookedBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class BookedFrameController implements Initializable {

    @FXML
    private AnchorPane noresultPane;
    @FXML
    private AnchorPane resultPane;
    @FXML
    private JFXButton noresukRefreshBtn;
    @FXML
    private JFXButton refreshBtn;
    @FXML
    private Label infoLabel;
    @FXML
    private JFXListView<AnchorPane> bookedList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadContentInListView();
    }    

    @FXML
    private void refreshFromNoresutPane(ActionEvent event) {
        loadContentInListView();
    }

    @FXML
    private void refreshListAction(ActionEvent event) {
        loadContentInListView();
    }
    
    private void loadContentInListView(){
        bookedList.getItems().clear();
        AnchorPane ap = null;
        int count = 0;
        BookedBLL b =new BookedBLL();
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();
            ResultSet rs =b.getBookedInformatioon(Routing.USERNAME,"Booked");
            try {
                if (!rs.isBeforeFirst()) {
                    resultPane.setVisible(false);
                    noresultPane.setVisible(true);
                  } else {
                    if (noresultPane.isVisible()) {
                         resultPane.setVisible(true);
                    }
                    while (rs.next()) {
                        count++;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.BOOKEDUSER));
                        try {
                            ap = (AnchorPane) loader.load();
                            
                            BookedController bc = (BookedController)loader.getController();
                            bc.requestedDateLabel.setText(rs.getDate("request_date").toString());
                            bc.bookedDateLabel.setText(rs.getDate("booked_date").toString());
                            bc.endDateLabel.setText(rs.getDate("house_end_date").toString());
                            bc.requestedDateLabel.setText(rs.getDate("request_date").toString());
                            bc.priceLabel.setText(String.valueOf(rs.getFloat("total_cost")));
                            bc.houseTypeLabel.setText(rs.getString("house_type_name"));
                            bc.id.setText(String.valueOf(rs.getInt("house_id")));
                            
                            //checking booked day to finish
                            if(rs.getString("user_status").equals("PRESENT")){
                                String enddate=rs.getDate("house_end_date").toString();
                                int day = new Validation().getDateDifferenceinDay(enddate);
                                bc.numberLabel.setText(String.valueOf(day));
                                bc.remainInfoLabel.setText(" Days remained to finish");
                                bc.activeLabel.setText("Active");
                                bc.inactiveGlyph.setVisible(false);
                            }
                            
                            //used to  get user that posted and user pic
                            ResultSet data=b.getBookedPostOwnerUsername(rs.getInt("house_id"));
                            while (data.next()) {                                    
                                    bc.usernameLabel.setText(data.getString("postownername"));
                                    Image image = new Image(Routing.USERIMAGES+data.getString("profile_name"));
                                    ImagePattern im = new ImagePattern(image);
                                    bc.imageCircle.setFill(im);
                                    
                                }
                        } catch (IOException ex) {
                            Logger.getLogger(BookedFrameController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        bookedList.getItems().add(ap);

                     }
                    infoLabel.setText("You have booked  "+count+" house" );
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
        }
    
    
}
