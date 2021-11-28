package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joann
 */
public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        
        Scene scene = new Scene(root, 925, 650);

        stage.setTitle("Scheduler");
        stage.setScene(scene);
        stage.show();
    } 
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
