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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import data.User;
import model.FolllowRequestBLL;
import model.UserBLL;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class SearchUserController implements Initializable {

    @FXML
    private TextField usernameTxt;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private AnchorPane resultPane;
    @FXML
    private AnchorPane noresultPane;
    @FXML
    private Label errorLabel;
    @FXML
    private Text searchedUsenameTxt;
    @FXML
    private Label resultFoundLabel;
    @FXML
    private JFXListView<AnchorPane> userlis;
    
    int initialcheckload=0;

    private UserBLL ubll;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ubll =new UserBLL();
        List<User> user = ubll.getAllRegisteredUsername(Routing.USERNAME);
        List<String> name = new ArrayList<>();
        for (User us : user) {
            name.add(us.getUsername());
        }
        TextFields.bindAutoCompletion(usernameTxt, name);
        
        if (initialcheckload == 1) {
            searchUserAction();
        }
    }

    @FXML
    private void searchUserActon(ActionEvent event) {
        initialcheckload=1;
        searchUserAction();
    }
    
    private void searchUserAction(){
        noresultPane.setVisible(false);
        resultPane.setVisible(false);

        FadeAnimation fd = new FadeAnimation();
        String username = usernameTxt.getText().trim();
        userlis.getItems().clear();
        AnchorPane ap = null;
        int count = 0;
        if (username.equals("")) {
            errorLabel.setText("Invalid Username");
            usernameTxt.requestFocus();
        } else {
            errorLabel.setText(null);
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            Connection conn = db.connectDB();
            ResultSet rs = ubll.listAllSearchedUser(username);
            try {
                if (!rs.isBeforeFirst()) {
                    searchedUsenameTxt.setText("'" + username + "'");
                    noresultPane.setVisible(true);
                } else {
                    resultPane.setVisible(true);
                   
                    while (rs.next()) {
                        count++;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(Routing.USERRESULT));
                        try {
                            ap = (AnchorPane) loader.load();
                            UserResultController urc = (UserResultController)loader.getController();
                            
                            Image image = new Image(Routing.USERIMAGES+rs.getString("profile_name"));
                            ImagePattern im = new ImagePattern(image);
                            FolllowRequestBLL fr =new FolllowRequestBLL();
                            
                            String activity=fr.checkPreviousFollowActivity(Routing.USERNAME,rs.getString("username"));
                            
                            if(null==activity){
                                urc.followBtn.setText("Follow");
                                urc.followBtn.setVisible(true);
                            }
                            else switch (activity) {
                                case "Requested":
                                    urc.followBtn.setText("Requested");
                                    urc.followBtn.setDisable(true);
                                    break;
                                default:
                                    urc.followBtn.setText("Accepted");
                                    urc.followBtn.setDisable(true);
                                    break;
                            }
                             if(Routing.USERNAME.equals(rs.getString("username"))){
                                    urc.followBtn.setVisible(false);
                                    urc.viewProfileBtn.setVisible(true);
                                    urc.viewProfileBtn.setText("Own Profile");
                            }
                             
                                urc.imageCircle.setFill(im);
                                urc.usernameLabel.setText(rs.getString("username"));
                                urc.genderLabel.setText(rs.getString("gender"));
                                urc.visiibiltyLabel.setText(rs.getString("visibility"));
                        } catch (IOException ex) {
                            Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        userlis.getItems().add(ap);
                    }
                    resultFoundLabel.setText(count + " Result Found for '" + username+"'");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            db.disconnect(conn);
        }
    }

}
