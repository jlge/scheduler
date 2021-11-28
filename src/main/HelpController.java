package main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelpController {
     
    @FXML
    private Button backBtn;
      
    public void changeScreenToLanding(ActionEvent event) throws IOException {
        Parent landingPageParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        Scene landingPageScene = new Scene(landingPageParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Scheduler");
        window.setScene(landingPageScene);
        window.show();
    }
    
}