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
    private Button vendors;

    @FXML
    private Button customers;
    
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
    private Button sProviders;
    
    @FXML
    private BorderPane  pane;
    
    @FXML
    private Button addSale;
    
    @FXML
    private Button miscParty;
    
    @FXML
    private Button PInventory;
    
    @FXML
    private Button SStock;

    @FXML
    private Button tools;
     @FXML
    private Button miscItem;

    @FXML
    private Button paymentOut;
    
    @FXML
    private Button direct;

    @FXML
    private Button indirect;

    @FXML
    private Button overheads;
    
    @FXML
    void handleAction(ActionEvent event) throws IOException {
        if(event.getSource() == direct){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("directExpense/DirectVariable");
            pane.setCenter(view);
        }
        if(event.getSource() == indirect){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("indirectExpense/Indirect");
            pane.setCenter(view);
        }
        if(event.getSource() == overheads){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("overheadExpense/Overheads");
            pane.setCenter(view);
        }
         if(event.getSource() == miscItem){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("miscItem/AddMiscItem");
            pane.setCenter(view);
        }
        if(event.getSource() == paymentOut){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("purchase/PaymentOut");
            pane.setCenter(view);
        }
        if(event.getSource() == miscParty){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("miscParty/Misc");
            pane.setCenter(view);
        }
        if(event.getSource() == PInventory){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("inventory/PInventory");
            pane.setCenter(view);
        }
        if(event.getSource() == SStock){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("stock/SStock");
            pane.setCenter(view);
        }
        if(event.getSource() == tools){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("tools/Tools");
            pane.setCenter(view);
        }
        if(event.getSource() == sales){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("sale/Sales");
            pane.setCenter(view);
        }
        else if (event.getSource() == addSale){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sale/AddSale.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.setMaximized(true);
            stage.show();
        }
        else if (event.getSource() == addPurchase){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("purchase/AddPurchase.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.setMaximized(true);
            stage.show();
        }
        else if (event.getSource() == purchases){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("purchase/Purchases");
            pane.setCenter(view);
        }
        else if (event.getSource() == quotation){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("sale/quotations");
            pane.setCenter(view);
        } 
        else if(event.getSource()== salesPaymentInt){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("sale/SalesPaymentIn");
            pane.setCenter(view);
        }
        else if (event.getSource()== customers){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("customer/Customers");
            pane.setCenter(view);
        }
        else if (event.getSource()== vendors){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("vendor/Vendor");
            pane.setCenter(view);
        }
        else if (event.getSource()== sProviders){
            fxmlLoader object = new fxmlLoader();
            Pane view = object.getPage("serviceProvider/ServiceProviders");
            pane.setCenter(view);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
