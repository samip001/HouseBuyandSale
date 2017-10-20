/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.UserBLL;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class LoginController implements Initializable {
    
      @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXButton signupBtn;

    @FXML
    private AnchorPane loginPane;
    @FXML
    private JFXTextField usernameTxt;
     @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXButton loginBtn;

    @FXML
    private AnchorPane dbPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXButton checkConnectivityBtn;
    @FXML
    private AnchorPane hmsPane;
    @FXML
    private JFXSpinner load;
    @FXML
    private Text errorInfo;
    
    private DatabaseConnection db;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          if(!checkDatabaseConnection()){
            dbPane.setVisible(true);
            signupPane.setVisible(false);
            loginPane.setVisible(false);
        }
    }   
      @FXML
    void checkServerStatus(ActionEvent event) {
         if(!checkDatabaseConnection()){
            dbPane.setVisible(true);
            signupPane.setVisible(false);
            loginPane.setVisible(false);
            Notifications nf =Notifications.create()
                .title("Connection Check")
                .text("Connection Not Found")
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(4))
                .graphic(null);
        nf.darkStyle();
        nf.showError();
        }
        else{
             Notifications nf =Notifications.create()
                .title("Connection Check")
                .text("Connection Started Successfully")
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(Duration.seconds(4))
                .graphic(null);
            nf.show();
            dbPane.setVisible(false);
            signupPane.setVisible(true);
            loginPane.setVisible(true);
            //load and checj user data and diirect too dashboard if found
           }
    }

    @FXML
    void loginCheck(ActionEvent event) {
        errorInfo.setText("");
        load.setVisible(true);
        PauseTransition pt =new PauseTransition();
        pt.setDuration(Duration.seconds(2));
        pt.setOnFinished((ActionEvent e) -> {
            Validation vd = new Validation();
            if (!vd.validateAddress(usernameTxt.getText().trim())) {
                errorInfo("Invalid Username");
            } else if (passwordTxt.getText().length() < 5) {
                errorInfo("Invalid Password Length");
            } else if (!vd.validatePassword(passwordTxt.getText().trim())) {
                errorInfo("Invalid Password ! Try Next ");
            } else {
                UserBLL ubll =new UserBLL();
                
                //user check exits or not in system
                //And user exists
                if (ubll.isValidUserandPassword(usernameTxt.getText().trim(), passwordTxt.getText().trim())) {
                    
                    //remove and write new user and password
                    String storeUserandPass = "user="+usernameTxt.getText().trim()+"\npassword="+passwordTxt.getText().trim();
                    try {
                        try (FileWriter fw = new FileWriter(Routing.HELPER + "user.properties")) {
                            fw.write(storeUserandPass);
                            fw.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //spinner making invisible
                    load.setVisible(false);
                   
                    //loads dashboard 
                    try {
                        Parent root = FXMLLoader.load(LoginController.this.getClass().getResource(Routing.DASHBOARDNEW));
                        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene sc = new Scene(root);
                        s.setScene(sc);
                        s.setTitle("HRBS - "+Routing.USERNAME);
                        s.show();
                    }catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     //notified to user
                    Notifications nf = Notifications.create()
                            .title("Login")
                            .text(Routing.USERNAME + " Sucessfully Log in")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
                    nf.show();
                    
                } 
                //user not found then stop and erase name and password
                else {
                    errorInfo("User Not Found");
                    usernameTxt.requestFocus();
                    passwordTxt.setText(null);
                }
            }
        });
        pt.play();

    }

    @FXML
    void signUpUser(ActionEvent event) {
         FadeTransition fadeout =new FadeTransition();
        fadeout.setDuration(Duration.seconds(1));
        fadeout.setNode(rootPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        
        fadeout.setOnFinished((e)->{
           try {
            Parent root =FXMLLoader.load(this.getClass().getResource("/view/SignUp.fxml"));
            Scene sc= new Scene(root);
            
            Stage s = (Stage)rootPane.getScene().getWindow();
            
            s.setScene(sc);
            s.setTitle("Sign Up Form");
            s.show();
        } catch (IOException ex) {
            System.out.println("error"+ex.getMessage());
        }
        });
        
        fadeout.play();
    }
    
    public boolean checkDatabaseConnection(){
        db= DatabaseConnection.getInstanceofDB();

        if(db.connectDB() != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    //used fir error
    public void errorInfo(String error){
        errorInfo.setText(error);
        load.setVisible(false);
    }
}   

