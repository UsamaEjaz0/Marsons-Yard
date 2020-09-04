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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class EditItemController implements Initializable {

    private ObservableList<String> pItemList;
    private ObservableList<String> cList;
    private ObservableList<Object> editItemList;
    static String iName, pName;
    boolean check;

    static String a = "", b = "", c = "", d = "", e = "", f = "", g = "", h = "";

    @FXML
    private ComboBox<String> categoryList;
    @FXML
    private TextField pItems;
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
    private Button update;
    Connection con;

    public void setEditData(String name, String pname) {
        iName = name;
        pName = pname;

    }

    @FXML
    void handleAction(ActionEvent event) throws SQLException, IOException {
        con = MyConnection.getConnection();
        if (event.getSource() == selectUnit) {
            UnitController uc = new UnitController();
            uc.setUnits(a, b, c, d, e, f, g);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("unit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.show();
            check = true;
        }
        if (event.getSource() == update) {
            String query = "UPDATE `items` SET `Name`='" + itemName.getText() + "' ,`ComponentOf`='" + pItems.getText() + "',`MainCat`= '" + categoryList.getValue() + "',`ItemCode`= '" + code.getText() + "',"
                    + "`SalePrice`= '" + salePrice.getText() + "' ,`OpeningQty`= '" + qty.getText() + "' ,`MinStock`= '" + minStock.getText() + "' ,`pUnit`= '" + a + "' ,`pPrice`= '" + pPrice.getText() + "' ,`AtPrice`= '" + atPrice.getText() + "',"
                    + "`Date`= '" + date.getValue() + "' ,`sUnitOne`= '" + b + "' ,`sUnitTwo`= '" + c + "' ,`sUnitThree`= '" + d + "' ,`conversionOne`= '" + e + "' ,"
                    + "`conversionTwo`= '" + f + "' ,`conversionThree`= '" + g + "' where name = '" + itemName.getText() + "' and ComponentOf = '" + pItems.getText() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) update.getScene().getWindow();
            stage.close();
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

        itemName.setText(iName);
        pItems.setText(pName);
        Connection c;
        try {
            c = MyConnection.getConnection();

            String SQL = "SELECT * from items where name = '" + iName + "' and ComponentOf = '" + pName + "'";
            ResultSet rs = c.createStatement().executeQuery(SQL);

            while (rs.next()) {
                editItemList = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    editItemList.add(rs.getString(i));
                }
                System.out.println(editItemList);
            }

            categoryList.setValue((String) editItemList.get(2));
            code.setText((String) editItemList.get(3));
            salePrice.setText((String) editItemList.get(4));
            qty.setText((String) editItemList.get(5));
            minStock.setText((String) editItemList.get(6));

            a = (String) editItemList.get(7);

            pPrice.setText((String) editItemList.get(8));
            atPrice.setText((String) editItemList.get(9));

            String dt = (String) editItemList.get(10);
            System.out.println(dt);

            LocalDate localDate = LocalDate.parse(dt);
            date.setValue(localDate);

            b = (String) editItemList.get(11);
            this.c = (String) editItemList.get(12);
            d = (String) editItemList.get(13);
            e = (String) editItemList.get(14);
            f = (String) editItemList.get(15);
            g = (String) editItemList.get(16);

            s1.setText("1 " + a + "=" + e + " " + b + " (Default)");
            s2.setText("1 " + a + "=" + f + " " + this.c);
            s3.setText("1 " + a + "=" + g + " " + d);

            System.out.println(b);
            System.out.println(this.c);
            System.out.println(d);
            if (b.contains("NONE") || b == "") {
                s1.setText("");
            }
            if (this.c.contains("NONE") || this.c == "") {
                s2.setText("");
            }
            if (d.contains("NONE") || d == "") {
                s3.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

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

        if (b == "NONE" || b == "") {
            s1.setText("");

        } else {
            s1.setText("1 " + a + "=" + e + " " + b + " (Default)");
        }
        if (c == "NONE" || c == "") {
            s2.setText("");
        } else {
            s2.setText("1 " + a + "=" + f + " " + c);
        }
        if (d == "NONE" || d == "") {
            s3.setText("");
        } else {
            s3.setText("1 " + a + "=" + g + " " + d);
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

            if (b == "NONE" || b == "") {
                s1.setText("");

            } else {
                s1.setText("1 " + a + "=" + e + " " + b + " (Default)");
            }
            if (c == "NONE" || c == "") {
                s2.setText("");

            } else {
                s2.setText("1 " + a + "=" + f + " " + c);
            }
            if (d == "NONE" || d == "") {
                s3.setText("");
            } else {
                s3.setText("1 " + a + "=" + g + " " + d);
            }

            if (b == "NONE" || b == "") {
                e = "0";

            }

            if (e != "" && qty.getText() != "") {
                    double conv = eval(e);
                    double dummy = Double.parseDouble(qty.getText()) * conv;
                    double roundOff = (double) Math.round(dummy * 1000) / 1000;
                    qtyDef.setText(String.valueOf(roundOff) + " " + b);
            }
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
}
