/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.addItem;

import connection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddItemContainerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<ObservableList> data;
    String componentOf;

    @FXML
    private TableView itemTable;
    @FXML
    private Button addItem;
    @FXML
    private TableView itemTransactionTable;

    public void miscTable() {
        Connection c;
        data = FXCollections.observableArrayList();
        String[] itemColNames = {"Item","Primary Item", "Quantity"};
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT name,componentOf, openingQty from items";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(itemColNames[i]);
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                itemTable.getColumns().addAll(col);
                System.out.print(col);

            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
            itemTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    void handleAction(ActionEvent event) throws IOException {
        if (event.getSource() == addItem) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItemScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
        }
    }

    public void buildItemTransactionsTable(String name) {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT * from items where Name = '" + name + "'";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                itemTransactionTable.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
            itemTransactionTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        miscTable();

        itemTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                ObservableList x = (ObservableList) (itemTable.getSelectionModel().getSelectedItems().get(0));
                itemTransactionTable.getColumns().clear();
                buildItemTransactionsTable(x.get(0) + "");

            }
            if (event.getClickCount() == 2) {
                ObservableList x = (ObservableList) (itemTable.getSelectionModel().getSelectedItems().get(0));
                System.out.print(x.get(0) + " " + x.get(1));

                try {
                    EditItemController e = new EditItemController();
                        e.setEditData((String) x.get(0), (String)x.get(1));
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditItem.fxml"));

                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                      

                    stage.show();

                    
                } catch (IOException ex) {
                    Logger.getLogger(AddItemContainerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

}
