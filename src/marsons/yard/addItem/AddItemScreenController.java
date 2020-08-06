/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.addItem;
//3 jagah changes krni hain

import connection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class AddItemScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<String> pItemList;
    private ObservableList<String> cList;

    boolean check;
    static String a = "", b = "", c = "", d = "", e = "", f = "", g = "", h = "";
    
    @FXML
    private ComboBox<String> categoryList;
    @FXML
    private ComboBox<String> pItems;
    @FXML
    private Text s1;

    @FXML
    private Text s3;

    @FXML
    private Text s2;
    @FXML
    private Text qtyDef;

    @FXML
    private Button selectUnit;

    @FXML
    private TextField itemName;

    @FXML
    private TextField salePrice;

    @FXML
    private TextField minStock;

    @FXML
    private TextField qty;

    @FXML
    private TextField code;

    @FXML
    private DatePicker date;

    @FXML
    private TextField pPrice;

    @FXML
    private TextField atPrice;



    @FXML
    private Button saveAndNew;

    @FXML
    private Button save;
    Connection con;

    @FXML
    void handleAction(ActionEvent event) throws SQLException, IOException {
        con = MyConnection.getConnection();
        if (event.getSource() == selectUnit) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("unit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
            check = true;
        }
        if (event.getSource() == save) {
            String query = "INSERT INTO `items`(`Name`, `ComponentOf`, `MainCat`, `ItemCode`, `SalePrice`, `OpeningQty`, `MinStock`, `pUnit`, `pPrice`, `AtPrice`, `Date`, `sUnitOne`, `sUnitTwo`, "
                    + "`sUnitThree`, `conversionOne`, `conversionTwo`, `conversionThree`) "
                    + "VALUES ('" + itemName.getText() + "','" + pItems.getValue() + "','" + categoryList.getValue() + "','" + code.getText() + "','" + salePrice.getText() + "',"
                    + "'" + qty.getText() + "','" + minStock.getText() + "','" + a + "','" + pPrice.getText() + "','" + atPrice.getText() + "','" + date.getValue() + "','" + b + "','" + c
                    + "','" + d + "','" + e + "','" + f + "','" + g + "')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        }
        if (event.getSource() == saveAndNew) {
            String query = "INSERT INTO `items`(`Name`, `ComponentOf`, `MainCat`, `ItemCode`, `SalePrice`, `OpeningQty`, `MinStock`, `pUnit`, `pPrice`, `AtPrice`, `Date`, `sUnitOne`, `sUnitTwo`, "
                    + "`sUnitThree`, `conversionOne`, `conversionTwo`, `conversionThree`) "
                    + "VALUES ('" + itemName.getText() + "','" + pItems.getValue() + "','" + categoryList.getValue() + "','" + code.getText() + "','" + salePrice.getText() + "',"
                    + "'" + qty.getText() + "','" + minStock.getText() + "','" + a + "','" + pPrice.getText() + "','" + atPrice.getText() + "','" + date.getValue() + "','" + b + "','" + c
                    + "','" + d + "','" + e + "','" + f + "','" + g + "')";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            itemName.setText("");
            code.setText("");
            salePrice.setText("");
            qty.setText("");
            /*unit.setValue("");*/
            pPrice.setText("");
            minStock.setText("");
            atPrice.setText("");
            date.setValue(LocalDate.now());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date.setValue(LocalDate.now());
        init();
        s1.setText("");
        s2.setText("");
        s3.setText("");

        check = false;

    }

    public void setData(String a, String b, String c, String d, String conv1, String conv2, String conv3) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = conv1;
        this.f = conv2;
        this.g = conv3;
        System.out.println("1 " + a + "=" + conv1 + " " + b);
        System.out.println("1 " + a + "=" + conv2 + " " + c);
        System.out.println("1 " + a + "=" + conv3 + " " + d);

    }

    public void setUnitData(MouseEvent event) {

        if (check == true) {
            s1.setText("1 " + a + "=" + e + " " + b + " (Default)");
            s2.setText("1 " + a + "=" + f + " " + c);
            s3.setText("1 " + a + "=" + g + " " + d);
            if (b == "NONE") {
                s1.setText("");
            }
            if (c == "NONE") {
                s2.setText("");
            }
            if (d == "NONE") {
                s3.setText("");
            }

        }
    }
public void init() {

        Connection c;
        try {
            c = MyConnection.getConnection();
           

            String SQL2 = "SELECT distinct ComponentOf from items";
            ResultSet rs2 = c.createStatement().executeQuery(SQL2);

            while (rs2.next()) {
                pItemList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
                    pItemList.add(rs2.getString(i));
                }

                pItems.getItems().addAll(pItemList);
                
                
            }
            String SQL = "SELECT distinct ComponentOf from items";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                cList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    cList.add(rs.getString(i));
                }

                categoryList.getItems().addAll(cList);
                
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
  
    @FXML
    void setData(MouseEvent event) {
        try {
            if (check == true) {
                s1.setText("1 " + a + "=" + e + " " + b + " (Default)");
                s2.setText("1 " + a + "=" + f + " " + c);
                s3.setText("1 " + a + "=" + g + " " + d);
                if (b == "NONE") {
                    s1.setText("");
                }
                if (c == "NONE") {
                    s2.setText("");
                }
                if (d == "NONE") {
                    s3.setText("");
                }

            }
            if (e != "" && qty.getText() != "") {
                double dummy = Double.parseDouble(qty.getText()) / Double.parseDouble(e);
                double roundOff = (double) Math.round(dummy * 1000) / 1000;
                qtyDef.setText(String.valueOf(roundOff) + " " + a);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
