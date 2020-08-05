/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard.addItem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class UnitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList units = FXCollections.observableArrayList();
    
    
    @FXML
    private Button save;
    
    @FXML
    private ComboBox<String> sUnit3TextField;

    @FXML
    private Text bUnitText3;

    @FXML
    private TextField conv3;

    @FXML
    private Text sUnitText3;

    @FXML
    private ComboBox<String> bUnitTextField;

    @FXML
    private ComboBox<String> sUnit1TextField;

    @FXML
    private Text bUnitText1;

    @FXML
    private TextField conv1;

    @FXML
    private Text sUnitText1;

    @FXML
    private ComboBox<String> sUnit2TextField;

    @FXML
    private Text bUnitText2;

    @FXML
    private TextField conv2;

    @FXML
    private Text sUnitText2;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        units.addAll("NONE", "TONNES", "BAGS", "BOTTLES", "BOXES");

        bUnitTextField.getItems().addAll(units);
        sUnit1TextField.getItems().addAll(units);
        sUnit2TextField.getItems().addAll(units);
        sUnit3TextField.getItems().addAll(units);
        
        bUnitTextField.setValue("NONE");
        sUnit1TextField.setValue("NONE");
        sUnit2TextField.setValue("NONE");
        sUnit3TextField.setValue("NONE");
        
        bUnitText1.setText("NONE");
        bUnitText2.setText("NONE");
        bUnitText3.setText("NONE");
        sUnitText1.setText("NONE");
        sUnitText2.setText("NONE");
        sUnitText3.setText("NONE");

        bUnitTextField.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                bUnitText1.setText("1 "+bUnitTextField.getValue());
                bUnitText2.setText("1 "+bUnitTextField.getValue());
                bUnitText3.setText("1 "+bUnitTextField.getValue());
                sUnitText1.setText("1 "+sUnit1TextField.getValue());
                sUnitText2.setText("1 "+sUnit2TextField.getValue());
                sUnitText3.setText("1 "+sUnit3TextField.getValue());
            }
        });
        sUnit1TextField.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                 bUnitText1.setText("1 "+bUnitTextField.getValue());
                bUnitText2.setText("1 "+bUnitTextField.getValue());
                bUnitText3.setText("1 "+bUnitTextField.getValue());
                sUnitText1.setText("1 "+sUnit1TextField.getValue());
                sUnitText2.setText("1 "+sUnit2TextField.getValue());
                sUnitText3.setText("1 "+sUnit3TextField.getValue());
            }
        });
        sUnit2TextField.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                bUnitText1.setText("1 "+bUnitTextField.getValue());
                bUnitText2.setText("1 "+bUnitTextField.getValue());
                bUnitText3.setText("1 "+bUnitTextField.getValue());
                sUnitText1.setText("1 "+sUnit1TextField.getValue());
                sUnitText2.setText("1 "+sUnit2TextField.getValue());
                sUnitText3.setText("1 "+sUnit3TextField.getValue());
            }
        });
        sUnit3TextField.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                bUnitText1.setText("1 "+bUnitTextField.getValue());
                bUnitText2.setText("1 "+bUnitTextField.getValue());
                bUnitText3.setText("1 "+bUnitTextField.getValue());
                sUnitText1.setText("1 "+sUnit1TextField.getValue());
                sUnitText2.setText("1 "+sUnit2TextField.getValue());
                sUnitText3.setText("1 "+sUnit3TextField.getValue());
            }
        });

    }
    public String getBaseUnit(){
        return bUnitTextField.getValue();
    } 
    public String getSUnit1(){
        return sUnit1TextField.getValue();
    } 
    public String getSUnit2(){
        return sUnit2TextField.getValue();
    } 
    public String getSUnit3(){
        return sUnit3TextField.getValue();
    } 
    @FXML
    void handleAction(ActionEvent event) {
        AddItemScreenController a = new AddItemScreenController() ;
        a.setData(bUnitTextField.getValue(),sUnit1TextField.getValue(), sUnit2TextField.getValue()
        ,sUnit3TextField.getValue(), conv1.getText(), conv2.getText(), conv3.getText());
        
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
}
