package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Button mainmenubutton;
    public Label invalidlable;
    private ObservableList<People> studentList;
    List emails = new ArrayList();

    public void initialize() {
        final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
        studentList = FXCollections.observableArrayList();
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);





        go.setOnAction(ActionEvent ->{
            try {
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement stmt = null;
                String first = textfirstname.getText();
                String last = textlastname.getText();
                String email = textemail.getText();




                LocalDate joindate = textjoinindate.getValue();
                LocalDate now = LocalDate.now();
                String Cougarname = "";
                if(last.length() <= 7){ Cougarname = first.substring(0,1)+last;}
                else{Cougarname = first.substring(0,1)+last.substring(0,7);}
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
                stmt = conn.prepareStatement("INSERT INTO people ( First_Name, Last_Name, Cougarname, Date_of_joining, email, Type, Status) VALUES (?,?,?,?,?,?,?)");
                stmt.setString(1, first);
                stmt.setString(2, last);
                stmt.setString(3, Cougarname);
                if (joindate.compareTo(now) > 0){
                    invalidlable.setVisible(true);
                    return;
                }
                else{
                    stmt.setDate(4, Date.valueOf(joindate));
                    invalidlable.setVisible(false);
                }
                Matcher matcher = pattern.matcher(email);
                if (!matcher.matches()){
                    invalidlable.setVisible(true);
                    return;
                }
                else{
                    stmt.setString(5, email);
                }
                stmt.setString(6, a);
                stmt.setString(7, "Active");
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
    public void mainmenu() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Stage stage = (Stage) mainmenubutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }
    public void studentSelected(){
        if (radiostudent.isSelected()){
            radiostaff.setDisable(true);
            radiofaculty.setDisable(true);
            radionontenured.setDisable(true);
            radiotenured.setDisable(true);
            radiopartime.setDisable(true);
            radiofulltime.setDisable(true);
            radiograduate.setDisable(false);
            radioundergraduate.setDisable(false);
            radiostaff.setSelected(false);
            radiofaculty.setSelected(false);
            radionontenured.setSelected(false);
            radiotenured.setSelected(false);
            radiopartime.setSelected(false);
            radiofulltime.setSelected(false);
        }
    }
    public void employeeSelected() {
        if (radioemployee.isSelected()) {
            radiostaff.setDisable(false);
            radiofaculty.setDisable(false);
            radionontenured.setDisable(false);
            radiotenured.setDisable(false);
            radiopartime.setDisable(false);
            radiofulltime.setDisable(false);
            radiograduate.setDisable(true);
            radioundergraduate.setDisable(true);
            radiograduate.setSelected(false);
            radioundergraduate.setSelected(false);
        }
    }
        public void staffSelected(){
            if (radiostaff.isSelected()){
                radiotenured.setDisable(true);
                radionontenured.setDisable(true);
                radiotenured.setSelected(false);
                radionontenured.setSelected(false);
                radiofulltime.setDisable(false);
                radiopartime.setDisable(false);
            }
        }
        public void facultySelected(){
            if (radiofaculty.isSelected()){
                radiofulltime.setDisable(true);
                radiopartime.setDisable(true);
                radiofulltime.setSelected(false);
                radiopartime.setSelected(false);
                radiotenured.setDisable(false);
                radionontenured.setDisable(false);
            }
        }
    }
