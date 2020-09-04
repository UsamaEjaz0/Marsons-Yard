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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author uejaz
 */
public class EditChequeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static String receipt;
    
     private ObservableList<ObservableList> data;
        @FXML
    private Button prev;
    @FXML
    private TextField cNum;

    @FXML
    private TextField bank;

    @FXML
    private TextField title;

    @FXML
    private TextField amount;

    @FXML
    private DatePicker dueDate;

    @FXML
    private Button next;

    @FXML
    private Button update;

    @FXML
    private Label pageNum;
    
    int num =1;
    
    void setData(String rec){
        this.receipt = rec;
    }
    @FXML
    void handleAction(ActionEvent event) throws SQLException {
        Connection con;
        if (event.getSource() == update ){
            con = MyConnection.getConnection();
            String query = "UPDATE `cheque` SET `Bank`='"+bank.getText()+"',`Title`='"+title.getText()+"',`Amount`='"+amount.getText()+"',`DueDate`='"+dueDate.getValue()+"' "
                    + "WHERE Receipt = '"+receipt+"' and ChequeNum = '"+cNum.getText()+"'";
            Statement st = con.createStatement();
            st.executeUpdate(query);
            con.close();
            Stage stage = (Stage) update.getScene().getWindow();
            stage.close();
        }
        else if(event.getSource() == next){
            if(num< data.size()){
                num++;
                pageNum.setText(String.valueOf(num));
                setChequeDetails(num-1);
                
            }
            
            
            
        }
        else if(event.getSource() == prev){
            if(num > 1){
                num--;
                pageNum.setText(String.valueOf(num));
                setChequeDetails(num-1);
                
            }
            
            
            
        }
    }
        public void getCheques() {
        
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = MyConnection.getConnection();
            String SQL  = "SELECT * from cheque where Receipt = '"+ receipt +"'";
            System.out.println(SQL);
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                

            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }

                data.add(row);

            }
           System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pageNum.setText("1");
        
        
            getCheques();
            setChequeDetails(Integer.parseInt(pageNum.getText())-1);
        
        
    }  
    
    void setChequeDetails( int id){
        cNum.setText(String.valueOf(data.get(id).get(0)));
        bank.setText(String.valueOf(data.get(id).get(2)));
        title.setText(String.valueOf(data.get(id).get(3)));
        amount.setText(String.valueOf(data.get(id).get(4)));
        String date = String.valueOf(data.get(id).get(5));
        String[] arr = date.split("-");
         System.out.println(date);
         System.out.println(arr[0]);
         System.out.println(arr[1]);
         System.out.println(arr[2]);
        LocalDate ld = LocalDate.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
        dueDate.setValue(ld);
        
    }
              
    }
    

