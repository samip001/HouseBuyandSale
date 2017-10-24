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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import data.User;
import data.UserDescription;
import java.io.FileWriter;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import model.UserBLL;
import model.UserDescriptionBLL;

/**
 * FXML Controller class
 *
 * @author samip
 */
public class ProfileOverviewController implements Initializable {

    @FXML
    private Circle imageCircle;
    @FXML
    private Label user1Label;
    @FXML
    private Label nameLabel;
    @FXML
    private Label user2Label;
    @FXML
    private Label genderLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private MaterialDesignIconView genderGlyph;
    @FXML
    private Label contactLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private JFXButton updateProfileBtn;
    @FXML
    private JFXButton updateProfileDescBtn;
    @FXML
    private Label visibiltyLabel;
    @FXML
    private StackPane noDescriptionPane;
    @FXML
    private AnchorPane descriptionPane;
    @FXML
    private TextArea profileDescription;
    @FXML
    private AnchorPane profileViewPane;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private JFXTextField firstNameTxt;
    @FXML
    private JFXTextField lastNameTxt;
    @FXML
    private JFXDatePicker dobtxt;
    @FXML
    private JFXTextField numberTxt;
    @FXML
    private JFXTextField addressTxt;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton other;
    @FXML
    private JFXComboBox<String> profileVisibilty;
    @FXML
    private JFXButton profilenextBtn;
    @FXML
    private JFXButton profileBackBtn;
    @FXML
    private Label error;
    @FXML
    private JFXSpinner profileSpinner;
    @FXML
    private AnchorPane upPane;
    @FXML
    private Label imageLbl;
    @FXML
    private AnchorPane rippane;
    @FXML
    private Button addProfileBtn;
    @FXML
    private Circle imagecircle;
    @FXML
    private JFXTextArea profileDescriptionTxt;
    @FXML
    private Label profileError;
    @FXML
    private JFXButton descriptionNxtBtn;
    @FXML
    private JFXButton descriptionBackBtn;
    @FXML
    private AnchorPane passwordPane;
    @FXML
    private JFXPasswordField passwordFiledTxt;
    @FXML
    private AnchorPane profiledescriptionPane;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private JFXButton cancelBtn;
    
    private String actionType;
    private String imageName, dbImageName;
    private File file;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        setAllData();
//        setDataInForm();

        ToggleGroup gender = new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        other.setToggleGroup(gender);

