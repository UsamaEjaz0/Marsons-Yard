/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.purchase;

import connection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import marsons.yard.sale.SalesController;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class PurchasesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox<String> purFilter;

    @FXML
    private DatePicker customStartDate;

    @FXML
    private DatePicker customEndDate;
    @FXML
    private Button addPurchase;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView purTransactions;

    public void purTable(String SQL) {
        purTransactions.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();

            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                purTransactions.getColumns().addAll(col);
                

            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
            purTransactions.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    void handleAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPurchase.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String query = "Select * from sales";
        purTable(query);
        purFilter.getItems().addAll("All Purchase Bills", "This Month", "Last Month", "This Quarter", "This Fiscal Year",
                "This Calendar Year", "Custom");
        purFilter.setValue("All Purchase Bills");
        purFilter.getSelectionModel()
                .selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number,
                            Number number2
                    ) {
                        String query = "Select * from purchases";
                        String filter = purFilter.getValue();

                        if ((purFilter.getItems().get((Integer) number2)) == "All Purchase Bills") {

                            query = "Select * from purchases ";
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            customStartDate.setValue(null);
                            customEndDate.setValue(null);
                        } else if ((purFilter.getItems().get((Integer) number2)) == "This Month") {
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            Calendar cal = Calendar.getInstance();
                            int m = cal.getInstance().get(cal.MONTH) + 1;
                            int y = cal.getInstance().get(cal.YEAR);
                            int d = 1;

                            Calendar mycal = new GregorianCalendar(y, m - 1, d);

                            // Get the number of days in that month
                            int endDay = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

                            String startDate = y + "-" + m + "-01";
                            String endDate = y + "-" + m + "-" + endDay;
                            System.out.print(endDate);
                            query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                        } else if ((purFilter.getItems().get((Integer) number2)) == "Last Month") {
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            customStartDate.setValue(null);
                            customEndDate.setValue(null);
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.MONTH, -1);

                            int m = cal.get(cal.MONTH) + 1;
                            int y = cal.get(cal.YEAR);
                            int d = 1;

                            Calendar mycal = new GregorianCalendar(y, m - 1, d);

                            // Get the number of days in that month
                            int endDay = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

                            String startDate = y + "-" + m + "-01";
                            String endDate = y + "-" + m + "-" + endDay;
                            System.out.print(startDate + "  " + endDate);
                            query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                        } else if ((purFilter.getItems().get((Integer) number2)) == "This Quarter") {
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            customStartDate.setValue(null);
                            customEndDate.setValue(null);
                            try {
                                Calendar cal = Calendar.getInstance();

                                ObservableList dates = null;
                                Connection c = MyConnection.getConnection();
                                String sql = "SELECT `startDate`, `endDate` FROM `financialyear`";
                                ResultSet rs3 = c.createStatement().executeQuery(sql);

                                while (rs3.next()) {
                                    dates = FXCollections.observableArrayList();
                                    for (int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
                                        dates.add(rs3.getString(i));
                                    }

                                }

                                String start = (String) dates.get(0);
                                String end = (String) dates.get(1);

                                String[] startArr = start.split("-");
                                String[] endArr = end.split("-");

                                Calendar mycal = new GregorianCalendar(Integer.parseInt(startArr[0]),
                                        Integer.parseInt(startArr[1]) - 1, Integer.parseInt(startArr[2]));

                                int quarter = (LocalDate.now().plusMonths(6).get(IsoFields.QUARTER_OF_YEAR));
                                String q1startDate = null, q1endDate = null, q2startDate = null, q2endDate = null, q3startDate = null, q3endDate = null, q4startDate = null, q4endDate = null;
                                System.out.println(quarter);
                                if (quarter == 1) {
                                    q1startDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    mycal.add(mycal.MONTH, 3);
                                    mycal.add(mycal.DATE, -1);
                                    q1endDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    System.out.println(q1startDate + "  " + q1endDate);
                                    query = "SELECT * from purchases where InvoiceDate BETWEEN '" + q1startDate + "' and '" + q1endDate + "'";
                                } else if (quarter == 2) {
                                    mycal.add(mycal.MONTH, 3);
                                    q2startDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    mycal.add(mycal.MONTH, 3);
                                    mycal.add(mycal.DATE, -1);
                                    q2endDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    System.out.println(q2startDate + "  " + q2endDate);
                                    query = "SELECT * from purchases where InvoiceDate BETWEEN '" + q2startDate + "' and '" + q2endDate + "'";
                                } else if (quarter == 3) {
                                    mycal.add(mycal.MONTH, 6);
                                    q3startDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    mycal.add(mycal.MONTH, 3);
                                    mycal.add(mycal.DATE, -1);
                                    q3endDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    System.out.println(q3startDate + "  " + q3endDate);
                                    query = "SELECT * from purchases where InvoiceDate BETWEEN '" + q3startDate + "' and '" + q3endDate + "'";
                                } else if (quarter == 4) {
                                    mycal.add(mycal.MONTH, 9);
                                    q4startDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    mycal.add(mycal.MONTH, 3);
                                    mycal.add(mycal.DATE, -1);
                                    q4endDate = mycal.get(mycal.YEAR) + "-" + (mycal.get(mycal.MONTH) + 1) + "-" + mycal.get(mycal.DATE);
                                    System.out.println(q4startDate + "  " + q4endDate);
                                    query = "SELECT * from purchases where InvoiceDate BETWEEN '" + q4startDate + "' and '" + q4endDate + "'";
                                }

//                              
                            } catch (SQLException ex) {
                                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if ((purFilter.getItems().get((Integer) number2)) == "This Fiscal Year") {
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            customStartDate.setValue(null);
                            customEndDate.setValue(null);
                            String startDate = LocalDate.now().getYear() + "-" + 07 + "-" + 01;
                            String endDate = (LocalDate.now().getYear() + 1) + "-" + 06 + "-" + 30;
                            query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";

                        } else if ((purFilter.getItems().get((Integer) number2)) == "This Calendar Year") {
                            customStartDate.getEditor().clear();
                            customEndDate.getEditor().clear();
                            customStartDate.setValue(null);
                            customEndDate.setValue(null);
                            String startDate = LocalDate.now().getYear() + "-" + 01 + "-" + 01;
                            String endDate = (LocalDate.now().getYear()) + "-" + 12 + "-" + 31;
                            query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                            System.out.println(query);
                        } else if ((purFilter.getItems().get((Integer) number2)) == "Custom") {

                            if (customStartDate != null && customEndDate != null) {
                                String startDate = customStartDate.getValue().toString();
                                String endDate = customEndDate.getValue().toString();
                                query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                            }
                        }
                        purTable(query);
                    }
                }
                );
         customStartDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                try {
                    String startDate = customStartDate.getValue().toString();
                    String endDate = customEndDate.getValue().toString();

                    if (endDate != null) {
                        String query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                        purTable(query);
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        });
        customEndDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                try {
                    String startDate = customStartDate.getValue().toString();
                    String endDate = customEndDate.getValue().toString();

                    if (startDate != null) {
                        String query = "SELECT * from purchases where InvoiceDate BETWEEN '" + startDate + "' and '" + endDate + "'";
                        purTable(query);
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        });
    }

}
