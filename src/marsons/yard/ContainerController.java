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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class ContainerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button salesPaymentInt;
    @FXML
    private Button purchases;
    
    @FXML
    private Button addPurchase;
    @FXML
    private Button quotation;
    @FXML
    private Button sales;
    
    @FXML
    private BorderPane  pane;
    
    @FXML
    private Button addSale;
    @FXML
    void handleAction(ActionEvent event) throws IOException {
        if(event.getSource() == sales){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("Sales");
            pane.setCenter(view);
        }
        else if (event.getSource() == addSale){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSale.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.setMaximized(true);
            stage.show();
        }
        else if (event.getSource() == addPurchase){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPurchase.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.setMaximized(true);
            stage.show();
        }
        else if (event.getSource() == purchases){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("Purchases");
            pane.setCenter(view);
        }
        else if (event.getSource() == quotation){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("quotations");
            pane.setCenter(view);
        } 
        else if(event.getSource()== salesPaymentInt){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("SalesPaymentIn");
            pane.setCenter(view);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
