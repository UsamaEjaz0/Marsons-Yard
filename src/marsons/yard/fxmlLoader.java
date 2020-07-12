/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsons.yard;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author uejaz
 */
public class fxmlLoader {
    private Pane pane;
    
    public Pane getPage(String filename) throws IOException{
        URL fileUrl = MarsonsYard.class.getResource(filename + ".fxml");
        pane = new FXMLLoader().load(fileUrl);
        return pane;
    }
    
}
