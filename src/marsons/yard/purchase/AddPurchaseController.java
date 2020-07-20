/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.purchase;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        amount.setText(String.valueOf(Long.parseLong(quantity.getText()) * Double.parseDouble(priceOfUnit.getText())));

    }
    Connection con;
@FXML
    void handleAction(ActionEvent event) throws SQLException {
        try {
            if (event.getSource() == save) {
                con = MyConnection.getConnection();
                String query = "INSERT INTO `purchase`(`purchaseType`, `vendorName`, `billingName`, `itemName`, `description`, `billNum`, `billDate`, `paymentTerm`, `dueDate`, `qty`, `unit`, `pricePerUnit`, `amount`) "
                        + "VALUES ('" + purchaseType.getValue() + "','" + customerList.getValue() + "','" + billingName.getText() + "',"
                        + "'" + itemName.getValue() + "','" + desc.getText() + "','" + invNum.getText() + "'"
                        + ",'" + invDate.getValue() + "','" + paymentTerms.getValue() + "','" + dueDate.getValue() + "','" + quantity.getText() + "',"
                        + "'" + priceOfUnit.getText() + "','" + unit.getValue() + "','" + amount.getText() + "')";
                Statement st = con.createStatement();
                st.executeUpdate(query);
                con.close();

            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
