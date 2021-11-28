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
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.YearMonth;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class SignUpController {
    public User currUser;
    
    @FXML
    private Pane errorPane;
    
    @FXML
    private Label reqNameLbl;
    
    @FXML 
    private Label reqUsernameLbl;
    
    @FXML 
    private Label reqPasswordLbl;
    
    @FXML
    private TextField nameTxt;
    
    @FXML
    private TextField usernameTxt;
    
    @FXML
    private PasswordField passwordTxt;
    
    @FXML
    private Button signUpBtn;
    
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
    
    public void changeScreenToCalendar(ActionEvent event) throws IOException {             
        /*Parent calendarParent = FXMLLoader.load(getClass().getResource("ToDoCalendar.fxml"));
        Scene calendarScene = new Scene(calendarParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Calendar");
        window.setScene(calendarScene);
        window.show();*/
        
        Parent calendarParent; 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ToDoCalendar.fxml"));
        calendarParent = loader.load();
        Scene calendarScene = new Scene(calendarParent, 925, 650);
        ToDoCalendarController controller = (ToDoCalendarController)loader.getController();
        controller.initData(currUser);
        controller.initializeCalendar();
        controller.setupAssignmentList();
        controller.setupAssessmentList();
        controller.populateAssignments();
        controller.populateAssessments();
        controller.colorCalendar(YearMonth.now());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Calendar");
        window.setScene(calendarScene);
        window.show();
    }
    
    public void createAccount(ActionEvent event) throws IOException {
        if (nameTxt.getText().equals("") || usernameTxt.getText().equals("") || passwordTxt.getText().equals("")) {
            if (nameTxt.getText().equals("")) {
                System.out.println("no name");
                reqNameLbl.setVisible(true);
            } else {
                reqNameLbl.setVisible(false);
            }
            
            if (usernameTxt.getText().equals("")) {
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
            reqNameLbl.setVisible(false);
            reqUsernameLbl.setVisible(false);
            reqPasswordLbl.setVisible(false);
            
            if (CsvFileReader.checkAccountExist(usernameTxt.getText()) == -1) {
                User account = new User(nameTxt.getText(), usernameTxt.getText(), passwordTxt.getText());
                CsvFileWriter.addAccount(account);
                currUser = account;

                changeScreenToCalendar(event); 
            } else {
                errorPane.setVisible(true);
                usernameTxt.setText("");
                passwordTxt.setText("");
            }
        }
        
    }
    
}