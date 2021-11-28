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
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class UserOverviewController {
    public User currUser;

    @FXML
    private Label welcomeTxt;
   
    @FXML
    private Button calendarBtn;
    
    @FXML
    private Button statisticsBtn;
    
    @FXML
    private Button settingsBtn;
    
    @FXML
    private Button logOutBtn;
    
    
    public void initData(User user) {
        currUser = user;
        welcomeTxt.setText("Hi " + user.getName() + "!");
    }
    
    public void changeScreenToCalendar(ActionEvent event) throws IOException {             
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
    
          
    public void changeScreenToStatistics(ActionEvent event) throws IOException {
        Parent statisticsParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
        statisticsParent = loader.load(); 
        Scene statisticsScene = new Scene(statisticsParent, 925, 650);
        StatisticsController controller = (StatisticsController)loader.getController();
        controller.initData(currUser);      
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Statistics");
        window.setScene(statisticsScene);
        window.show();
    }
    
          
    public void changeScreenToSettings(ActionEvent event) throws IOException {   
        Parent settingsParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        settingsParent = loader.load();        
        Scene settingsScene = new Scene(settingsParent, 925, 650);
        SettingsController controller = (SettingsController)loader.getController();
        controller.initData(currUser);        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Settings");
        window.setScene(settingsScene);
        window.show();
    }
    
    public void changeScreenToLanding(ActionEvent event) throws IOException {
        Parent landingPageParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        Scene landingPageScene = new Scene(landingPageParent, 925, 650);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setTitle("Scheduler");
        window.setScene(landingPageScene);
        window.show();
    }
    
    public void logout(ActionEvent event) throws IOException {
        //UserSession.cleanUserSession();
        changeScreenToLanding(event);
    }
    
}