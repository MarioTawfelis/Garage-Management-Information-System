/**
 * FXML Controller class
 *
 * @author Efthymios 
 */
package vehicles.gui;

import common.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vehicles.*;

public class VehiclesScreenController implements Initializable {

    @FXML
    private Button searchButton, addButton, editButton, updateButton, futureBookingsButton, pastBookingsButton, partsButton;
    
    @FXML
    private ChoiceBox searchOpt;
    
    @FXML
    private TableView<vehicles.Vehicle> vehicleTable;
    @FXML
    private TableColumn<vehicles.Vehicle, String> colRegNum;
    @FXML 
    private TableColumn<vehicles.Vehicle, String> colModel;
    @FXML
    private TableColumn<vehicles.Vehicle, String> colLastService;
    @FXML
    private TableColumn<customers.Customer, String> colName;
    @FXML
    private TableColumn<customers.Customer, String> colLastName;
    @FXML
    private TableColumn<diagrep.Diagrep,String> colNextBookingDate;
    @FXML
    private TableColumn<diagrep.Diagrep, String> colBookingType;
    
    private Database db;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
	db = Database.getInstance();
        searchOpt.setItems(FXCollections.observableArrayList("Vehicle Reg. Number", new Separator(), "Vehicle Manufacturer"));
        searchOpt.getSelectionModel().selectFirst();
        
        colRegNum.setCellValueFactory(new PropertyValueFactory<vehicles.Vehicle, String>("registrationNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<vehicles.Vehicle, String>("model"));
        colLastService.setCellValueFactory(new PropertyValueFactory<vehicles.Vehicle, String>("lastServiceDate"));
         
        
        colName.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("FirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("LastName"));
        
        colNextBookingDate.setCellValueFactory(new PropertyValueFactory<diagrep.Diagrep, String>("nextBooking"));
        colBookingType.setCellValueFactory(new PropertyValueFactory<diagrep.Diagrep, String>("bookingType"));
        
       // outputTableData(null);
        
    }
    public void outputTableData(String SQLquery){
        
        
        
    }
    
    @FXML
    public void handleSearch(){
       //TODO 
   }

   
    
    @FXML
    public void handleAdd() throws ClassNotFoundException, SQLException, IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddVehicle.fxml"));     
        Parent root = (Parent) fxmlLoader.load(); 
	Scene scene = new Scene(root);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.centerOnScreen();
	stage.setTitle("Add new Vehicle");
	stage.show();
        
        
    }
    @FXML
    public void handeParts() throws ClassNotFoundException, SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("VehicleParts.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Vehicle Parts");
        stage.show();
        
    }
    @FXML
    public void handlePastBookings() throws ClassNotFoundException, SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("VehiclePastBookings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Vehicle Past Bookings");
        stage.show();
        
    }
    @FXML
    public void handleFutureBookings() throws ClassNotFoundException, SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FutureVehicleBookings.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Future Vehicle Bookings");
        stage.show();
        
    }
     @FXML
    public void handleVehicleDetails() throws ClassNotFoundException, SQLException, IOException{
        Parent root = FXMLLoader.load(getClass().getResource("VehicleDetail.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Future Vehicle Bookings");
        stage.show();
        
    }
}
