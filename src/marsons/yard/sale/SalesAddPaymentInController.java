/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.sale;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class SalesAddPaymentInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox<String> party;

    @FXML
    private ChoiceBox<String> paymentType;

    @FXML
    private TextField desc;

    @FXML
    private TextField receiptNum;

    @FXML
    private DatePicker date;

    @FXML
    private TextField received;

    @FXML
    private TextField disc;

    @FXML
    private Text total;

    @FXML
    private Button save;

    Connection con;
    @FXML
    void handleAction(ActionEvent event) {
        try {
            if (event.getSource() == save) {
                con = MyConnection.getConnection();
                String query = "INSERT INTO `paymentin`(`party`, `paymentType`, `description`, `receiptNum`, `date`, `received`, `discount`, `total`)"
                        + "VALUES ('" + party.getValue() + "','" + paymentType.getValue() + "','" + desc.getText() + "',"
                        + "'" + receiptNum.getText() + "','" + date.getValue() + "','" + received.getText() + "'"
                        + ",'" + disc.getText() + "','" + total.getText() +  "')";
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
