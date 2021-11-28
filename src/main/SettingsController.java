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

public class SettingsController {
    public User currUser;
    
    
    @FXML
    private Button backBtn;
    
    public void initData(User user) {
        currUser = user;
    }
    
    @FXML
    public void changeScreenToOverview(ActionEvent event) throws IOException {       
        Parent overviewParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserOverview.fxml"));
        overviewParent = loader.load();        
        Scene overviewScene = new Scene(overviewParent, 925, 650);
        
        UserOverviewController controller = (UserOverviewController)loader.getController();
        controller.initData(currUser);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("User Overview");
        window.setScene(overviewScene);
        window.show();
    } 
    
        
}