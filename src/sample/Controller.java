package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class Controller {
    public TextField textfirstname;
    public DatePicker textjoinindate;
    public TextField textemail;
    public TextField textlastname;
    public RadioButton radiostudent;
    public ToggleGroup group1;
    public ToggleGroup group2;
    public RadioButton radioemployee;
    public RadioButton radiostaff;
    public RadioButton radiofaculty;
    public Button go;
    public RadioButton radioundergraduate;
    public RadioButton radiograduate;
    public RadioButton radiofulltime;
    public RadioButton radiopartime;
    public RadioButton radiotenured;
    public RadioButton radionontenured;
    public ListView lv1;
    public Button viewbutton;
    public Button updatebutton;
    private ObservableList<People> studentList;
    public Button createbutton;

    public void initialize() {
        final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
        studentList = FXCollections.observableArrayList();
        go.setOnAction(ActionEvent ->{
            try {
                Connection conn = DriverManager.getConnection(DB_URL);
                ;
                PreparedStatement stmt = null;
                String first = textfirstname.getText();
                String last = textlastname.getText();
                String email = textemail.getText();
                LocalDate joindate = textjoinindate.getValue();
                String Cougarname = first.substring(0, 1) + last.substring(0, 7);
                String a = "";
                if (radiostudent.isSelected()) {
                    if (radioundergraduate.isSelected()) {
                        a = "Student, Undergraduate";
                    }
                    if (radiograduate.isSelected()) {
                        a = "Student, Graduate";
                    }
                }
                if (radioemployee.isSelected()) {
                    if (radiostaff.isSelected()) {
                        if (radiofulltime.isSelected()) {
                            a = "Employee, Full-Time";
                        }
                        if (radiopartime.isSelected()) {
                            a = "Employee, Part-Time";
                        }
                    }
                    if (radiofaculty.isSelected()) {
                        if (radiotenured.isSelected()) {
                            a = "Faculty, Tenured";
                        }
                        if (radionontenured.isSelected()) {
                            a = "Faculty, Non-Tenured";
                        }
                    }
                }
                stmt = conn.prepareStatement("INSERT INTO people ( First_Name, Last_Name, Cougarname, Date_of_joining, email, Type) VALUES (?,?,?,?,?,?)");
                stmt.setString(1, first);
                stmt.setString(2, last);
                stmt.setString(3, Cougarname);
                stmt.setDate(4, Date.valueOf(joindate));
                stmt.setString(5, email);
                stmt.setString(6, a);
                stmt.executeUpdate();
                System.out.println("record inserted into table");
            }catch (SQLException e) {
                e.printStackTrace();
            }
           // id.clear();
            //name.clear();
           // p.getSelectedToggle().setSelected(false); //to deselect the radio button
           // sel.valueProperty().setValue(null); //to clear the combobox

        });

    }


}
