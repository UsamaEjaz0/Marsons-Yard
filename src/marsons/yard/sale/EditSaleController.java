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
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class EditSaleController implements Initializable {

    ObservableList saleTypeList = FXCollections.observableArrayList();
    ObservableList customerNameList;
    ObservableList paymentTermsList = FXCollections.observableArrayList();
    ObservableList unitList = FXCollections.observableArrayList();
    ObservableList itemList = FXCollections.observableArrayList();
    ObservableList invPrefixList = FXCollections.observableArrayList();
    ObservableList pItemList = FXCollections.observableArrayList();
    ObservableList iList = FXCollections.observableArrayList();
    ObservableList pList = FXCollections.observableArrayList();
    ObservableList convList = FXCollections.observableArrayList();
    ObservableList<String> completeSale = FXCollections.observableArrayList();
    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    static String editInvoiceNum;

    @FXML
    private Button editRow;

    @FXML
    private TableColumn<Items, String> iNameCol;
    @FXML
    private TableColumn<Items, String> pItemCol;

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
    private Label cash;

    @FXML
    private Label dueDateLabel;

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
    @FXML
    private ComboBox<String> primaryItem;

    @FXML
    private TextField tName;

    @FXML
    private TextField dLoc;

    @FXML
    private TextField vNum;

    Connection con;
    @FXML
    private TextField cashReceived;

    @FXML
    private Label addDisc;
    @FXML
    private Text subTotalField;
    @FXML
    private Button addParty = new Button("Add Party");

    @FXML
    private TextField discPer;

    @FXML
    private TextField discRupees;

    @FXML
    private Text total;
    @FXML
    private ComboBox<String> taxTypes;

    @FXML
    private Text taxValue;
    @FXML
    private Label asterisk;
    @FXML
    private ComboBox<String> paymentMode;
    @FXML
    private TextField miscFreight;
    int count = 0;

    public void autoFill(String invNum) throws SQLException {
        Connection c;
        try {
            c = MyConnection.getConnection();

            String SQL = "SELECT * from sales where InvoiceNum = '" + invNum + "'";

            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                completeSale = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    completeSale.add(rs.getString(i));
                }

            }

            c.close();
            String SQL2 = "SELECT  `itemName`,`PrimaryItem`, `description`, `quantity`, `unit`, `pricePerUnit`, `amount`  FROM `salesinvanditems` where invoiceNum = '" + invNum + "'";
            salesItemTable(SQL2);

            this.invNum.setText(completeSale.get(0));
            saleType.setValue(completeSale.get(1));
            customerList.setValue(completeSale.get(2));
            billingName.setText(completeSale.get(3));

            //invDate.setValue(completeSale.get(4));
            //dueDate.setValue(completeSale.get(5));
            paymentTerms.setValue(completeSale.get(6));

            tName.setText(completeSale.get(7));
            dLoc.setText(completeSale.get(8));
            vNum.setText(completeSale.get(9));
            total.setText(completeSale.get(10));
            //discPer.setText(completeSale.get(11));
            miscFreight.setText(completeSale.get(12));
            discRupees.setText(completeSale.get(13));
            cashReceived.setText(completeSale.get(14));
            addDisc.setText(completeSale.get(15));
            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void salesItemTable(String SQL) {

        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();

            ResultSet rs = c.createStatement().executeQuery(SQL);
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                final int j = i;
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
//                        return new SimpleStringProperty(param.getValue().get(j).toString());
//                    }
//                });
//
//                itemTable.getColumns().addAll(col);
//
//            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
            System.out.println(data);
            for (int i = 0; i < data.size(); i++) {

                Items item = new Items(data.get(i).get(0), data.get(i).get(1), data.get(i).get(2), data.get(i).get(3), data.get(i).get(4), data.get(i).get(5), data.get(i).get(6));
                itemTable.getItems().add(item);
                System.out.println("Added");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        editRow.setVisible(false);
        itemTable.setEditable(true);
        iNameCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));
        pItemCol.setCellValueFactory(new PropertyValueFactory<Items, String>("PrimaryItem"));
        descCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Description"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Unit"));
        ppuCol.setCellValueFactory(new PropertyValueFactory<Items, String>("PricePerUnit"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        //Delete.setCellValueFactory(new PropertyValueFactory("b"));    
        qtyCol.setStyle("-fx-alignment: CENTER;");
        unitCol.setStyle("-fx-alignment: CENTER;");
        ppuCol.setStyle("-fx-alignment: CENTER;");
        amountCol.setStyle("-fx-alignment: CENTER;");
        paymentTermsList.addAll(
                "Custom", "Due on Receipt", "Net 15", "Net 30", "Net 45", "Net 60");
        saleTypeList.addAll(
                "Cash", "Credit");
        saleType.getItems()
                .addAll(saleTypeList);

        paymentTerms.getItems()
                .addAll(paymentTermsList);

        try {
            // TODO
            autoFill(editInvoiceNum);

        } catch (SQLException ex) {
            Logger.getLogger(EditSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((saleType.getValue()) == "Cash") {
            asterisk.setText("");
            asterisk.setStyle("-fx-text-fill: black");
            paymentTerms.setVisible(false);
            dueDate.setVisible(false);
            dueDateLabel.setVisible(false);
            cash.setVisible(true);

        } else if (saleType.getValue() == "Credit") {
            asterisk.setText("*");
            asterisk.setStyle("-fx-text-fill: red");
            paymentTerms.setVisible(true);
            dueDate.setVisible(true);
            dueDateLabel.setVisible(true);
            cash.setVisible(false);

        }
        itemTable.setOnMouseClicked((MouseEvent event) -> {
            try {
                if (event.getClickCount() > 0) {
                    editRow.setVisible(true);
                    Items x = (itemTable.getSelectionModel().getSelectedItem());

                    itemName.setValue(x.getName());
                    primaryItem.setValue(x.getPrimaryItem());
                    desc.setText(x.getDescription());
                    quantity.setText(x.getQuantity());
                    unit.setValue(x.getUnit());
                    priceOfUnit.setText(x.getPricePerUnit());
                    amount.setText(x.getAmount());

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
        init();
        paymentTerms.setVisible(false);
        dueDate.setVisible(false);
        dueDateLabel.setVisible(false);
        cash.setVisible(true);
        new AutoCompleteComboBoxListener<>(itemName);

        addParty.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                System.out.println("Hello World!");
            }
        }
        );

        paymentMode.getItems().addAll("Cash", "Cheque", "Bank Transfer", "Third Party Payout");
        paymentMode.setValue("Cash");

        saleType.getItems()
                .addAll(saleTypeList);
        saleType.setValue(
                "Cash");
        paymentTerms.getItems()
                .addAll(paymentTermsList);
        paymentTerms.setValue(
                "Due on Receipt");
        customerList.buttonCellProperty();

        //itemName.getItems().addAll(itemList);
        invDate.setValue(LocalDate.now());
        dueDate.setValue(LocalDate.now());
        invPrefixList.addAll(
                "SR-INV");
        invPrefix.getItems()
                .addAll(invPrefixList);
        invPrefix.setValue(
                "SR-INV");

        try {
            File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
            Scanner myReader = new Scanner(myObj);
            count = myReader.nextInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String date = invDate.getValue().toString();
        String year = date.substring(2, 4);
        String month = date.substring(5, 7);
        String defaultInvoiceNum1 = year + "" + month + "-" + String.valueOf(count);

        invNum.setText(defaultInvoiceNum1);

        invDate.valueProperty()
                .addListener((ov, oldValue, newValue) -> {
                    String date1 = newValue.toString();
                    System.out.println(newValue);
                    String year1 = date1.substring(2, 4);
                    String month1 = date1.substring(5, 7);
                    System.out.print(year1 + month1);
                    String defaultInvoiceNum = year + "-" + month + "-" + String.valueOf(count);
                    invNum.setText(defaultInvoiceNum);
                }
                );
        paymentTerms.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {
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
                }
                );

        saleType.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {
                        if ((saleType.getItems().get((Integer) number2)) == "Cash") {
                            asterisk.setText("");
                            asterisk.setStyle("-fx-text-fill: black");
                            paymentTerms.setVisible(false);
                            dueDate.setVisible(false);
                            dueDateLabel.setVisible(false);
                            cash.setVisible(true);

                        } else if ((saleType.getItems().get((Integer) number2)) == "Credit") {
                            asterisk.setText("*");
                            asterisk.setStyle("-fx-text-fill: red");
                            paymentTerms.setVisible(true);
                            dueDate.setVisible(true);
                            dueDateLabel.setVisible(true);
                            cash.setVisible(false);

                        }
                    }
                }
                );

        unit.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    double ppu;
                    boolean check = true;

                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2) {
                        try {
                            String baseUnit = unit.getItems().get(0);
                            double qty = Double.parseDouble(quantity.getText());
                            if (check) {
                                ppu = Double.parseDouble(priceOfUnit.getText());
                                check = false;
                            }
                            String newUnit = unit.getItems().get((Integer) observableValue.getValue());

                            String SQL3 = "";
                            if (observableValue.getValue().equals(1)) {
                                SQL3 = "select conversionOne from items where pUnit = '" + baseUnit + "' and sUnitOne = '" + newUnit + "'";
                            } else if (observableValue.getValue().equals(2)) {
                                SQL3 = "select conversionTwo from items where pUnit = '" + baseUnit + "' and sUnitTwo = '" + newUnit + "'";
                            } else if (observableValue.getValue().equals(3)) {
                                SQL3 = "select conversionThree from items where pUnit = '" + baseUnit + "' and sUnitThree = '" + newUnit + "'";
                            } else {
                                priceOfUnit.setText(String.valueOf(ppu));
                            }

                            System.out.println("Base Unit " + baseUnit);
                            System.out.println("New Unit " + newUnit);

                            Connection c = MyConnection.getConnection();

                            ResultSet rs3 = c.createStatement().executeQuery(SQL3);

                            while (rs3.next()) {
                                convList = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
                                    convList.add(rs3.getString(i));
                                }

                            }

                            double conv = eval((String) convList.get(0));
                            System.out.println(conv);
                            priceOfUnit.setText(String.valueOf(ppu / conv));

                            if (quantity.getText() != "" && priceOfUnit.getText() != "") {
                                amount.setText(String.valueOf(Long.parseLong(quantity.getText()) * Double.parseDouble(priceOfUnit.getText())));
                            }

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }

                }
                );
        try {
            double subTotal = 0;
            for (Items item : itemTable.getItems()) {
                subTotal = subTotal + Double.parseDouble(item.getAmount());
            }
            subTotalField.setText(String.valueOf((subTotal * 100) / 100));
            total.setText(String.valueOf(subTotal));
            if (!(miscFreight.getText() == null)) {
                total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText()) + Double.parseDouble(miscFreight.getText())));
            }
            if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
        } catch (Exception e) {
            System.out.println(e);
        }
        itemName.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {
                        try {

                            unit.getItems().clear();
                            Connection c = MyConnection.getConnection();
                            String SQL3 = "SELECT `pUnit` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select`sUnitOne` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select `sUnitTwo` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select `sUnitThree` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "'";
                            ResultSet rs3 = c.createStatement().executeQuery(SQL3);
                            while (rs3.next()) {
                                unitList = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
                                    if (rs3.getString(i) != "[NONE]" && rs3.getString(i) != "[]") {
                                        System.out.println(rs3.getString(i));
                                        unitList.add(rs3.getString(i));
                                    }
                                }
                                if (unitList.get(0).equals("NONE") || unitList.get(0).equals("")) {

                                } else {
                                    unit.getItems().addAll(unitList);
                                }
                            }
                            if (observableValue.getValue().equals(-1)) {

                            } else {
                                primaryItem.getItems().clear();

                                String SQL2 = "select ComponentOf from items where name = '" + itemName.getValue() + "'";
                                ResultSet rs2 = c.createStatement().executeQuery(SQL2);
                                while (rs2.next()) {
                                    System.out.println("HERE");
                                    pList = FXCollections.observableArrayList();
                                    for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                                        if (rs2.getString(i) != "[NONE]" && rs2.getString(i) != "[]") {
                                            System.out.println(rs2.getString(i));
                                            pList.add(rs2.getString(i));
                                        }

                                    }
                                    if (pList.get(0).equals("NONE") || pList.get(0).equals("")) {

                                    } else {
                                        primaryItem.getItems().addAll(pList);
                                    }
                                }
                            }
                            unit.getSelectionModel().selectFirst();
                        } catch (SQLException ex) {
                            Logger.getLogger(AddSaleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );
        primaryItem.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {

                        try {
                            unit.getItems().clear();
                            Connection c = MyConnection.getConnection();
                            String SQL3 = "SELECT `pUnit` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select`sUnitOne` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select `sUnitTwo` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "' union "
                                    + "select `sUnitThree` from items where ComponentOf = '" + primaryItem.getValue() + "' and name = '" + itemName.getValue() + "'";
                            ResultSet rs3 = c.createStatement().executeQuery(SQL3);
                            System.out.println(primaryItem.getValue());
                            System.out.println(itemName.getValue());

                            while (rs3.next()) {
                                System.out.println("HERE");
                                unitList = FXCollections.observableArrayList();
                                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
                                    if (rs3.getString(i) != "[NONE]" && rs3.getString(i) != "[]") {
                                        System.out.println(rs3.getString(i));
                                        unitList.add(rs3.getString(i));
                                    }
                                    System.out.println(unitList);

                                }
                                if (unitList.get(0).equals("NONE") || unitList.get(0).equals("")) {

                                } else {
                                    unit.getItems().addAll(unitList);
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AddSaleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        unit.getSelectionModel().selectFirst();
                    }
                }
                );
        customerList.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {
                        System.out.println(customerList.getValue());
                    }
                }
                );
        
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

            String SQL2 = "select distinct name from items";
            ResultSet rs2 = c.createStatement().executeQuery(SQL2);

            while (rs2.next()) {
                itemList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                    itemList.add(rs2.getString(i));
                }

                itemName.getItems().addAll(itemList);
                System.out.println(itemList);
            }
            String SQL3 = "select distinct ComponentOf from items";
            ResultSet rs3 = c.createStatement().executeQuery(SQL3);

            while (rs3.next()) {
                pItemList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
                    pItemList.add(rs3.getString(i));
                }

                primaryItem.getItems().addAll(pItemList);

            }
