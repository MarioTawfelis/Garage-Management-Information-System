/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ilyas
 */
public class DeleteConfirmationController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    boolean delete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void yesButtonClicked() {
        System.out.println("YES CLICKED");
    }

    public void noButtonClicked() {
        System.out.println("NO CLICKED");
    }

}
