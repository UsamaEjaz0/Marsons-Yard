/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.directExpense;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddDirectVariableController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private AnchorPane screen;

    @FXML
    private ChoiceBox<String> expType;

    @FXML
    private ChoiceBox<String> expenseCat;

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
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private Button addCat;

    @FXML
    private ChoiceBox<String> paymentType;

    @FXML
    private DatePicker date;
    
    @FXML
    private Button saveAndNew;

    @FXML
    void ComputeAmount(MouseEvent event) {
                amount.setText(String.valueOf(Long.parseLong(quantity.getText()) * Double.parseDouble(priceOfUnit.getText())));

    }

    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        con = MyConnection.getConnection();
        if(event.getSource()== save){
            String query = "INSERT INTO `directexpense`(`type`, `category`, `itemName`, `description`, `date`, `quantity`, `pricePerUnit`, `amount`, `paymentType`) "
                    + "VALUES ('"+expType.getValue()+"','"+expenseCat.getValue()+"','"+itemName.getValue()+"','"+desc.getText()+"','"+date.getValue()+"',"
                    + "'"+quantity.getText()+"','"+priceOfUnit.getText() + "','"+amount.getText()+"','"+paymentType.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
        if (event.getSource()== saveAndNew){
            String query = "INSERT INTO `directexpense`(`type`, `category`, `itemName`, `description`, `date`, `quantity`, `pricePerUnit`, `amount`, `paymentType`) "
                    + "VALUES ('"+expType.getValue()+"','"+expenseCat.getValue()+"','"+itemName.getValue()+"','"+desc.getText()+"','"+date.getValue()+"',"
                    + "'"+quantity.getText()+"','"+priceOfUnit.getText() + "','"+amount.getText()+"','"+paymentType.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            
            desc.setText("");
            
            quantity.setText("");
            priceOfUnit.setText("");
            amount.setText("");
            date.setValue(LocalDate.now());
        }
    }
    
    Connection con;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }       
    
}
