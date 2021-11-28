package main;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatisticsController {
    public User currUser;
    
    
    @FXML
    private Button backBtn;
    
    @FXML
    private PieChart chart;
    
    @FXML
    private Label numAssignLbl;
    
    @FXML
    private Label numAssessLbl;
    
    @FXML
    private Label totalTasksCompleteLbl;
    
    @FXML
    private Label totalTasksLbl;
    
    public void initData(User user) {
        currUser = user;
        setup(currUser);
    }
    
    public void setup(User user) {
        ArrayList<ArrayList<String>> stats = CsvFileReader.getStatistics(user);
        numAssignLbl.setText(stats.get(0).get(0));
        numAssessLbl.setText(stats.get(0).get(1));
        totalTasksCompleteLbl.setText(stats.get(0).get(2));
        totalTasksLbl.setText(stats.get(0).get(3));
        createPieChart(user);
    }
    
    public void createPieChart(User user) {
        ArrayList<ArrayList<String>> stats = CsvFileReader.getStatistics(user);
        ArrayList<String> courses = stats.get(1);
        ArrayList<String> courseStats = stats.get(2);
        
        for (int i = 0; i < courses.size(); i++) {
            chart.getData().add(new PieChart.Data(courses.get(i), Double.valueOf(courseStats.get(i))));
        }
        chart.setLegendSide(Side.LEFT);
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