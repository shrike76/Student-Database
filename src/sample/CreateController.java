package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreateController {
    public Button createbutton;
    public Button viewbutton;
    public Button updatebutton;

    public void start() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) createbutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }
}