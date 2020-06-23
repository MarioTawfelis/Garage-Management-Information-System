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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ilyas
 */
public class DialogBoxController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button closeButton;
    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void closeButtonClicked() {
//        System.out.println("CLOSE CLICKED");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setMessage(String message){
        messageLabel.setText(message);
    }
}