        profileVisibilty.getItems().addAll("Private", "Public");

    }

    //Used to set data
    private void setAllData() {
        //in profile pane
        DatabaseConnection db = DatabaseConnection.getInstanceofDB();
        Connection conn = db.connectDB();

        UserBLL ubll = new UserBLL();
        ResultSet userdata = ubll.getUserProfileDetails(Routing.USERNAME);
        try {
            while (userdata.next()) {

                user1Label.setText(userdata.getString("username"));
                nameLabel.setText(userdata.getString("first_name")+" " +userdata.getString("last_name"));
                user2Label.setText(userdata.getString("username"));
                genderLabel.setText(userdata.getString("gender"));
                dobLabel.setText(userdata.getDate("dob").toString());
                contactLabel.setText(userdata.getString("contact_no"));
                addressLabel.setText(userdata.getString("address"));
                visibiltyLabel.setText(userdata.getString("visibility"));

                firstNameTxt.setText(userdata.getString("first_name"));
                lastNameTxt.setText(userdata.getString("last_name"));
                numberTxt.setText(userdata.getString("contact_no"));
                addressTxt.setText(userdata.getString("address"));

                //setting up date
                Date e = (Date)userdata.getDate("dob");
                LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(e));
                dobtxt.setValue(localDate);

                    switch (userdata.getString("gender")) {
                        case "Male":
                            male.setSelected(true);
                            break;
                        case "Female":
                            female.setSelected(true);
                            break;
                        default:
                            other.setSelected(true);
                            break;
                    }
                
                if ("Public".equals(userdata.getString("visibility"))) {
                    profileVisibilty.setValue("Public");
                } else {
                    profileVisibilty.setValue("Private");
                }
        
                if ("Male".equals(userdata.getString("gender"))) {
                    genderGlyph.setGlyphName("GENDER_MALE");
                } else {
                    genderGlyph.setGlyphName("GENDER_FEMALE");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // used in profile description
        UserDescriptionBLL udbll =new UserDescriptionBLL();
        ResultSet rs = udbll.getUserDescription(Routing.USERNAME);
        try {
            while (rs.next()) {
                // used when loading at initial
                ImagePattern im = null;
                Image image = new Image(Routing.USERIMAGES + rs.getString("profile_name"));
                im = new ImagePattern(image);
                imageCircle.setFill(im);
                
                if (rs.getString("profile_description").equals("")) {
                    noDescriptionPane.setVisible(true);
                    descriptionPane.setVisible(false);
                } else {
                    noDescriptionPane.setVisible(false);
                    descriptionPane.setVisible(true);
                    profileDescription.setText(rs.getString("profile_description"));
                }

                //used for updating profile descritpion
                imagecircle.setFill(im);
                profileDescriptionTxt.setText(rs.getString("profile_description"));
                
                //get image name same from database for checking
                dbImageName = rs.getString("profile_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseResultController.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.disconnect(conn);

    }

    @FXML
    private void profileToPasswordPane(ActionEvent event) {
        profileSpinner.setVisible(true);
        error.setVisible(false);

        Validation validate = new Validation();
        if (!validate.validateTextField(firstNameTxt.getText())) {
            errorAlert("Invalid First Name");
        } else if (!validate.validateTextField(lastNameTxt.getText())) {
            errorAlert("Invalid Last Name");
        } else if (dobtxt.getValue() == null) {
            errorAlert("Please Choose Valid Date");
        } else if (validate.getDateDifferenceinYear(dobtxt.getValue().toString()) == 1
                || validate.getDateDifferenceinYear(dobtxt.getValue().toString()) < 16) {
            errorAlert("Age Must be Greater Than 16");
        } else if (!validate.validateContactNumber(numberTxt.getText())) {
            errorAlert("Invalid Contact Number");
        } else if (!validate.validateAddress(addressTxt.getText())) {
            errorAlert("Invalid Address");
        } else if (getGender() == null) {
            errorAlert("Choose Gender");
        } else if (getProfileVisibilty() == null) {
            errorAlert("Choose Visibilty For Your Profile");
        } else {
            profileSpinner.setVisible(false);
            animatePane(profilePane, passwordPane);
        }
    }

    @FXML
    private void profileToViewProfile(ActionEvent event) {
        animatePane(profilePane, profileViewPane);
    }

    @FXML
    private void selectImage(MouseEvent event) {
        Node node = (Node) event.getSource();
        FileChooser choose = new FileChooser();
        choose.setTitle("Choose Image");
        choose.setInitialDirectory(new File(System.getProperty("user.home")));
        choose.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif")
        );
        file = choose.showOpenDialog(node.getScene().getWindow());

        if (file != null) {
            //used to compare with database image name
            imageName = file.getName();
            
            imageLbl.setText("");
            addProfileBtn.setText("Change Image");
            Image imag = new Image(file.toURI().toString());
            ImagePattern im = new ImagePattern(imag);
            imagecircle.setFill(im);

        } else {
            Image imag = new Image(Routing.USERIMAGES + dbImageName);
            ImagePattern im = new ImagePattern(imag);
            imagecircle.setFill(im);
        }
    }

    @FXML
    private void descriptionToPassword(ActionEvent event) {
        if (profileDescriptionTxt.getText().trim().equals("")) {
            profileError.setText("Please Write Some of your description");
        } else if (profileDescriptionTxt.getText().length() < 5) {
            profileError.setText("Description too Short");

        } else if (!new Validation().validateTextArea(profileDescriptionTxt.getText())) {
            profileError.setText(("Invalid User Description"));
        } else {
            profileError.setText("");
            animatePane(profiledescriptionPane, passwordPane);
        }
    }

    @FXML
    private void descriptionToViewProfile(ActionEvent event) {
        animatePane(profiledescriptionPane, profileViewPane);
    }

    @FXML
    private void UpdateProfileAction(ActionEvent event) {
        
        UserBLL ubll = new UserBLL();
        UserDescriptionBLL udll = new UserDescriptionBLL();
        User user = new User(Routing.USERNAME);
        UserDescription ud = new UserDescription();
        Validation validate = new Validation();
        String password = passwordFiledTxt.getText();
        //query for vhecking password

        if (ubll.isValidUserandPassword(Routing.USERNAME, password)) {
            if (actionType.equals("Profile Details")) {
                user.setFirstName(validate.makeCapitalLetter(firstNameTxt.getText().trim()));
                user.setLastName(validate.makeCapitalLetter(lastNameTxt.getText().trim()));
                user.setDob(java.sql.Date.valueOf(dobtxt.getValue()));
                user.setContactNo(numberTxt.getText().trim());
                user.setAddress(validate.makeCapitalLetter(addressTxt.getText().trim()));
                user.setGender(getGender());
                user.setVisibility(getProfileVisibilty());

                //business logic updates user profile
                ubll.updateProfileDetailse(user);

            } else {
                //used when manipulating image
                if (imageName.equals("")) {
                    ud.setProfileName(dbImageName);
                } else if (!imageName.equals(dbImageName)) {
                    System.out.println(imageName + "iamge name not equal");
                    ud.setProfileName(imageName);
                    Path src = file.toPath();
                    Path target = Paths.get("src/" + Routing.USERIMAGES + src.getFileName());
                    try {
                        Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);

                    } catch (IOException ex) {
                        System.out.println("Error " + ex.getMessage());
                    }
                } else {
                    ud.setProfileName(dbImageName);
                }
                ud.setProfileDescription(profileDescriptionTxt.getText());
                ud.setUsername(user);

                //Buisness Logic updates profile image and description
                udll.updateProfileDescription(ud);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Profile Update Details");
            alert.setHeaderText(null);
            alert.setContentText("Sucessfully Profile Updated.\n\nPlease Login For Change Information");

            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));

            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                String storeUserandPass = "user=\npassword=";
                //file user and password must be ket empty
                try {
                    try (FileWriter fw = new FileWriter(Routing.HELPER + "user.properties")) {
                        fw.write(storeUserandPass);
                        fw.close();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Parent root = FXMLLoader.load(this.getClass().getResource(Routing.LOGIN));
                    Scene sc = new Scene(root);

                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Stage s = new Stage();
                    s.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));
                    s.setScene(sc);
                    s.setTitle("Housing Management System");
                    s.show();
                } catch (IOException ex) {
                    Logger.getLogger(ProfileOverviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Password not macth! Try Again");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(Routing.IMAGES + Routing.ICON));

            alert.showAndWait();

            animatePane(passwordPane, profileViewPane);
        }
   }

    private void errorAlert(String error) {
        if (profileSpinner.isVisible()) {
            this.error.setVisible(true);
            this.error.setText(error);
        }
        profileSpinner.setVisible(false);

    }

    private String getProfileVisibilty() {
        if (profileVisibilty.getSelectionModel().isEmpty()) {
            return null;
        } else {
            return profileVisibilty.getSelectionModel().getSelectedItem();
        }
    }

    private String getGender() {
        if (male.isSelected()) {
            return male.getText();
        } else if (female.isSelected()) {
            return female.getText();
        } else if (other.isSelected()) {
            return other.getText();
        }
        return null;

    }

    @FXML
    private void viewtoprofileForm(ActionEvent event) {
        animatePane(profileViewPane, profilePane);
        setAllData();
        actionType = "Profile Details";
    }

    @FXML
    private void viewToDescritpionPane(ActionEvent event) {
        animatePane(profileViewPane, profiledescriptionPane);
        setAllData();
        actionType = "Profile Description";
    }

    @FXML
    private void cancelUpdating(ActionEvent event) {
        actionType = null;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Profile Update");
        alert.setHeaderText(null);
        alert.setContentText("Are You Sure cancel? ");
        Stage alertstage = (Stage)alert.getDialogPane().getScene().getWindow();
        alertstage.getIcons().add(new Image(Routing.IMAGES+Routing.ICON));
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            animatePane(passwordPane, profileViewPane);
            actionType = null;
        } else {
            alert.close();
        }

    }

    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }
}
