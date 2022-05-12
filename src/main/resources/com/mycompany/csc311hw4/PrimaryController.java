/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.csc311hw4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nicka
 */
public class PrimaryController implements Initializable {

    @FXML
    private ListView<?> listView;
    @FXML
    private TextField fullName;
    @FXML
    private TextField BirthDate;
    @FXML
    private Label totalLabel;
    @FXML
    private Button checkOutButton;
    @FXML
    private ListView<?> cart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doCheckout(ActionEvent event) {
    }
    
}
