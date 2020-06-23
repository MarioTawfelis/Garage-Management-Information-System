/**
 * FXML Controller class
 *
 * @author Nexus
 */
package parts.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PartsScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Test();
    }    
    
    @FXML
    public void Test(){
        System.out.println("Parts working");
    }
}
