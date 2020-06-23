/**
 * FXML Controller class
 *
 * @author Nexus
 */
package specialist.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SpecialistScreenController implements Initializable {

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
        System.out.println("Specialist Repairs working");
    }
    
}
