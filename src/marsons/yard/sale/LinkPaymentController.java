/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.sale;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class LinkPaymentController implements Initializable {

    private ObservableList<ObservableList> data;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView invTable;
    @FXML
    private RadioButton invButton;

    @FXML
    private RadioButton debtorsButton;

    @FXML
    private RadioButton accButton;

    @FXML
    private RadioButton contraButton;
    @FXML
    public Button link;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        invTable.setVisible(false);
        invTable();

        invButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (invButton.isSelected()) {
                    System.out.println("Selected");
                    debtorsButton.setSelected(false);
                    accButton.setSelected(false);
                    contraButton.setSelected(false);

                    invTable.setVisible(true);

                } else {
                    System.out.println("Un-Selected");
                    invTable.setVisible(false);
                }

            }
        });
        contraButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (contraButton.isSelected()) {
                    System.out.println("Selected");
                    debtorsButton.setSelected(false);
                    accButton.setSelected(false);
                    invButton.setSelected(false);
                    invTable.setVisible(false);

                } else {
                    System.out.println("Un-Selected");
                }

            }
        });
        accButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (accButton.isSelected()) {
                    System.out.println("Selected");
                    debtorsButton.setSelected(false);
                    invButton.setSelected(false);
                    contraButton.setSelected(false);
                    invTable.setVisible(false);

                } else {
                    System.out.println("Un-Selected");
                }

            }
        });
        debtorsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (debtorsButton.isSelected()) {
                    System.out.println("Selected");
                    invButton.setSelected(false);
                    accButton.setSelected(false);
                    contraButton.setSelected(false);
                    invTable.setVisible(false);

                } else {
                    System.out.println("Un-Selected");
                }

            }
        });
    }

    @FXML
    void sendInvoiceNumber(ActionEvent event) {
        

        if (invButton.isSelected()) {
            try{
            SalesAddPaymentInController a = new SalesAddPaymentInController();
            ObservableList x = (ObservableList) (invTable.getSelectionModel().getSelectedItems().get(0));
            
            
            a.setData(String.valueOf(x.get(0)), String.valueOf(x.get(5)), String.valueOf(x.get(3)));
            }
            catch(Exception e){
                System.out.println(e);
            }
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
    }

}
