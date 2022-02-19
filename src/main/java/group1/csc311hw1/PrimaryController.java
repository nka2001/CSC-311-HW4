package group1.csc311hw1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField textField;
    @FXML
    private Button loadFrom;

    

    @FXML
    private void loadFromDB(ActionEvent event) {
        
        ObservableList<String> i = listView.getItems();
        i.clear();
        textField.setText("0");
         
        
        textField.setText(Integer.toString(i.size()));
        
        retrieveData(i);
        
        textField.setText(Integer.toString(i.size()));
        
        
        
        
    }
    public void retrieveData(ObservableList<String> i){
        String dbURL = "";
        Connection c = null;
        
        try{//connect to the database block
            dbURL = "jdbc:ucanaccess://.//Games.accdb";
            c = DriverManager.getConnection(dbURL);
            System.out.println("Connected");
        }catch(SQLException e){
            System.out.println("Database not found");
        }
        try{
            String tablename = "VideoGames";
            Statement st = c.createStatement();
            ResultSet r = st.executeQuery("Select * from " + tablename);
            
            
            while(r.next()){
                int id = r.getInt("ID");
                String title = r.getString("Title");
                double price = r.getDouble("Price");
                String esrbRating = r.getString("ESRB Rating");
                i.add(id+ " " + title+ " " + price+ " " +esrbRating);
            }
        }catch(SQLException e){
            System.out.println("error");
        }
        
        
    }
}
