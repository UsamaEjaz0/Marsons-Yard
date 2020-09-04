/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.sale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class BankTransferController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField refNum;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker TransactionDate;

    @FXML
    private ComboBox<String> accounts;

    @FXML
    private Button save;

    @FXML
    void handleAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
