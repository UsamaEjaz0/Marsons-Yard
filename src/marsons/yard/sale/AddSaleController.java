/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Line 95
package marsons.yard.sale;

import connection.MyConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddSaleController implements Initializable {

    ObservableList saleTypeList = FXCollections.observableArrayList();
    ObservableList customerNameList;
    ObservableList paymentTermsList = FXCollections.observableArrayList();
    ObservableList unitList = FXCollections.observableArrayList();
    ObservableList itemList = FXCollections.observableArrayList();
    ObservableList invPrefixList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */

    @FXML
    private TableColumn<Items, String> srCol;
    @FXML
    private TableColumn<Items, String> iNameCol;

    @FXML
    private TableColumn<Items, String> descCol;

    @FXML
    private TableColumn<Items, String> qtyCol;

    @FXML
    private TableColumn<Items, String> unitCol;

    @FXML
    private TableColumn<Items, String> ppuCol;

    @FXML
    private TableColumn<Items, String> amountCol;
    @FXML
    private Label customerLabel;
    @FXML
    private ChoiceBox<String> invPrefix;
    @FXML
    private AnchorPane screen;
    @FXML
    private ChoiceBox<String> saleType;

    @FXML
    private ComboBox customerList;

    @FXML
    private TextField billingName;

    @FXML
    private TextField invNum;

    @FXML
    private DatePicker invDate;

    @FXML
    private DatePicker dueDate;

    @FXML
    private ChoiceBox<String> paymentTerms;

    @FXML
    private ComboBox<String> itemName;

    @FXML
    private Button addItem;

    @FXML
    private TextField desc;

    @FXML
    private TextField quantity;

    @FXML
    private TextField priceOfUnit;

    @FXML
    private Text amount;

    @FXML
    private ChoiceBox<String> unit;

    @FXML
    private Button addUnit;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    @FXML
    private TableView<Items> itemTable;
    @FXML
    private Button addRow;

    Connection con;

    @FXML
    private Button addParty = new Button("Add Party");

    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();

        addParty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        paymentTermsList.addAll("Custom", "Due on Receipt", "Net 15", "Net 30", "Net 45", "Net 60");
        saleTypeList.addAll("Cash", "Credit");

        saleType.getItems().addAll(saleTypeList);
        saleType.setValue("Cash");
        paymentTerms.getItems().addAll(paymentTermsList);
        paymentTerms.setValue("Due on Receipt");
        customerList.buttonCellProperty();

        itemName.getItems().addAll(itemList);

        invDate.setValue(LocalDate.now());
        dueDate.setValue(LocalDate.now());
        invPrefixList.addAll("SR-INV");
        invPrefix.getItems().addAll(invPrefixList);
        invPrefix.setValue("SR-INV");

        try {
            File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
            Scanner myReader = new Scanner(myObj);
            count = myReader.nextInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        unit.getItems().addAll(unitList);
        String date = invDate.getValue().toString();
        String year = date.substring(2, 4);
        String month = date.substring(5, 7);
        String defaultInvoiceNum1 = year + "-" + month + "-" + String.valueOf(count);
        invNum.setText(defaultInvoiceNum1);

        invDate.valueProperty().addListener((ov, oldValue, newValue) -> {
            String date1 = newValue.toString();
            System.out.println(newValue);
            String year1 = date1.substring(2, 4);
            String month1 = date1.substring(5, 7);
            System.out.print(year1 + month1);
            String defaultInvoiceNum = year + "-" + month + "-" + String.valueOf(count);
            invNum.setText(defaultInvoiceNum);
        });
        paymentTerms.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if ((paymentTerms.getItems().get((Integer) number2)) == "Due on Receipt") {
                    dueDate.setValue(invDate.getValue());
                } else if ((paymentTerms.getItems().get((Integer) number2)) == "Net 15") {
                    dueDate.setValue(invDate.getValue().plusDays(15));
                } else if ((paymentTerms.getItems().get((Integer) number2)) == "Net 30") {
                    dueDate.setValue(invDate.getValue().plusDays(30));
                } else if ((paymentTerms.getItems().get((Integer) number2)) == "Net 45") {
                    dueDate.setValue(invDate.getValue().plusDays(45));
                } else if ((paymentTerms.getItems().get((Integer) number2)) == "Net 60") {
                    dueDate.setValue(invDate.getValue().plusDays(60));
                } else if ((paymentTerms.getItems().get((Integer) number2)) == "Custom") {
                    dueDate.setValue(invDate.getValue());
                }
            }
        });
        saleType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if ((saleType.getItems().get((Integer) number2)) == "Cash") {
                    customerLabel.setText("Customer");
                } else if ((saleType.getItems().get((Integer) number2)) == "Credit") {
                    customerLabel.setText("Customer*");

                }
            }
        });
        customerList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(customerList.getValue());
            }
        });

    }

    public void init() {

        Connection c;
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT Name from customers";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            customerList.getItems().add(addParty);
            while (rs.next()) {
                customerNameList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    customerNameList.add(rs.getString(i));
                }

                customerList.getItems().addAll(customerNameList);
            }

            String SQL2 = "select name from miscitems UNION select itemName from purchaseitems union select itemName from salestock union select name from tools";
            ResultSet rs2 = c.createStatement().executeQuery(SQL2);

            while (rs2.next()) {
                itemList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                    itemList.add(rs2.getString(i));
                }

                itemName.getItems().addAll(itemList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        try {
            if (event.getSource()== addItem){
                //To be added
            }
            if (event.getSource() == save) {
                Button b = new Button("Added");
                con = MyConnection.getConnection();
                String query = "INSERT INTO `sales`(`InvoiceNum`, `saleType`, `customerName`, `billingName`, `InvoiceDate`, `DueDate`, `paymentTerms`) "
                        + "VALUES ('" + invPrefix.getValue() + invNum.getText() + "','" + saleType.getValue() + "','" + customerList.getValue() + "',"
                        + "'" + billingName.getText() + "','" + invDate.getValue() + "','" + dueDate.getValue() + "'"
                        + ",'" + paymentTerms.getValue() + "')";
                Statement st = con.createStatement();
                st.executeUpdate(query);

                //fdfdfdf
                for (int j = 0; j < itemTable.getItems().size(); j++) {

                    String iNum = invNum.getText();
                    String iName = itemTable.getItems().get(j).getName();
                    String desc = itemTable.getItems().get(j).getDescription();
                    String quantity = itemTable.getItems().get(j).getQuantity();
                    String unit = itemTable.getItems().get(j).getUnit();
                    String pricePerUnit = itemTable.getItems().get(j).getPricePerUnit();
                    String amount = itemTable.getItems().get(j).getAmount();

                    String query1 = "INSERT INTO `salesinvanditems`(`invoiceNum`, `itemName`, `description`, `quantity`, `unit`, `pricePerUnit`, `amount`) "
                            + "VALUES ('" + iNum + "','" + iName + "','" + desc+ "',"
                        + "'" + quantity + "','" + unit + "','" + pricePerUnit + "'"
                        + ",'" + amount + "')";
                    Statement st1 = con.createStatement();
                    st1.executeUpdate(query1);
                }

                con.close();
                count++;
                File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
                FileWriter myWriter = new FileWriter("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
                myWriter.write(String.valueOf(count));
                myWriter.close();

            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void ComputeAmount(MouseEvent event) {
        try {
            amount.setText(String.valueOf(Long.parseLong(quantity.getText()) * Double.parseDouble(priceOfUnit.getText())));
        } catch (Exception e) {
            System.out.println("Compute Amount : " + e);
        }
    }

    private void openAddParty() throws IOException {
        System.out.print("Clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quotations.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));

        stage.show();
    }

    @FXML
    void loadData(ActionEvent event) {
        //srCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Sr"));
        iNameCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));
        descCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Description"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Unit"));
        ppuCol.setCellValueFactory(new PropertyValueFactory<Items, String>("PricePerUnit"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Amount"));
        itemTable.setEditable(true);
        ObservableList<String> itemData = FXCollections.observableArrayList();
        itemData.add(itemName.getValue());
        itemData.add(desc.getText());
        itemData.add(quantity.getText());
        itemData.add("D");
        itemData.add(priceOfUnit.getText());
        itemData.add(amount.getText());
        Items i = new Items(itemName.getValue(), desc.getText(), quantity.getText(), "D", priceOfUnit.getText(), amount.getText());
        itemTable.getItems().addAll(i);

        System.out.println(itemTable.getItems().size());
        desc.setText("");
        quantity.setText("");
        priceOfUnit.setText("");
        amount.setText("");
        
        
        

    }

}
