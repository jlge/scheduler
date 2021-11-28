package main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskController {
    User currUser;
    
    @FXML
    private TextField assignTitleTxt;
    
    @FXML
    private TextField assignCourseTxt;
    
    @FXML
    private DatePicker dueDatePicker;
    
    @FXML
    private TextArea assignDetailsTxt;
    
    @FXML
    private TextField assessTitleTxt;
    
    @FXML
    private TextField assessCourseTxt;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TextArea assessDetailsTxt;
    
    @FXML
    private Button submitAssignmentBtn;
    
    @FXML
    private Button submitAssessmentBtn;
    
    public void initData(User user) {
        currUser = user;
    }
    
    public void addAssignment(ActionEvent event) throws IOException {
        if (!assignTitleTxt.getText().equals("") && !assignCourseTxt.getText().equals("") && 
                dueDatePicker.getValue() != null ) {
            CsvFileWriter.addAssignment(currUser, new Assignment(assignTitleTxt.getText(), 
                    assignCourseTxt.getText(), assignDetailsTxt.getText(), dueDatePicker.getValue(), false));
            
            Stage stage = (Stage) submitAssignmentBtn.getScene().getWindow();
            stage.close();
        }        
    }
    
    public void addAssessment(ActionEvent event) throws IOException {
        if (!assessTitleTxt.getText().equals("") && !assessCourseTxt.getText().equals("") && 
                datePicker.getValue() != null) {
            System.out.println("ADDING ASSESSMENT");
            CsvFileWriter.addAssessment(currUser, new Assessment(assessTitleTxt.getText(), 
                    assessCourseTxt.getText(), assessDetailsTxt.getText(), datePicker.getValue(), false));
            Stage stage = (Stage) submitAssignmentBtn.getScene().getWindow();
            stage.close();
        }        
    }
    
}