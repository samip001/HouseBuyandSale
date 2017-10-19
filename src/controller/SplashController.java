package controller;

import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXProgressBar progress;
    @FXML
    private Label loadLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PauseTransition pt = new PauseTransition(Duration.seconds(2));
        pt.setOnFinished(ev -> {
            new Splash().start();
        });
        pt.play();
    }

    class Splash extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Parent pane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));

                            Scene sc = new Scene(pane);
                            Stage stage = new Stage();
                            stage.setResizable(false);

                            stage.setScene(sc);
                            stage.setTitle("Housing Management System");
                            Image image = new Image(Routing.IMAGES + Routing.ICON);
                            stage.getIcons().add(image);
            
                            rootPane.getScene().getWindow().hide();
                            stage.show();

                        } catch (IOException ex) {
                            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
