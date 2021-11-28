package main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LandingPageController {
    @FXML
    private Button loginBtn;
    
    @FXML
    private Button newUserBtn;
    
    @FXML
    private Button helpBtn;
    
    public void changeScreenToLogin(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(loginParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Login");
        window.setScene(loginScene);
        window.show();
    }
    
    public void changeScreenToNewUser(ActionEvent event) throws IOException {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signUpScene = new Scene(signUpParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Sign Up");
        window.setScene(signUpScene);
        window.show();
    }
        
    public void changeScreenToHelp(ActionEvent event) throws IOException {
        Parent helpParent = FXMLLoader.load(getClass().getResource("Help.fxml"));
        Scene helpScene = new Scene(helpParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Help");
        window.setScene(helpScene);
        window.show();
    }

}