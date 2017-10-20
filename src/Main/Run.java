/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import model.DatabaseConnection;
import controller.Routing;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.UserBLL;
import org.controlsfx.control.Notifications;

/**
 *
 * @author samip
 */
public class Run extends Application {

    private InputStream inputStream;
    private Properties properties;
    private String username, passwrd;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public Run() {
        this.username = null;
        this.passwrd = null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;
        properties = new Properties();
        try {
            inputStream = new FileInputStream(Routing.HELPER + "user.properties");
            properties.load(inputStream);
            username = properties.getProperty("user");
            passwrd = properties.getProperty("password");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        //check if prevoius users is not logged out or not
        if (username == null || username.equals("")) {

            Parent root = FXMLLoader.load(this.getClass().getResource(Routing.SPLASH));
            scene = new Scene(root);
            // remoove the top close, maxiimise and minimise
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.setScene(scene);
            Image image = new Image(Routing.IMAGES + Routing.ICON);
            primaryStage.getIcons().add(image);
            primaryStage.show();
        } else {
            DatabaseConnection db = DatabaseConnection.getInstanceofDB();
            if (db.connectDB() != null) {
                UserBLL ubll = new UserBLL();
                if (ubll.isValidUserandPassword(username.trim(), passwrd.trim())) {
                    Parent root = FXMLLoader.load(Run.this.getClass().getResource(Routing.DASHBOARDNEW));
                    scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("HRBS - " + Routing.USERNAME);
                    Image image = new Image(Routing.IMAGES + Routing.ICON);
                    primaryStage.setResizable(false);
                    primaryStage.getIcons().add(image);
                    primaryStage.show();
                    
                     //notified to user
                    Notifications nf = Notifications.create()
                            .title("Login")
                            .text(Routing.USERNAME+" found and logged in sucessfully")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
                    nf.show();
                    
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connection Failed");
                alert.setHeaderText(null);
                alert.setContentText("No Connection Found");

                Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));
                alert.show();
            }
        }
    }
}
