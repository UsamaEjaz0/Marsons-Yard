/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.miscParty;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddMiscController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList pGroupList = FXCollections.observableArrayList();
    @FXML
    private TextField Cname;

    @FXML
    private TextField pNum;

    @FXML
    private TextField sAddr;

    @FXML
    private TextField addr;

    @FXML
    private TextField NTN;

    @FXML
    private TextField cBalance;

    @FXML
    private DatePicker asOf;

    @FXML
    private ChoiceBox<String> pGroup;

    @FXML
    private TextField email;

    @FXML
    private Button saveAndNew;

    @FXML
    private Button save;

    Connection con;
    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        con = MyConnection.getConnection();
if(event.getSource()== save){
            String query = "INSERT INTO `miscparty`(`Name`, `NTN`, `Phone`, `Address`, `ShippingAddr`, `Balance`, `pGroup`, `Email`, `As of`) "
                    + "VALUES ('"+Cname.getText()+"','"+NTN.getText()+"','"+pNum.getText()+"','"+addr.getText()+"','"+sAddr.getText()+"',"
                    + "'"+cBalance.getText()+"','"+pGroup.getValue() + "','"+email.getText()+"','"+asOf.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
        if (event.getSource()== saveAndNew){
            String query = "INSERT INTO `miscparty`(`Name`, `NTN`, `Phone`, `Address`, `ShippingAddr`, `Balance`, `pGroup`, `Email`, `As of`) "
                    + "VALUES ('"+Cname.getText()+"','"+NTN.getText()+"','"+pNum.getText()+"','"+addr.getText()+"','"+sAddr.getText()+"',"
                    + "'"+cBalance.getText()+"','"+pGroup.getValue() + "','"+email.getText()+"','"+asOf.getValue()+"')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Cname.setText("");
            NTN.setText("");
            pNum.setText("");
            addr.setText("");
            sAddr.setText("");
            cBalance.setText("");
            
            email.setText("");
            asOf.setValue(LocalDate.now());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pGroupList.add("General");
        pGroup.getItems().addAll(pGroupList);
        
        
    }    
    
}
