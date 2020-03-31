package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateController {

    public Button gobutton;
    public Button exitbutton;
    public TextField uwu;
    public RadioButton inactiveradio;
    public RadioButton alumnusradio;

    public void mainmenu() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }

    public void change() throws SQLException {
        String a = uwu.getText();
        final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement stmt = null;
        if (inactiveradio.isSelected()){
            stmt = conn.prepareStatement("update PEOPLE set STATUS = 'Inactive' where PEOPLESOFT_ID = ?");
            stmt.setString(1, a);

        }
        else if (alumnusradio.isSelected()){
            stmt = conn.prepareStatement("update PEOPLE set STATUS = 'Alumnus' where PEOPLESOFT_ID = ?");
            stmt.setString(1, a);
        }
        stmt.executeUpdate();

    }


}
