/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.customer;

import connection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class CustomersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
 
    @FXML
    private TableView customerTable;
    @FXML
    private TableView customerTransactionTable;
 
    public void customerTable() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT Name,Balance from customers";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
 
                customerTable.getColumns().addAll(col);
                System.out.print(col);
                
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                
                data.add(row);
 
            }
            customerTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public void buildSalesTransactionsTable() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT * from customers";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
 
                customerTransactionTable.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
              
                data.add(row);
 
            }
            customerTransactionTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
     @FXML
    private Button addCustomer;
    
   

    
    @FXML
    void handleAction(ActionEvent event) throws IOException {
        if(event.getSource()== addCustomer){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add Customer.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
       
            stage.show(); 
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         buildSalesTransactionsTable();
         customerTable();
         
    }    
    
}
