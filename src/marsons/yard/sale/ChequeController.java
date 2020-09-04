/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.sale;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class ChequeController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    static String receipt;

    @FXML
    private TextField cNum;

    @FXML
    private TextField bank;

    @FXML
    private TextField title;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker dueDate;

    @FXML
    private Button save;

    @FXML
    private Button saveAndNew;

    void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    Connection con;

    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        try{
        if (event.getSource() == save) {
            
            con = MyConnection.getConnection();
            String query = "INSERT INTO `cheque`(`ChequeNum`, `Receipt`, `Bank`, `Title`, `Amount`, `DueDate`) VALUES ('" + cNum.getText() + "',"
                    + "'" + receipt + "','" + bank.getText() + "','" + title.getText() + "','" + amount.getText() + "','" + dueDate.getValue() + "')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
            System.out.println(receipt);
        } else if (event.getSource() == saveAndNew) {
            con = MyConnection.getConnection();
            String query = "INSERT INTO `cheque`(`ChequeNum`, `Receipt`, `Bank`, `Title`, `Amount`, `DueDate`) VALUES ('" + cNum.getText() + "',"
                    + "'" + receipt + "','" + bank.getText() + "','" + title.getText() + "','" + amount.getText() + "','" + dueDate.getValue() + "')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            
            cNum.setText("");
            bank.setText("");
            title.setText("");
            amount.setText("");
            
        }}
        catch(Exception e){
            System.out.println(e);
        }
    }
}