//            String SQL2 = "select distinct name from items";
//            ResultSet rs2 = c.createStatement().executeQuery(SQL2);
//
//            while (rs2.next()) {
//                itemList = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
//                    itemList.add(rs2.getString(i));
//                }
//
//                itemName.getItems().addAll(itemList);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        try {
            if (event.getSource() == addItem) {
                //To be added
            }
            if (event.getSource() == save) {
                Button b = new Button("Added");
                con = MyConnection.getConnection();
                String query = "INSERT INTO `sales`(`InvoiceNum`, `saleType`, `customerName`, `billingName`, `InvoiceDate`, `DueDate`, `paymentTerms`, `transport`, `deliveryLocation`, `vehicleNumber`,  `total`, `discount`, `miscFreight`, `discountedRupees`,`CashReceived`, `addDiscount`)"
                        + "VALUES ('" + invPrefix.getValue() + invNum.getText() + "','" + saleType.getValue() + "','" + customerList.getValue() + "',"
                        + "'" + billingName.getText() + "','" + invDate.getValue() + "','" + dueDate.getValue() + "'"
                        + ",'" + paymentTerms.getValue() + "', '" + tName.getText() + "','" + dLoc.getText() + "','" + vNum.getText() + "','" + total.getText() + "','" + discPer.getText() + "','" + miscFreight.getText() + "','" + discRupees.getText() + "','" + cashReceived.getText() + "','" + addDisc.getText() + "')";
                Statement st = con.createStatement();
                st.executeUpdate(query);

                //fdfdfdf
                for (int j = 0; j < itemTable.getItems().size(); j++) {

                    String iNum = invPrefix.getValue() + invNum.getText();
                    String iName = itemTable.getItems().get(j).getName();
                    String desc = itemTable.getItems().get(j).getDescription();
                    String quantity = itemTable.getItems().get(j).getQuantity();
                    String unit = itemTable.getItems().get(j).getUnit();
                    String pricePerUnit = itemTable.getItems().get(j).getPricePerUnit();
                    String amount = itemTable.getItems().get(j).getAmount();
                    String itemSource = itemTable.getItems().get(j).getPrimaryItem();

                    String query1 = "INSERT INTO `salesinvanditems`(`invoiceNum`, `itemName`, `description`, `quantity`, `unit`, `pricePerUnit`, `amount`, `PrimaryItem`)"
                            + "VALUES ('" + iNum + "','" + iName + "','" + desc + "',"
                            + "'" + quantity + "','" + unit + "','" + pricePerUnit + "'"
                            + ",'" + amount + "','" + itemSource + "')";
                    Statement st1 = con.createStatement();
                    st1.executeUpdate(query1);
                }

                con.close();
                count++;
                File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
                FileWriter myWriter = new FileWriter("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\InvoiceIncrement.txt");
                myWriter.write(String.valueOf(count));
                myWriter.close();

                Stage stage = (Stage) save.getScene().getWindow();
                stage.close();

            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void ComputeAmount(KeyEvent event) {
        try {
            if (quantity.getText() != "" && priceOfUnit.getText() != "") {
                amount.setText(String.valueOf(Long.parseLong(quantity.getText()) * Double.parseDouble(priceOfUnit.getText())));
            }

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
    void deleteItem(ActionEvent event) {
        itemTable.getItems().removeAll(itemTable.getSelectionModel().getSelectedItems());
        double subTotal = 0;
        try {
            
            for (Items item : itemTable.getItems()) {
                subTotal = subTotal + Double.parseDouble(item.getAmount());
            }
            discPer.setText(String.valueOf(Math.round((Double.parseDouble(discRupees.getText()) / (subTotal * 0.01)) * 100.00) / 100.00));
            subTotalField.setText(String.valueOf((subTotal * 100) / 100));
            total.setText(String.valueOf(subTotal));
            if (!(miscFreight.getText() == null)) {
                total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText()) + Double.parseDouble(miscFreight.getText())));
            }
             if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @FXML
    void calPer(KeyEvent event) {
        double subTotal = 0;
        try {

            for (Items item : itemTable.getItems()) {
                subTotal = subTotal + Double.parseDouble(item.getAmount());
            }
            discPer.setText(String.valueOf(Math.round((Double.parseDouble(discRupees.getText()) / (subTotal * 0.01)) * 100.00) / 100.00));
            total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText())));
            if (!(miscFreight.getText() == null)) {
                total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText()) + Double.parseDouble(miscFreight.getText())));
            }
             if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    @FXML
    void calAddDisc(KeyEvent event) {
        try {
            if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void editRow(ActionEvent event) {
        deleteItem(event);
        loadData(event);
        
        editRow.setVisible(false);
    }

    @FXML
    void calRupees(KeyEvent event) {
        double subTotal = 0;
        try {

            for (Items item : itemTable.getItems()) {
                subTotal = subTotal + Double.parseDouble(item.getAmount());
            }
            discRupees.setText(String.valueOf(Math.round((subTotal * 0.01 * Double.parseDouble(discPer.getText())) * 100.00) / 100.00));

            total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText())));
            if (!(miscFreight.getText() == null)) {
                total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText()) + Double.parseDouble(miscFreight.getText())));
            }
             if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    @FXML
    void loadData(ActionEvent event) {
        itemTable.setEditable(true);
        iNameCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));
        pItemCol.setCellValueFactory(new PropertyValueFactory<Items, String>("PrimaryItem"));
        descCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Description"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Quantity"));
        unitCol.setCellValueFactory(new PropertyValueFactory<Items, String>("Unit"));
        ppuCol.setCellValueFactory(new PropertyValueFactory<Items, String>("PricePerUnit"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        //Delete.setCellValueFactory(new PropertyValueFactory("b"));    
        qtyCol.setStyle("-fx-alignment: CENTER;");
        unitCol.setStyle("-fx-alignment: CENTER;");
        ppuCol.setStyle("-fx-alignment: CENTER;");
        amountCol.setStyle("-fx-alignment: CENTER;");
        ObservableList<String> itemData = FXCollections.observableArrayList();
        itemData.add(itemName.getValue());
        itemData.add(primaryItem.getValue());
        itemData.add(desc.getText());
        itemData.add(quantity.getText());
        itemData.add(unit.getValue());
        itemData.add(priceOfUnit.getText());
        itemData.add(amount.getText());
        Items i = new Items(itemName.getValue(), primaryItem.getValue(), desc.getText(), quantity.getText(), unit.getValue(), priceOfUnit.getText(), amount.getText());

        itemTable.getItems().addAll(i);
        //System.out.print(itemTable);

        desc.setText("");
        quantity.setText("");
        priceOfUnit.setText("");
        amount.setText("");

        try {
            double subTotal = 0;
            for (Items item : itemTable.getItems()) {
                subTotal = subTotal + Double.parseDouble(item.getAmount());
            }
            discPer.setText(String.valueOf(Math.round((Double.parseDouble(discRupees.getText()) / (subTotal * 0.01)) * 100.00) / 100.00));
            
            subTotalField.setText(String.valueOf((subTotal * 100) / 100));
            total.setText(String.valueOf(subTotal));
            if (!(miscFreight.getText() == null)) {
                total.setText(String.valueOf(subTotal - Double.parseDouble(discRupees.getText()) + Double.parseDouble(miscFreight.getText())));
            }
             if (cashReceived.getText().equals("")) {
                addDisc.setText(total.getText());
            }
            addDisc.setText(String.valueOf((Double.parseDouble(total.getText()) - Double.parseDouble(cashReceived.getText()))));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm(); // addition
                    } else if (eat('-')) {
                        x -= parseTerm(); // subtraction
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        x *= parseFactor(); // multiplication
                    } else if (eat('/')) {
                        x /= parseFactor(); // division
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {
                    return parseFactor(); // unary plus
                }
                if (eat('-')) {
                    return -parseFactor(); // unary minus
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') {
                        nextChar();
                    }
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) {
                        x = Math.sqrt(x);
                    } else if (func.equals("sin")) {
                        x = Math.sin(Math.toRadians(x));
                    } else if (func.equals("cos")) {
                        x = Math.cos(Math.toRadians(x));
                    } else if (func.equals("tan")) {
                        x = Math.tan(Math.toRadians(x));
                    } else {
                        throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) {
                    x = Math.pow(x, parseFactor()); // exponentiation
                }
                return x;
            }
        }.parse();
    }

    void setEditData(String string) {
        editInvoiceNum = string;
        System.out.println(editInvoiceNum);

    }

}
