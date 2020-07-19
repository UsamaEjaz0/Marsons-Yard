/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.miscItem;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddFourthMiscItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField itemName;

    @FXML
    private TextField salePrice;

    @FXML
    private TextField minStock;

    @FXML
    private TextField qty;

    @FXML
    private TextField code;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<String> unit;

    @FXML
    private TextField pPrice;

    @FXML
    private TextField atPrice;

    @FXML
    private Button saveAndNew;

    @FXML
    private Button save;

    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        con = MyConnection.getConnection();
        if(event.getSource()== save){
            String query = "INSERT INTO `miscitems`(`name`, `code`, `salePrice`, `openingQty`, `minStock`, `unit`, `purchasePrice`, `atPrice`, `date`) "
                    + "VALUES ('"+itemName.getText()+"','"+code.getText()+"','"+salePrice.getText()+"','"+qty.getText()+"','"+minStock.getText()+"',"
                    + "'"+unit.getValue()+"','"+pPrice.getText() + "','"+atPrice.getText()+"','"+date.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
        if (event.getSource()== saveAndNew){
            String query = "INSERT INTO `miscitems`(`name`, `code`, `salePrice`, `openingQty`, `minStock`, `unit`, `purchasePrice`, `atPrice`, `date`) "
                    + "VALUES ('"+itemName.getText()+"','"+code.getText()+"','"+salePrice.getText()+"','"+qty.getText()+"','"+minStock.getText()+"',"
                    + "'"+unit.getValue()+"','"+pPrice.getText() + "','"+atPrice.getText()+"','"+date.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            itemName.setText("");
            code.setText("");
            salePrice.setText("");
            qty.setText("");
            unit.setValue("");
            pPrice.setText("");
            minStock.setText("");
            atPrice.setText("");
            date.setValue(LocalDate.now());
        }
    }
    
    Connection con;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }     
    
}
