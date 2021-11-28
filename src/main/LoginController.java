package main;

import java.io.IOException;
import java.time.YearMonth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
    public User currUser;
    
    @FXML
    private Pane errorPane;
    
    @FXML
    private Label loginErrorLbl;
    
    @FXML 
    private Label reqUsernameLbl;
    
    @FXML 
    private Label reqPasswordLbl;
    
    @FXML
    private TextField usernameTxt;
    
    @FXML
    private PasswordField passwordTxt;
    
    @FXML
    private Button signInBtn;
    
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
    
    public void changeScreenToOverView(ActionEvent event) throws IOException {       
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
    
    public void login(ActionEvent event) throws IOException {
        String username = usernameTxt.getText().toLowerCase();
        
        if (username.equals("") || passwordTxt.getText().equals("")) {
            if (username.equals("")) {
                reqUsernameLbl.setVisible(true);
            } else {
                reqUsernameLbl.setVisible(false);
            }
            
            if (passwordTxt.getText().equals("")) {
                reqPasswordLbl.setVisible(true);
            } else {
                reqPasswordLbl.setVisible(false);
            }
            
        } else {
            reqUsernameLbl.setVisible(false);
            reqPasswordLbl.setVisible(false);
        
            if (CsvFileReader.checkAccountExist(username) != -1) {
                if (CsvFileReader.checkValidLogin(username, passwordTxt.getText())) {
                    errorPane.setVisible(false);
                    currUser = new User(CsvFileReader.getAccountName(username), username, passwordTxt.getText());
                    changeScreenToOverView(event);
                    System.out.println(username + " logged in successfully.");
                } else {
                    errorPane.setVisible(true);
                }

            } else {
                errorPane.setVisible(true);
                System.out.println("account doesnt exist");
            }
        }
    }
    
}