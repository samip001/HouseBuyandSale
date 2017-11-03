/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import data.User;
import data.UserDescription;
import javafx.scene.control.TextArea;
import model.UserBLL;
import model.UserDescriptionBLL;
import org.controlsfx.control.Notifications;


/**
 *
 * @author samip
 */
public class SignUpController implements Initializable{

    @FXML
    public AnchorPane rootPane;
    @FXML
    public JFXTextField firstNameTxt;
    @FXML
    private JFXTextField lastNameTxt;
    @FXML
    private JFXTextField userNameTxt;
    @FXML
    private JFXTextField addressTxt;
    @FXML
    private JFXPasswordField passwordTxt;
    @FXML
    private JFXDatePicker dobtxt;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton other;
    @FXML
    private JFXPasswordField rePasswordTxt;
    @FXML
    private AnchorPane profilePane;
    @FXML
    private AnchorPane descriptionPane;
    @FXML
    private AnchorPane upPane;
    @FXML
    private Label imageLbl;
    @FXML
    private AnchorPane rippane;
    @FXML
    private Button addProfileBtn;
    @FXML
    private Button backBtn;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane completePane;
    @FXML
    private JFXButton completeNextBtn;
    @FXML
    private JFXButton nextBtn;
    @FXML
    public CheckBox agreeConditions;
    private Label info;
    @FXML
    private Label error;
    @FXML
    private JFXTextField numberTxt;
    @FXML
    private Circle circle;
    @FXML
    private JFXSpinner checkSpinner;
    @FXML
    private JFXButton profileNxtBtn;
    @FXML
    private JFXButton profileLaterBtn;
    @FXML
    private Label profileError;
    @FXML
    private JFXTextArea profileDescriptionTxt;
    @FXML
    private Label profileInfo;
    @FXML
    private JFXComboBox<String> profileVisibilty;
    
    private File file;
    private Validation validate;
//    SimpleDateFormat sdf;
//    String todayDate;
    private User user;
//    Query query;
    @FXML
    private Text username;
    @FXML
    private AnchorPane termsandcoditionPane;
    @FXML
    private TextArea txtarea;
    @FXML
    private JFXButton rejectBtn;
    @FXML
    private JFXButton acceptBtn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validate =new Validation();
        profileVisibilty.getItems().addAll("Private","Public");
//        rootPane.setOpacity(0);
        
        descriptionPane.setOpacity(0);
        completePane.setOpacity(0);
//        sdf= new SimpleDateFormat("yyyy-MM-dd");
        setContentinTermsCondition();
        fadeIn();
        
        firstNameTxt.requestFocus(); 
        
        ToggleGroup gender =new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        other.setToggleGroup(gender);
        
        JFXRippler riipler =new JFXRippler(addProfileBtn);
        riipler.setMaskType(JFXRippler.RipplerMask.RECT);
        riipler.setRipplerFill(Paint.valueOf("#1564C0"));
        
        JFXRippler riipler2 =new JFXRippler(backBtn);
        riipler2.setMaskType(JFXRippler.RipplerMask.RECT);
        riipler2.setRipplerFill(Paint.valueOf("#141414"));
        
