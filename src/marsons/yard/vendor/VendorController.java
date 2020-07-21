/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.vendor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class VendorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView vendorTable;

    @FXML
    private TableView vendorTransactionsTable;
     @FXML
    private Button addVendor;

    @FXML
    void handleAction(ActionEvent event) throws IOException {
        if(event.getSource()== addVendor){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddVendor.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
           
            stage.show(); 
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
