package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    public void initialize(){
        try{
            final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
//replace people with your table name
// Id, Name, Category are column names in this example, You can use the column names you want
            stmt.execute("create table PEOPLE\n" +
                    "(\n" +
                    "    PEOPLESOFT_ID   INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                    "    FIRST_NAME      VARCHAR(20),\n" +
                    "    LAST_NAME       VARCHAR(20),\n" +
                    "    COUGARNAME      VARCHAR(8),\n" +
                    "    DATE_OF_JOINING DATE,\n" +
                    "    EMAIL           VARCHAR(20),\n" +
                    "    TYPE            VARCHAR(40),\n" +
                    "    STATUS          VARCHAR(20)\n" +
                    ")");
            stmt.execute("alter table PEOPLE\n" +
                    "    add constraint PEOPLE_PK\n" +
                    "        primary key (PEOPLESOFT_ID)\n");
            stmt.close();
            System.out.println("Success!..Table created");
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table already exists");
        }
    }

}