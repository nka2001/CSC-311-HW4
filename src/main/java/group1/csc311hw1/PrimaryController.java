package group1.csc311hw1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

/**
 * GUI class, contains the methods to load data from a database, and load data from a JSON file into a database, and to display the data from the database 
 * @author nicka
 */
public class PrimaryController {

    @FXML
    private ListView<String> listView;//listview to display database
    @FXML
    private TextField textField;//textfield for showing record count
    @FXML
    private Button loadFrom;//button to load from the database
    @FXML
    private MenuItem loadFromJSON;//menu item to load from JSON file
    @FXML
    private MenuItem menuClose;//menu item to close program
    
    private ObservableList<String> i; //obervable list for listview

   
    @FXML
     /**
     * loads data from the database when the button is clicked, retrieveData is called to retrieve the data from the database 
     * @param event
     * @throws SQLException 
     */
    private void loadFromDB(ActionEvent event) throws SQLException {//this will load the listview with data from the access database 
        i = listView.getItems();
        i.clear();//this will clear the listview 

        textField.setText("0");//sets the record count to 0

        textField.setText(Integer.toString(i.size()));//this sets the textfield to whatever the record count is 

        retrieveData(i);//calls the retrieve data method to populate the listview 

        textField.setText(Integer.toString(i.size()));//record count is updated

         

    }
    /**
     * retrieves data from the database and displays the contents in the listview
     * @param i (observableList of type string)
     * @throws SQLException 
     */

    public void retrieveData(ObservableList<String> i) throws SQLException {

        String dburl = "";
        Connection c = null;

        try {
            dburl = "jdbc:ucanaccess://.//Game.accdb";
            c = DriverManager.getConnection(dburl);

            System.out.println("connected");

            String tableName = "VideoGames";
            Statement stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("select * from " + tableName);

            while (r.next()) {
                int id = r.getInt("ID");
                String title = r.getString("Title");
                String rate = r.getString("Esrb");
                double price = r.getDouble("Price");

                i.add(title + ", " + price + ", " + rate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.close();
        }
    }

    
    /**
     * LoadFromJSON will load data into the database from a JSON file 
     * @param event
     * @throws SQLException
     * @throws FileNotFoundException 
     */
    @FXML
    private void loadFromJSON(ActionEvent event) throws SQLException, FileNotFoundException {
        
      
        deleteList();
        
        

        textField.setText("0");

        String dburl = "";
        Connection c = null;

        try {
            dburl = "jdbc:ucanaccess://.//Game.accdb";
            c = DriverManager.getConnection(dburl);

            System.out.println("connected");

            GsonBuilder b = new GsonBuilder();
            b.setPrettyPrinting();
            Gson gson = b.create();

            FileReader fr = new FileReader("games.json");
            VideoGame[] vg = gson.fromJson(fr, VideoGame[].class); // reads from the JSON file and put the output into an array of videogame objects

            for (int k = 0; k < vg.length; k++) {

                String sql = "INSERT INTO VideoGames (Title,Price,Esrb) VALUES (?,?,?)";

                PreparedStatement ps = c.prepareStatement(sql);

                ps.setString(1, vg[k].getTitle());
                ps.setDouble(2, vg[k].getPrice());
                ps.setString(3, vg[k].getRating());

                int row = ps.executeUpdate();

                if (row > 0) {
                    System.out.println("added");
                }

            }
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.close();
        }
    }
    /**
     * closes the program upon clicking the menu item close 
     * @param event 
     */
    @FXML
    private void CloseHW(ActionEvent event
    ) {//close method, upon clicking from the menu drop down "Close" it will cause the program to stop runnning

        Platform.exit();//closes the window 
        System.exit(0);

    }
/**
 * deletes the entire database, this is needed so no duplicates are added to the database, the listview is not updtaed when this happens
 * @throws SQLException 
 */
    public void deleteList() throws SQLException {
        
        
        
        String dburl = "";
        Connection c = null;

        try {
            dburl = "jdbc:ucanaccess://.//Game.accdb";
            c = DriverManager.getConnection(dburl);

            System.out.println("connected");

            Statement st = c.createStatement();

            st.executeUpdate("DELETE FROM VideoGames");
            System.out.println("deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.close();
        }

    }
}
