package sample;

import javafx.scene.control.*;

public class Controller {
    public TextField textfirstname;
    public TextField textjoinindate;
    public TextField textemail;
    public TextField textlastname;
    public RadioButton radiostudent;
    public ToggleGroup group1;
    public ToggleGroup group2;
    public RadioButton radioemployee;
    public ComboBox combostudent;
    public RadioButton radiostaff;
    public RadioButton radiofaculty;
    public ComboBox combostaff;
    public ComboBox combofaculty;
    public Button go;

    public void initialize(){
        combostudent.getItems().addAll("Graduate","Undergraduate");
        combostaff.getItems().addAll("Part-Time","Full-Time");
        combofaculty.getItems().addAll("Tenured","Non-Tenured");
    }



}
