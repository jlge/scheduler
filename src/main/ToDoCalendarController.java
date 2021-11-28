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
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public class ToDoCalendarController {
    public User currUser;
    private YearMonth currentYearMonth;
    private ArrayList<AnchorPane> allCalendarDays = new ArrayList<>(35);

    @FXML
    private Label calendarLbl;
        
    @FXML
    private GridPane calendar;
    
    @FXML
    private Label monthYearLbl;
    
    @FXML
    private ListView<Assignment> assignmentList;
    private ArrayList<Assignment> assignmentArray = new ArrayList<Assignment>();
    
    @FXML
    private ListView<Assessment> assessmentList;
    private ArrayList<Assessment> assessmentArray = new ArrayList<Assessment>();
      
    @FXML
    private Button newTaskBtn;
    
    @FXML
    private Button prevMonthBtn;
    
    @FXML
    private Button nextMonthBtn;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private Button markDoneBtn;
    
    public void initData(User user) {
        currUser = user;
        currentYearMonth = YearMonth.now();
        String name = user.getName().substring(0,1).toUpperCase() + user.getName().substring(1).toLowerCase();
        calendarLbl.setText(name + "'s Calendar");
    }
    
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
       
    public void initializeCalendar() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPane ap = new AnchorPane();
                ap.setPrefSize(200,200);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        populateCalendar(currentYearMonth);
        
    }
       
    public void populateCalendar(YearMonth yearMonth) {                        
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        
        for (AnchorPane ap : allCalendarDays) {
            if (!ap.getChildren().isEmpty()) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        
        monthYearLbl.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }
    
    public void previousMonth(ActionEvent event) throws IOException {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
        colorCalendar(currentYearMonth);
    }

    public void nextMonth(ActionEvent event) throws IOException {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
        colorCalendar(currentYearMonth);
    }
    
    public void completeTasks(ActionEvent event) throws IOException {
        for (Assignment assignment : assignmentArray) {
            if (assignment.isSelected()) {
                Assignment updated = new Assignment(assignment.getTitle(), assignment.getCourse(), 
                    assignment.getDetails(), assignment.getDueDate(), true);
                CsvFileWriter.editAssignment(currUser, assignment, updated);
                System.out.println(assignment.getTitle() + " is complete");
            }
                 
        }
        
        for (Assessment assessment : assessmentArray) {
            if (assessment.isSelected()) {
                Assessment updated = new Assessment(assessment.getTitle(), assessment.getCourse(), 
                    assessment.getDetails(), assessment.getDate(), true);
                CsvFileWriter.editAssessment(currUser, assessment, updated);
                System.out.println(assessment.getTitle() + " is studied for");
            }
                 
        }        
        
        populateAssignments();
        populateAssessments();
        colorCalendar(currentYearMonth);
        
    }
    
    public void populateAssignments() {
        assignmentArray = CsvFileReader.getAllAssignments(currUser.getUsername());
        
        assignmentList.getItems().clear();
        for (Assignment items : assignmentArray)
            addAssignmentItem(items);
        
    }
    
    public void populateAssessments() {
        assessmentArray = CsvFileReader.getAllAssessments(currUser.getUsername());
        
        assessmentList.getItems().clear();
        for (Assessment items : assessmentArray)
            addAssessmentItem(items);      
    }
    
    public void setupAssignmentList() {
        assignmentList.setCellFactory(CheckBoxListCell.forListView(Assignment::selectedProperty));
        /*
        assignmentList.setCellFactory(CheckBoxListCell.forListView(new Callback<Assignment, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Assignment assignment) {
                return assignment.getStatusValue();
            }
        }));*/
    }
    
    public void setupAssessmentList() {
        assessmentList.setCellFactory(CheckBoxListCell.forListView(Assessment::selectedProperty));
        
        /*
        assessmentList.setCellFactory(CheckBoxListCell.forListView(new Callback<Assessment, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Assessment assessment) {
                return assessment.getStudiedValue();
            }
        }));*/
    }
       
    public void addAssignmentItem(Assignment newAssignment) {
        //toDoList.setCellFactory
        newAssignment.getStatusValue().addListener((obs, wasOn, isNowOn) -> {
            System.out.println(newAssignment.getTitle() + " changed on state from " + wasOn + " to " + isNowOn);
        });
        
        assignmentList.getItems().add(newAssignment);
    }
    
     public void addAssessmentItem(Assessment newAssessment) {
        //toDoList.setCellFactory
        newAssessment.getStudiedValue().addListener((obs, wasOn, isNowOn) -> {
            System.out.println(newAssessment.getTitle() + " changed on state from " + wasOn + " to " + isNowOn);
        });
        
        assessmentList.getItems().add(newAssessment);
    }   
    
    public void openAddTask(ActionEvent event) throws IOException {
        Parent addTaskParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
        addTaskParent = loader.load();
        Scene addTaskScene = new Scene(addTaskParent, 330, 320);
        AddTaskController controller = (AddTaskController)loader.getController();
        controller.initData(currUser);
                
        Stage popup = new Stage();
        newTaskBtn.setDisable(true);
        popup.setTitle("Add Task");
        popup.setScene(addTaskScene);
        popup.initModality(Modality.WINDOW_MODAL); 
        popup.setAlwaysOnTop(true);
        popup.showAndWait();
        
        newTaskBtn.setDisable(false);
        populateAssignments();
        populateAssessments();
        colorCalendar(currentYearMonth);
        
    }
    
    public void colorCalendar(YearMonth yearMonth) {
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        int num;
        
        for (AnchorPane ap : allCalendarDays) {
            num = CsvFileReader.getAssignments(currUser.getUsername(), calendarDate).size() + CsvFileReader.getAssessments(currUser.getUsername(), calendarDate).size();
            if (num > 4) {
                ap.setStyle("-fx-background-color:#ffc7c7");
            } else if (num > 2) {
                ap.setStyle("-fx-background-color:#fff4c7");
            } else if (num > 0) {
                ap.setStyle("-fx-background-color:#c7ffc7");
            } else {
                ap.setStyle("-fx-background-color:#ffffff");
            }
            calendarDate = calendarDate.plusDays(1);
        }       
    }


   
}