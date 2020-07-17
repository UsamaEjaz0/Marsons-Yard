/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class DirectVariableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button addCat;
    @FXML
    private Button addExpense;

    @FXML
    void handleAction(ActionEvent event) throws IOException {
if (event.getSource() == addExpense){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddDirectVariable.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.setMaximized(true);
            stage.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
