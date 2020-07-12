/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Line 95
package marsons.yard;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddSaleController implements Initializable {

    
    ObservableList saleTypeList = FXCollections.observableArrayList();
    ObservableList customerNameList = FXCollections.observableArrayList();
    ObservableList paymentTermsList = FXCollections.observableArrayList();
    ObservableList unitList = FXCollections.observableArrayList();
    ObservableList itemList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane screen;
    @FXML
    private ChoiceBox<String> saleType;

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
    
    Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        saleTypeList.addAll("Cash", "Credit");
        saleType.getItems().addAll(saleTypeList);
        
        paymentTermsList.addAll("Waddi", "Nikki");
        paymentTerms.getItems().addAll(paymentTermsList);
        
        //item list to be taken from db
        itemList.addAll("Copper", "Aluminum");
        itemName.getItems().addAll(itemList);
        
        //customer list to be taken from db
        customerNameList.addAll("Mr. Beast", "Dr. Potato", "Pewdiepie");
        customerList.getItems().addAll(customerNameList);
        
        unitList.addAll("Kg", "Tonne","Mund");
        unit.getItems().addAll(unitList);
        
        con = MyConnection.getConnection();
    }    
    
    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        if(event.getSource() == save){
            String query = "INSERT INTO `sales`(`InvoiceNum`, `saleType`, `customerName`, `billingName`, `InvoiceDate`, `DueDate`, `paymentTerms`, `itemName`, `Description`, `Quantity`, `pricePerUnit`, `Amount`, `Unit`) "
                    + "VALUES ('"+invNum.getText()+"','"+saleType.getValue()+"','"+customerList.getValue()+"',"
                    + "'"+billingName.getText()+"','"+invDate.getValue()+"','"+dueDate.getValue()+"'"
                    + ",'"+paymentTerms.getValue()+"','"+itemName.getValue()+"','"+desc.getText()+"','"+quantity.getText()+"',"
                    + "'"+priceOfUnit.getText()+"','"+amount.getText()+"','"+unit.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();

        }
    }
    @FXML
    void ComputeAmount(MouseEvent event) {
        amount.setText(""+(Long.parseLong(quantity.getText())* Double.parseDouble(priceOfUnit.getText())));
    }
}
