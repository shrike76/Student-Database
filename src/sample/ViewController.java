package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.sql.*;

public class ViewController {

    public Button mainmenubutton;
    public TableView lv1;
    public ObservableList<ObservableList> data;
    public ObservableList<String> ID1 = FXCollections.observableArrayList();
    public ObservableList<String> firstname = FXCollections.observableArrayList();
    public ObservableList<String> lastname = FXCollections.observableArrayList();
    public ObservableList<String> cougarname = FXCollections.observableArrayList();
    public ObservableList<String> joiningdate = FXCollections.observableArrayList();
    public ObservableList<String> email1 = FXCollections.observableArrayList();
    public ObservableList<String> type1 = FXCollections.observableArrayList();
    public TableColumn id;
    public TableColumn fname;
    public TableColumn lname;
    public TableColumn cname;
    public TableColumn joindate;
    public TableColumn email;
    public TableColumn type;


    public void mainmenu() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Stage stage = (Stage) mainmenubutton.getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
    }

    /*public void initialize() {
        final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
        ResultSet resultSet = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from PEOPLE");
            resultSet = stmt.executeQuery();
            id.setText(String.valueOf(ID1));
            fname.setText(String.valueOf(firstname));
            while (resultSet.next()) {
                int id = resultSet.getInt("PEOPLESOFT_ID");
                String firstname=resultSet.getString("FIRST_NAME");
                String lastname=resultSet.getString("LAST_NAME");
                //data.add(id);
                ID1.add(resultSet.getString(1));
               // firstname.add(resultSet.getString(2));
                //data.add(lastname);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    public void initialize() { //view code borrowed from https://stackoverflow.com/questions/18941093/how-to-fill-up-a-tableview-with-database-data
        data = FXCollections.observableArrayList();
        final String DB_URL = "jdbc:derby://localhost:1527/CougarDB;";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement("SELECT * from PEOPLE");
            ResultSet rs = stmt.executeQuery();
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                lv1.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }
            lv1.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}