        rippane.getChildren().add(riipler);
        stackPane.getChildren().add(riipler2);
    }
    
    @FXML
    private void signUpControl(ActionEvent event) {
        //make pause Transitions
        error.setText("");
        checkSpinner.setVisible(true);
        
        PauseTransition pt =new PauseTransition(Duration.seconds(3));
        pt.setOnFinished((e)->{
            if (!validate.validateTextField(firstNameTxt.getText())) {
                errorAlert("*Invalid First Name");
                firstNameTxt.requestFocus();
            } else if (!validate.validateTextField(lastNameTxt.getText())) {
                errorAlert("*Invalid Last Name");
                lastNameTxt.requestFocus();
            } else if (!validate.validateUsername(userNameTxt.getText())) {
                errorAlert("*Invalid Username ");
                userNameTxt.requestFocus();
            } else if (!validate.validatePassword(passwordTxt.getText())) {
                errorAlert("*Password Must Contain One Small, Capital, Number & Special Character");
                passwordTxt.requestFocus();
            } else if (!passwordTxt.getText().equals(rePasswordTxt.getText())) {
                errorAlert("*Password Not Match");
                rePasswordTxt.setText(null);
                passwordTxt.requestFocus();
            } else if (dobtxt.getValue()== null) {
                errorAlert("*Please Choose Valid Date");
                dobtxt.requestFocus();
            } else if (validate.getDateDifferenceinYear(dobtxt.getValue().toString()) == 1
                || validate.getDateDifferenceinYear(dobtxt.getValue().toString()) < 16) {
                errorAlert("*Age Must be Greater Than 16");
                dobtxt.requestFocus();
            } else if (!validate.validateContactNumber(numberTxt.getText())) {
                errorAlert("*Invalid Contact Number");
                numberTxt.requestFocus();
            } else if (!validate.validateAddress(addressTxt.getText())) {
                errorAlert("*Invalid Address");
                addressTxt.requestFocus();
            } else if (getGender() == null) {
                errorAlert("*Choose Gender");
            } else if (getProfileVisibilty() == null) {
                errorAlert("*Choose Visibilty For Your Profile");
            } else if (!agreeConditions.isSelected()) {
                errorAlert("*Agree Terms and Condtions for Sign Up");
            } else {
                //backend code
                user =new User();
                user.setUsername(userNameTxt.getText().trim());
                user.setFirstName(validate.makeCapitalLetter(firstNameTxt.getText().trim()));
                user.setLastName(validate.makeCapitalLetter(lastNameTxt.getText().trim()));
                user.setPassword(passwordTxt.getText().trim());
                user.setDob(java.sql.Date.valueOf(dobtxt.getValue()));
                user.setContactNo(numberTxt.getText().trim());
                user.setAddress(validate.makeCapitalLetter(addressTxt.getText().trim()));
                user.setGender(getGender());
                user.setVisibility(getProfileVisibilty());
                user.setTermsConditions("Agreed");
                
                UserBLL ubll =new UserBLL();
                if (ubll.checkRepeatedUsername(user)) {
                    errorAlert("*User Already Exists. Try Another Username");
                }
                else{
                    //registered user in system
                    ubll.saveUser(user);
                    
                    //based on gender user description it is set;
                    UserDescription userdesc =new UserDescription();
                    if (getGender().equals("Male")) {
                        userdesc.setProfileName("male.png");
                    }
                    else if(getGender().equals("Female")){
                        userdesc.setProfileName("female.png");
                    }
                    else{
                        userdesc.setProfileName("other.png");
                    }
                    userdesc.setProfileDescription("");
                    userdesc.setUsername(user);
                    
                    //insert into user description
                    UserDescriptionBLL udbll =new UserDescriptionBLL();
                    udbll.addProfileDescription(userdesc);
                            
                    Notifications nf = Notifications.create()
                            .title("Registration")
                            .text(validate.makeCapitalLetter(firstNameTxt.getText()) + " Account Sucessfully Created")
                            .position(Pos.BOTTOM_RIGHT)
                            .hideAfter(Duration.seconds(4))
                            .graphic(null);
                    nf.darkStyle();
                    nf.show();
                    
                    animatePane(profilePane, descriptionPane);
                    backBtn.setVisible(false);
                }
            }
        });
        pt.play();
    }

    //for fading in scene
    private void fadeIn(){
        FadeTransition fadeout =new FadeTransition();
        fadeout.setDuration(Duration.millis(100));
        fadeout.setNode(rootPane);
        fadeout.setFromValue(0);
        fadeout.setToValue(1);
        
        fadeout.play();
    }

    @FXML
    private void selectImage(MouseEvent event) {
        profileError.setText("");
        Node node = (Node) event.getSource();
        FileChooser choose = new FileChooser();
        choose.setTitle("Choose Image");
        choose.setInitialDirectory(new File(System.getProperty("user.home")));
        choose.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
        );
         file = choose.showOpenDialog(node.getScene().getWindow());

        if (file != null) {
            imageLbl.setText("");
            addProfileBtn.setText("Change Image");
            Image imag = new Image(file.toURI().toString());
            ImagePattern im =new ImagePattern(imag);
            circle.setVisible(true);
            circle.setFill(im);
        } 
        else {
                imageLbl.setText("File Not Selected");
        }
    }
    
    @FXML
    private void agreeTerms(ActionEvent event) {
        animatePane(profilePane, termsandcoditionPane);
     }
    private String getGender(){
        if(male.isSelected()){
           return male.getText();
        }
        else if(female.isSelected()){
            return female.getText();
        }
        else if(other.isSelected()){
            return other.getText();
        }
       return null;
        
    }
    
    //used for making alert for all
    private void errorAlert(String error){
        if(checkSpinner.isVisible()){
         this.error.setText(error);   
        }
        checkSpinner.setVisible(false);
         
    }
    
    private String getImagename(){
         try {
            if (!file.getName().equals("")) {
                return file.getName().trim();
            } else {
                return null;
            }
            
        } catch (NullPointerException e) {
            return null;
        }
    }

    @FXML
    private void profileNext(ActionEvent event) {
       if(getImagename() == null){
           profileError.setText("*Please Choose Valid Images");
       }
       else if(profileDescriptionTxt.getText().trim().equals("")){
           profileError.setText("*Please Write Some of your description");
       }
       else if(profileDescriptionTxt.getText().length()<5){
           profileError.setText("*Description too Short");
           
       }
       else{
           //path from source to targetted on disk
            Path src = file.toPath();
            Path target =Paths.get("src/"+Routing.USERIMAGES+src.getFileName());    
            //copy fiile 
            try {
                Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
           } catch (IOException ex) {
                System.out.println("Error "+ex.getMessage());
            }
           UserDescription ud =new UserDescription();
           UserDescriptionBLL udbll =new UserDescriptionBLL();
           
           ud.setUsername(user);
           ud.setProfileName(file.getName().trim());
           ud.setProfileDescription(validate.makeCapitalLetter(profileDescriptionTxt.getText().trim()));
           
           //updating profile Description
           udbll.updateProfileDescription(ud);
           
           //setting value in complete pane after profile description
            username.setText(user.getUsername());
            animatePane(descriptionPane, completePane);
       }
    }

    @FXML
    private void profileLater(ActionEvent event) {
        String filename=null;
        if(user.getGender().equals("Female")){
            filename="female.png";
        }
        else if(user.getGender().equals("Male")){
            filename="male.png";
        }
        else{
            filename="other.png";
        }
            
        UserDescription ud = new UserDescription();
        UserDescriptionBLL udbll = new UserDescriptionBLL();

        ud.setUsername(user);
        ud.setProfileName(filename);
        ud.setProfileDescription("");

        //updating profile Description 
        udbll.updateProfileDescription(ud);

        //setting value in complete pane after profile description
        username.setText(user.getUsername());
    
        animatePane(descriptionPane, completePane);
}
    
    private String getProfileVisibilty(){
        if(profileVisibilty.getSelectionModel().isEmpty()){
            return null;
        }
        else{
            return profileVisibilty.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void completeRegistration(ActionEvent event) {
        loadLoginPage(event);
    }

    // to go in login page through action event
    public void loadLoginPage(ActionEvent event){
         try {
            Parent root = FXMLLoader.load(this.getClass().getResource(Routing.LOGIN));
            Scene sc = new Scene(root);

            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(sc);
            s.setTitle("Home Buy and Sale Manage");
            s.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void directtoLoginPage(MouseEvent event) {
        //it is in mouse event
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource(Routing.LOGIN));
            Scene sc = new Scene(root);

            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(sc);
            s.setTitle("House Buy and Sale Manage");
            s.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void animatePane(AnchorPane pane1, AnchorPane pane2) {
        FadeAnimation fd = new FadeAnimation();
        fd.fadeOut(pane1, pane2);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void termsRejectedAction(ActionEvent event) {
        loadLoginPage(event);
    }

    @FXML
    private void termAgreeAction(ActionEvent event) {
        agreeConditions.setSelected(true);
        animatePane(termsandcoditionPane, profilePane);
    }
    
    private void setContentinTermsCondition(){
         txtarea.setText("Terms and Coondition\n\n" +
                    "Please read these Terms and Conditions carefully before using the  desktop application operated by XYZ Company\n\n"
                 +"Your access to and use of the Service is conditioned on your acceptance of and compliance with these Terms. \n\n"
                 +"1. Introduction\n"
                 +"These Terms will be applied fully and affect to your use of this application. "
                 + "By using this apllication, you agreed to accept all terms and conditions written in here. "
                 + "You must not use this application if you disagree with any of these application Standard Terms and Conditions.\n" +
                        "Minors or people below 16 years old are not allowed to use this application.\n\n\n" 
                 +"2. Intellectual Property Rights\n"
                 + "Other than the content you own, under these Terms, XYZ "
                 + "and/or its licensors own all the intellectual property rights and materials contained in this application.\n" +
                    "You are granted limited license only for purposes of viewing the material contained on this application.\n\n\n" +
                    "3. Restrictions\n" +
"You are specifically restricted from all of the following\n" +
"•  publishing any application material in any other media;\n" +
"•  selling, sublicensing and/or otherwise commercializing any application material;\n" +
"•  publicly performing and/or showing any application material;\n" +
"•  using this application in any way that is or may be damaging to this application;\n" +
"•  using this application in any way that impacts user access to this application;\n" +
"•  using this application contrary to applicable laws and regulations, or in any way may cause harm to the application, or to any person or business entity;\n" +
"•  engaging in any data mining, data harvesting, data extracting or any other similar activity in relation to this application;\n" +
"•  using this application to engage in any advertising or marketing.\n" +
                "Certain areas of this application are restricted from being access by you and XYZ may further restrict access by you to any areas of this application, at any time, in absolute discretion. "
                 + "Any user ID and password you may have for this application are confidential and you must maintain confidentiality as well.\n\n\n" +
            "4.	Your Content\n" +
"In these application Standard Terms and Conditions, “Your Content” shall mean any audio, video text, images or other material you choose to display on this application. "
                 + "By displaying Your Content, you grant XYZ a non-exclusive, worldwide irrevocable, sub licensable license to use, reproduce, adapt, publish, translate and distribute it in any and all media.\n" +
"Your Content must be your own and must not be invading any third-party’s rights. XYZ reserves the right to remove any of Your Content from this application at any time without notice.\n\n\n" +
"5. No warranties\n" +
"This application is provided “as is,” with all faults, and XYZ express no representations or warranties, of any kind related to this application or the materials contained on this application. Also, nothing contained on this application shall be interpreted as advising you.\n\n\n" +
"6. Limitation of liability\n" +
"In no event shall XYZ, nor any of its officers, directors and employees, shall be held liable for anything arising out of or in any way connected with your use of this application whether such liability is under contract.  "
                 + "XYZ, including its officers, directors and employees shall not be held liable for any indirect, consequential or special liability arising out of or in any way related to your use of this application.\n\n\n" +
"7. Indemnification\n" +
"You hereby indemnify to the fullest extent XYZ from and against any and/or all liabilities, costs, demands, causes of action, damages and expenses arising in any way related to your breach of any of the provisions of these Terms.\n\n\n" +
"8. Severability\n" +
"If any provision of these Terms is found to be invalid under any applicable law, such provisions shall be deleted without affecting the remaining provisions herein.\n\n\n" +
"9. Variation of Terms\n" +
"XYZ is permitted to revise these Terms at any time as it sees fit, and by using this application you are expected to review these Terms on a regular basis.\n\n\n" +
"10. Assignment\n" +
"The XYZ is allowed to assign, transfer, and subcontract its rights and/or obligations under these Terms without any notification. However, you are not allowed to assign, transfer, or subcontract any of your rights and/or obligations under these Terms.\n\n\n" +
"11. Entire Agreement\n" +
"These Terms constitute the entire agreement between XYZ and you in relation to your use of this application, and supersede all prior agreements and understandings.\n\n\n" +
"12.	Governing Law & Jurisdiction\n" +
"These Terms will be governed by and interpreted in accordance with the laws of the State of Kathmandu, and you submit to the non-exclusive jurisdiction of the state and federal courts located in Kathmandu for the resolution of any disputes."
         );
    }
}
    
    
   

