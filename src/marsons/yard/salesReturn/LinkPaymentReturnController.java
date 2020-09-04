/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.salesReturn;

import connection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import marsons.yard.sale.SalesAddPaymentInController;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class LinkPaymentReturnController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    private ObservableList<ObservableList> data;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView invTable;
    

   

    @FXML
    public Button link;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        invTable();

        
    }

    @FXML
    void sendInvoiceNumber(ActionEvent event) {
        

        
            try{
            AddReturnController a = new AddReturnController();
            ObservableList x = (ObservableList) (invTable.getSelectionModel().getSelectedItems().get(0));
            
            
            a.setData(String.valueOf(x.get(0)), String.valueOf(x.get(3)));
            }
            catch(Exception e){
                System.out.println(e);
            }
        
        
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();

    }

    public void invTable() {
        invTable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT `InvoiceNum`, `saleType`, `customerName`, `total` , `CashReceived`,  `InvoiceDate` from sales";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                invTable.getColumns().addAll(col);

            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
            invTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        
//        try {
//            c = MyConnection.getConnection();
//            String SQL = "SELECT `InvoiceNum`, `saleType`, `customerName`, `total` , `CashReceived`,  `InvoiceDate` from sales";
//            ResultSet rs = c.createStatement().executeQuery(SQL);
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                invTable.getColumns().addAll(col);
//
//            }
//            while (rs.next()) {
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    row.add(rs.getString(i));
//                }
//
//                data.add(row);
//
//            }
//            invTable.setItems(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
    }
 
    
}
