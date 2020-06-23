
package common;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class LoginScreenController implements Initializable {

	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label errorMessage;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
	
	
	@FXML
	public void login (ActionEvent event)
	{
		Database db = Database.getInstance();
		ResultSet resultset = db.query("SELECT * FROM User WHERE UserID=\'"+userField.getText()+"\' AND UserPassword=\'"+passwordField.getText()+"\'");
		try
		{
			if (!resultset.isBeforeFirst())	//isBeforeFirst returns false if no rows in resultset
			{
				errorMessage.setText("Invalid user/password");
			}
			else
			{
				
				Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
				Scene scene = new Scene(root);
				Stage stage = Main.stage;
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.setTitle("Main Menu");
				stage.show();
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
