/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.CashAndBank;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddBankAccountController implements Initializable {

    
    @FXML
    private TextField accTitle;

    @FXML
    private TextField accNumber;

    @FXML
    private TextField iban;

    @FXML
    private TextField bCode;

    @FXML
    private TextField cBal;

    @FXML
    private DatePicker asOf;

    @FXML
    private Button saveAndNew;

    @FXML
    private Button save;
    
    Connection con;
    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        con = MyConnection.getConnection();
        if (event.getSource()== save){
                
                String query ="INSERT INTO `accounts`(`Title`, `AccountNum`, `IBAN`, `BranchCode`, `Balance`, `AsOf`) VALUES ('" + accTitle.getText() + "','" + accNumber.getText() + "','"+ iban.getText()+
                        "','"+ bCode.getText()+"','"+cBal.getText()+"','"+ asOf.getValue()+"')";
                Statement st = con.createStatement();
                st.executeUpdate(query);
                con.close();
                
                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();
            
        }else if(event.getSource()== saveAndNew){
            String query ="INSERT INTO `accounts`(`Title`, `AccountNum`, `IBAN`, `BranchCode`, `Balance`, `AsOf`) VALUES ('" + accTitle.getText() + "','" + accNumber.getText() + "','"+ iban.getText()+
                        "','"+ bCode.getText()+"','"+cBal.getText()+"','"+ asOf.getValue()+"')";
                Statement st = con.createStatement();
                st.executeUpdate(query);
                con.close();
                bCode.setText("");
                cBal.setText("");
                accTitle.setText("");
                accNumber.setText("");
                iban.setText("");
                asOf.setValue(LocalDate.now());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        asOf.setValue(LocalDate.now());
        
        
    }    
    
}
