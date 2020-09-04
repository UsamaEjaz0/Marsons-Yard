/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class SalesAddPaymentInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList customerNameList;
    ObservableList autoFill;
    static String invNumb, invDateNormal, invTotal;

    @FXML
    private ComboBox<Person> test;
    @FXML
    private AnchorPane screen;
    @FXML
    private TextField totalBank;

    @FXML
    private TextField contraAmount;
    @FXML
    private Button linkPay;

    @FXML
    private TextField remBal;
    @FXML
    private ComboBox<String> party;

    @FXML
    private TextField cash;

    @FXML
    private Label rupees;

    @FXML
    private TextField totalAmount;

    @FXML
    private Button edit;

    @FXML
    private Label totalCheques;

    @FXML
    private Button cheque;

    @FXML
    private Button bank;

    @FXML
    private Button cNote;

    @FXML
    private Button editCNote;

    @FXML
    private Button editBank;

    @FXML
    private TextField receiptNum;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> preFix;

    @FXML
    private Label invDateLabel;

    @FXML
    private Label invNumLabel;

    @FXML
    private TextField invNum;

    @FXML
    private TextField invDate;

    @FXML
    private TextField received;

    @FXML
    private TextField disc;

    @FXML
    private Text total;

    @FXML
    private Button save;

    @FXML
    private TextArea desc;
    static boolean check = false;

    Connection con;
    int count = 0;

    void setData(String invNum, String date, String total) {
        this.invDateNormal = date;
        this.invNumb = invNum;
        invTotal = total;
        check = true;
    }

    @FXML
    void openChequeWindow(ActionEvent event) throws IOException {
        System.out.println("Selected");
        System.out.println("REceipt num is " + receiptNum.getText());
        EditChequeController e = new EditChequeController();
        e.receipt = receiptNum.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCheque.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root1);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void handleAction(ActionEvent event) throws IOException {
        try {
            if (event.getSource() == save) {
                con = MyConnection.getConnection();
                String query = "";
                Statement st = con.createStatement();
                st.executeUpdate(query);
                con.close();

            }
        } catch (Exception e) {
            System.out.print(e);
        }

        count++;
        File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\PaymentInReceiptIncrement");
        FileWriter myWriter = new FileWriter("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\PaymentInReceiptIncrement");
        myWriter.write(String.valueOf(count));
        myWriter.close();

        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML
    void linkPayment(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LinkPayment.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root1);
        stage.setScene(scene);

        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Person p1 = new Person("Usama", "1000");
        Person p2 = new Person("Hamza", "-2000");

        test.setItems(FXCollections.observableArrayList(
                new Person("Usama", "1000000000000"), new Person("Hamza", "-2000")));
        test.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person object) {
                String displayName = null;
                if (object != null) {
                    displayName = object.getName();
                }
                return displayName;
            }

            @Override
            public Person fromString(String string) {
                return null;
            }
        });
        test.setCellFactory(cell -> new ListCell<Person>() {

            // Create our layout here to be reused for each ListCell
            GridPane gridPane = new GridPane();
            Label lblName = new Label();
            Label lblTitle = new Label();

            // Static block to configure our layout
            {
                // Ensure all our column widths are constant

                gridPane.getColumnConstraints().addAll(
                        new ColumnConstraints(100, 100, 100),
                        new ColumnConstraints(100, 100, 100)
                );

                gridPane.add(lblName, 0, 1);
                gridPane.add(lblTitle, 1, 1);

                GridPane.setHalignment(lblTitle, HPos.RIGHT);

            }

            // We override the updateItem() method in order to provide our own layout for this Cell's graphicProperty
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);

                if (!empty && person != null) {
                    Image image = new Image(getClass().getResourceAsStream("download.png"));
                    ImageView img = new ImageView(image);
                    img.setFitHeight(20);
                    img.setFitWidth(20);
                    lblTitle.setGraphic(img);
                    // Update our Labels
                    lblName.setText(person.getName());
                    lblTitle.setText(person.getBalance());
                    lblTitle.setWrapText(true);
//lblTitle.setAlignment(Pos.CENTER_LEFT);

                    lblTitle.setContentDisplay(ContentDisplay.RIGHT);
                    if (Long.parseLong(person.getBalance()) > 0) {
                        lblTitle.setStyle("-fx-text-fill: red");
                    } else {
                        lblTitle.setStyle("-fx-text-fill: green");
                    }

                    // Set this ListCell's graphicProperty to display our GridPane
                    setGraphic(gridPane);
                } else {
                    // Nothing to display here
                    setGraphic(null);
                }
            }
        });
        invNumb = "";
        invNumLabel.setVisible(false);
        invNum.setVisible(false);
        invDate.setVisible(false);
        invDateLabel.setVisible(false);
        date.setValue(LocalDate.now());

        init();
        new AutoCompleteBox(party);
        preFix.getItems().addAll("RCD");
        preFix.setValue("RCD");
        try {
            File myObj = new File("C:\\Users\\uejaz\\Documents\\NetBeansProjects\\Marsons Yard\\src\\marsons\\yard\\sale\\PaymentInReceiptIncrement");
            Scanner myReader = new Scanner(myObj);
            count = myReader.nextInt();
            String mydate = date.getValue().toString();
            String year = mydate.substring(2, 4);
            String month = mydate.substring(5, 7);
            String defaultInvoiceNum1 = year + "" + month + "-" + String.valueOf(count);

            receiptNum.setText(defaultInvoiceNum1);
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        }

    }

    @FXML
    void add(ActionEvent event) {
        if (event.getSource() == cheque) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cheque.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                ChequeController.receipt = receiptNum.getText();
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SalesAddPaymentInController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == bank) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BankTransfer.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root1);
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SalesAddPaymentInController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void init() {

        Connection c;
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT Name, NTN from customers";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                customerNameList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    customerNameList.add(rs.getString(i));
                }

                party.getItems().addAll(customerNameList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    @FXML
    void AutoFill(MouseEvent event) throws SQLException {
        Stage stage = (Stage) screen.getScene().getWindow();

        getAmountAndCheques();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    Connection con;
                    con = MyConnection.getConnection();
                    String query = "DELETE FROM `cheque` WHERE Receipt = '" + receiptNum.getText() + "'";
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SalesAddPaymentInController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        if (check) {

            try {
                invNum.setVisible(true);

                invNumLabel.setVisible(true);
                invDate.setVisible(true);
                invDateLabel.setVisible(true);
                if (!received.getText().equals("")) {
                    remBal.setText(String.valueOf(Double.parseDouble(invTotal) - Double.parseDouble(received.getText())));
                }
                invNum.setText(invNumb);
                invDate.setText(invDateNormal);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @FXML
    void setReceivedPayment(KeyEvent event) {
        try {
            double cheque = 0;
            double bank = 0;
            double cashAmount = 0;
            double contra = 0;

            if (!totalAmount.getText().equals("")) {
                cheque = Double.parseDouble(totalAmount.getText());
            }

            if (!totalBank.getText().equals("")) {
                bank = Double.parseDouble(totalBank.getText());
            }

            if (!cash.getText().equals("")) {
                cashAmount = Double.parseDouble(cash.getText());
            }
            if (!contraAmount.getText().equals("")) {
                contra = Double.parseDouble(contraAmount.getText());
            }
            double receivedAmount = cheque + bank + cashAmount + contra;
            received.setText(String.valueOf(receivedAmount));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getAmountAndCheques() {

        Connection c;
        ObservableList x = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL = "SELECT sum(Amount), count(ChequeNum) from cheque where Receipt = '" + receiptNum.getText() + "'";
            System.out.println(SQL);
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    x.add(rs.getString(i));
                }

            }
            System.out.println(x);
            System.out.println(x.get(0));

            if (!(x.get(0) == null)) {
                totalAmount.setText(String.valueOf(x.get(0)));
            }
            totalCheques.setText(String.valueOf(x.get(1)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}

class AutoCompleteBox implements EventHandler<KeyEvent> {

    private ComboBox comboBox;
    final private ObservableList data;
    private Integer sid;

    public AutoCompleteBox(final ComboBox comboBox) {
        this.comboBox = comboBox;
        this.data = comboBox.getItems();

        this.doAutoCompleteBox();
    }

    public AutoCompleteBox(final ComboBox comboBox, Integer sid) {
        this.comboBox = comboBox;
        this.data = comboBox.getItems();
        this.sid = sid;

        this.doAutoCompleteBox();
    }

    private void doAutoCompleteBox() {
        this.comboBox.setEditable(true);
        this.comboBox.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//mean onfocus
                this.comboBox.show();
            }
        });

        this.comboBox.getEditor().setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    return;
                }
            }
            this.comboBox.show();
        });

        this.comboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            moveCaret(this.comboBox.getEditor().getText().length());
        });

        this.comboBox.setOnKeyPressed(t -> comboBox.hide());

        this.comboBox.setOnKeyReleased(AutoCompleteBox.this);

        if (this.sid != null) {
            this.comboBox.getSelectionModel().select(this.sid);
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
                || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            String str = this.comboBox.getEditor().getText();
            if (str != null && str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            if (str != null) {
                this.comboBox.getEditor().setText(str);
                moveCaret(str.length());
            }
            this.comboBox.getSelectionModel().clearSelection();
        }

        if (event.getCode() == KeyCode.ENTER && comboBox.getSelectionModel().getSelectedIndex() > -1) {
            return;
        }

        setItems();
    }

    private void setItems() {
        ObservableList list = FXCollections.observableArrayList();

        for (Object datum : this.data) {
            String s = this.comboBox.getEditor().getText().toLowerCase();
            if (datum.toString().toLowerCase().contains(s.toLowerCase())) {
                list.add(datum.toString());
            }
        }

        if (list.isEmpty()) {
            this.comboBox.hide();
        }

        this.comboBox.setItems(list);
        this.comboBox.show();
    }

    private void moveCaret(int textLength) {
        this.comboBox.getEditor().positionCaret(textLength);
    }

}
