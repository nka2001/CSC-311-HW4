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
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * GUI class, contains the methods to load data from a database, and load data
 * from a JSON file into a database, and to display the data from the database
 *
 * @author nicka
 */
public class PrimaryController {

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField fullName;
    @FXML
    private TextField BirthDate;
    @FXML
    private Label totalLabel;
    @FXML
    private Button checkOutButton;
    @FXML
    private ListView<String> cart;

    private ObservableList<String> i;

    private ObservableList<String> cartList;
    @FXML
    private DatePicker d1;
    @FXML
    private DatePicker d2;

    private String startDate;
    private String endDate;

    private ArrayList<Integer> hold = new ArrayList<Integer>();

    /**
     * this method will do the checkout and process the total price, this is
     * considered a long operation so a thread will be used to keep the GUI from
     * hanging, as well as write to the database the information the user
     * entered
     *
     * @param event
     */
    @FXML
    private void doCheckout(ActionEvent event) {

        System.out.println(startDate + " === " + endDate);
    }

    public void initialize() throws SQLException {
        i = listView.getItems();
        i.clear();

        Thread t = new Thread(() -> loadDatabase(i));
        t.start();

    }

    /**
     * this method loads the list view so users can shop from them, this is
     * considered a long operation and a thread is used to cause the GUI not to
     * "hang"
     *
     * @param i
     */
    public void loadDatabase(ObservableList<String> i) throws SQLException {

        String dbUrl = "";
        Connection c = null;

        try {
            dbUrl = "jdbc:ucanaccess://.//HW4DB.accdb";
            c = DriverManager.getConnection(dbUrl);

            System.out.println("connected");

            String tableName = "Products";
            Statement stmt = c.createStatement();
            ResultSet r = stmt.executeQuery("select * from " + tableName);

            while (r.next()) {
                int id = r.getInt("ID");
                String product = r.getString("Product Name");
                String RentalPrice = r.getString("Rental Price");
                String IsAvailable = r.getString("Is Available");

                i.add(id + ", " + product + ", " + RentalPrice + ", " + IsAvailable);
            }

        } catch (SQLException e) {

        } finally {
            c.close();
        }

    }

    @FXML
    private void handleOnMouseClicked(MouseEvent event) {

        ObservableList<String> ov = listView.getItems();
        cartList = cart.getItems();

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < ov.size() && !(cartList.contains(ov.get(selectedIndex)))) {
            cartList.add(ov.get(selectedIndex));
            hold.add(selectedIndex);

        }

    }

    @FXML
    private void retrunDate(ActionEvent event) {

        startDate = d1.getValue().toString();

    }

    /**
     * this method gets the end date from the date picker, it checks to make
     * sure the date selected is not less than the start date
     *
     * @param event
     */
    @FXML
    private void retrurnDate2(ActionEvent event) {

        if (d2.getValue().compareTo(d1.getValue()) < 1) {
            makeDateAlert();
            
        } else {
            endDate = d2.getValue().toString();
        }

    }

    private void makeDateAlert() {

        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("Error Incorrect Date Selected");
        a.setHeaderText("You selected a date that is before the start date");
        a.showAndWait();
        
        

    }
}
