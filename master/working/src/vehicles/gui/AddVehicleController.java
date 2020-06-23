/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.gui;

import common.Database;
import customers.Customer;
import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import vehicles.Vehicle;
import java.util.regex.Matcher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EfthymiosChatziathanasiadis
 */
public class AddVehicleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    private String company="";
    
    private String address="";
    
    private String expDate="";
    
    @FXML
    private TextField warantyCompany;
    @FXML
    private TextField warantyAddress;
    @FXML
    private TextField warantyExpDate;
    @FXML
    private ComboBox<String> customerDropDownList;
    @FXML
    private ComboBox<String> vehicleTemplateDropDownList;
    @FXML
    private ComboBox<String> vehicleTypeDropDownList;
    @FXML
    private TextField model;
    @FXML
    private TextField regNumberField;
    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField engineSizeField;
    @FXML
    private TextField fuelTypeField;
    @FXML
    private TextField colourField;
    @FXML
    private TextField MoTField;
    @FXML
    private TextField mileage;
    @FXML
    private Button addVehicleField;
    
    private ObservableList<String> vehicleTemplate;
    
    private ArrayList<vehicles.Vehicle> vehicles;
    
    private ObservableList<String> customers;
    
    private ArrayList<customers.Customer> customerss;

    
    private Database db;
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        db=db.getInstance();
        
        //Load observable array lists(customers, vehicleTemplate)
        vehicleTypeDropDownList.setItems(FXCollections.observableArrayList("Car","Van","Truck"));
        vehicleTypeDropDownList.getSelectionModel().selectFirst();
        
        System.out.println("type loaded");
        
        String getTemplateQuery="SELECT * FROM vehicle_template;";
        
        ResultSet TemplateRs=db.query(getTemplateQuery);
        
        vehicleTemplate=FXCollections.observableArrayList();
        vehicles= new ArrayList<>();
        try {
            int count=0;
            while(TemplateRs.next()){
                Vehicle vh=new Vehicle(TemplateRs.getString("Type"),"",TemplateRs.getString("template_make"), TemplateRs.getString("template_model"), TemplateRs.getString("template_engine_size"), TemplateRs.getString("template_fuel_type"), "", "","","");
                vehicleTemplate.add(""+count+": "+vh.getMake()+" "+vh.getModel());
                vehicles.add(vh);
                System.out.println(count);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println("template loaded locally");

        vehicleTemplateDropDownList.setItems(vehicleTemplate);
        //vehicleTemplateDropDownList.getSelectionModel().selectFirst();
        vehicleTemplateDropDownList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    
                     int vehicleIndex;
                     Matcher matcher = Pattern.compile("\\d+").matcher(newValue);//vehicleTemplateDropDownList.getSelectionModel().getSelectedItem();
                     matcher.find();
                     vehicleIndex = Integer.valueOf(matcher.group());
                     Vehicle veh=vehicles.get(vehicleIndex);
                     //set text fields from default template
                     vehicleTypeDropDownList.setValue(veh.getVehicleType());//setSelectedItem(T value)
                     manufacturerField.setText(veh.getMake());
                     model.setText(veh.getModel());
                     engineSizeField.setText(veh.getEngSize());
                     fuelTypeField.setText(veh.getFuel());
                        
        }});

          
        customers=FXCollections.observableArrayList();
        customerss=new ArrayList<>();
        String getCustomersQuery="SELECT CustomerID, CustomerFirstName, CustomerLastName FROM Customer;";
        ResultSet customersRs=db.query(getCustomersQuery);
        try {
            int count=0;
            while(customersRs.next()){
                Customer cs=new Customer(customersRs.getString("CustomerID"), customersRs.getString("CustomerFirstName"), customersRs.getString("CustomerLastName"), "","","","");
                customers.add(count+": "+cs.getFirstName()+" "+cs.getLastName());
                customerss.add(cs);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customerDropDownList.setItems(customers);
       // customerDropDownList.getSelectionModel().selectFirst();
        
    } 
    @FXML
    public void addVehicle(){
      
        String mod=model.getText();
        String regNumber=regNumberField.getText();
        String manufacturer=manufacturerField.getText();
        String engineSize=engineSizeField.getText();
        String fuelType=fuelTypeField.getText();
        String colour=colourField.getText();
        String MoT=MoTField.getText();
        String milage=mileage.getText();
       
        String customerSelection=customerDropDownList.getSelectionModel().getSelectedItem();
        String typeSelection=vehicleTypeDropDownList.getSelectionModel().getSelectedItem();
        
        //if(customerDropDownList.getSelectionModel())
        if(customerDropDownList.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a customer");
            return ;
        }
        if(vehicleTypeDropDownList.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a vehicle type");
            return ;
        }
        if(
                mod.equals("")||
                regNumber.equals("") || 
                manufacturer.equals("") || 
                engineSize.equals("") || 
                fuelType.equals("") || 
                colour.equals("") || 
                MoT.equals("") ||
                milage.equals("") ){
            
            JOptionPane.showMessageDialog(null, "Please fill in all details");
            return ;
            
        }
        int customerID;
        String insertVehicle, insertWaranty;
        Matcher matcher = Pattern.compile("\\d+").matcher(customerSelection);
        matcher.find();
        customerID = Integer.valueOf(matcher.group());
        Customer cust=customerss.get(customerID);
        
        if(company.equals("")){
            
             insertVehicle="INSERT INTO Vehicle (VehicleType, VehicleRegNo, VehicleModel, VehicleMake,VehicleMileage, VehicleEngSize, VehicleFuelType, VehicleColour, VehicleMoTDate, CustomerID) VALUES("
             +regNumber+","+mod+","+manufacturer+","+engineSize+","+milage+","+fuelType+","+colour+","+MoT+","+cust.getId()+ ")";
             db.update(insertVehicle);
             
        }else{
            
            
            int WarantyID = getNumberOfRowsTable("WarantyCompany") + 1;
            
            
            insertWaranty="INSERT INTO WarantyCompany VALUES("+company+","+address+","+expDate+","+WarantyID+")";
            insertVehicle="INSERT INTO Vehicle (VehicleRegNo, VehicleModel, VehicleMake,VehicleMileage, VehicleEngSize, VehicleFuelType, VehicleFuelType, VehicleColour, VehicleMoTDate,WarrantyID, CustomerID)VALUES("
            +regNumber+","+mod+","+manufacturer+","+engineSize+","+fuelType+","+colour+","+MoT+","+WarantyID+","+cust.getId()+ ")";
            db.update(insertWaranty);
            db.update(insertVehicle);
        }
       
           // int  ans = JOptionPane.showConfirmDialog(null, "Does the vehicle have warantly?", "Add Vehicle", 0);
           
      JOptionPane.showMessageDialog(null,"Vehicle added to DB successfully");  
      return ;  
    }
    @FXML
    public void addWaranty(){
         
         company=warantyCompany.getText();
         address=warantyAddress.getText();
         expDate=warantyExpDate.getText();
         
        if(company.equals("") || address.equals("") || expDate.equals("")){
            
            JOptionPane.showMessageDialog(null, "Please fill in the provided text fields");
            return ;
            
        }
         
         
         return ;
        
    }
    private int getNumberOfRowsTable(String table){
        String query="SELECT COUNT(*) FROM "+table+";";
        ResultSet rowCount=db.query(query);
        try {
            return rowCount.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AddVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    @FXML
    public void handleWarranty() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vehicles/gui/addWarantyScreen.fxml"));     
        Parent root = (Parent) fxmlLoader.load(); 
	Scene scene = new Scene(root);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.centerOnScreen();
	stage.setTitle("Waranty");
	stage.show();
        
        
    }

   
    
    
    
}
