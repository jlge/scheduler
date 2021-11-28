/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joann
 */
public class Scheduler extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        
        Scene scene = new Scene(root, 925, 650);

        stage.setTitle("Scheduler");
        stage.setScene(scene);
        stage.show();
    } 
    
    public static void main(String[] args) {
        Launcher.main(args);
    }
    
}
