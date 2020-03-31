package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.*;

import java.io.IOException;

public class MainMenuController {
    public Button createbutton;
    public Button viewbutton;
    public Button updatebutton;
    public Button exitbutton;

    public void start() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) createbutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }
    public void view() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("view.fxml"));
        Stage stage = (Stage) viewbutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }
    public void update() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("update.fxml"));
        Stage stage = (Stage) updatebutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }

    public void exit(){
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
}

}