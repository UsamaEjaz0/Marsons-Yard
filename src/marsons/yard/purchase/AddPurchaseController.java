/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.purchase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddPurchaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane screen;

    @FXML
    private ChoiceBox<String> purchaseType;

    @FXML
    private ChoiceBox<String> customerList;

    @FXML
    private TextField billingName;

    @FXML
    private TextField invNum;

    @FXML
    private DatePicker invDate;

    @FXML
    private DatePicker dueDate;

    @FXML
    private ChoiceBox<String> paymentTerms;

    @FXML
    private ChoiceBox<String> itemName;

    @FXML
    private Button addItem;

    @FXML
    private TextField desc;

    @FXML
    private TextField quantity;

    @FXML
    private TextField priceOfUnit;

    @FXML
    private Text amount;

    @FXML
    private ChoiceBox<String> unit;

    @FXML
    private Button addUnit;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    void ComputeAmount(MouseEvent event) {

    }

    @FXML
    void handleAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
