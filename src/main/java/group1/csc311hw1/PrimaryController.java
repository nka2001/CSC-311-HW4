package group1.csc311hw1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField textField;
    @FXML
    private Button loadFrom;
    @FXML
    private MenuItem loadFromJSON;
    @FXML
    private MenuItem menuClose;

    @FXML
    private void loadFromDB(ActionEvent event) {

        ObservableList<String> i = listView.getItems();

        textField.setText("0");

        textField.setText(Integer.toString(i.size()));

        retrieveData(i);

        textField.setText(Integer.toString(i.size()));
        
        deleteList();

    }

    public void retrieveData(ObservableList<String> i) {

        String dbURL = "";
        Connection c = null;

        try {//connect to the database block
            dbURL = "jdbc:ucanaccess://.//Games.accdb";
            c = DriverManager.getConnection(dbURL);
            System.out.println("Connected");

            try {

                VideoGame v1 = new VideoGame();

                String sql = "INSERT INTO VideoGames (Title,Price,ESRB Rating) VALUES(?,?,?)";
                c = DriverManager.getConnection(dbURL);

                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, v1.getTitle());
                ps.setDouble(2, v1.getPrice());
                ps.setString(3, v1.getRating());

                int a = ps.executeUpdate();
                
                ps.close();
                c.close();

            } catch (SQLException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Database not found");
        }

        /*
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
              
                String title = r.getString("Title");
                double price = r.getDouble("Price");
                String esrbRating = r.getString("ESRB Rating");
                i.add(title+ ", " + price+ ", " +esrbRating);
            }
        }catch(SQLException e){
            System.out.println("error");
        }
        
         */
    }

    @FXML
    private void loadFromJSON(ActionEvent event) {

    }

    @FXML
    private void CloseHW(ActionEvent event) {//close method, upon clicking from the menu drop down "Close" it will cause the program to stop runnning

        Platform.exit();//closes the window 
        System.exit(0);

    }

    private void deleteList() {

        try {

            Connection c = null;
            String dbURL = "jdbc:ucanaccess://.//Games.accdb";
            c = DriverManager.getConnection(dbURL);
            
            Statement stmt = c.createStatement();

            String sql = "DELETE FROM VideoGames";
            stmt.execute(sql);
            stmt.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
