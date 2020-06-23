package customers.gui;

import com.sun.javafx.scene.control.behavior.TextAreaBehavior;
import common.Database;
import customers.Customer;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class CustomersScreenController implements Initializable {

    //Connection to the Database
    Connection conn;

    //FXML Elements
    @FXML
    private HBox hBox;
    @FXML
    private ChoiceBox searchOptions;
    @FXML
    private TableView<customers.Customer> customersTable;
    @FXML
    private TableColumn<customers.Customer, String> firstNameCol;
    @FXML
    private TableColumn<customers.Customer, String> lastNameCol;
    @FXML
    private TableColumn<customers.Customer, String> addressCol;
    @FXML
    private TableColumn<customers.Customer, String> postcodeCol;
    @FXML
    private TableColumn<customers.Customer, String> phoneNumberCol;
    @FXML
    private TableColumn<customers.Customer, String> emailAddressCol;
    @FXML
    private TextField searchField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField postcodeField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailAddressField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label tableInfo;

    private ObservableList<customers.Customer> data;
    final private String tInfo = "Number of rows:";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        conn = Database.getInstance().getConnection();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/customers/Test.db");
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
        }

        searchOptions.setItems(
                FXCollections.observableArrayList("First Name", "Last Name", new Separator(), "Vehicle Registration Number")
        );

        ArrayList<Button> buttonList = new ArrayList<>();
        buttonList.add(addButton);
        buttonList.add(deleteButton);
        buttonList.add(editButton);
        buttonList.add(searchButton);
        allButtonOnEnter(buttonList);

        customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        customersTable.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );

        firstNameCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("lastName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("address"));
        postcodeCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("postcode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("phoneNumber"));
        emailAddressCol.setCellValueFactory(new PropertyValueFactory<customers.Customer, String>("emailAddress"));

        data = FXCollections.observableArrayList();
        boolean dataLoaded = loadAllCustomers();

    }

    public boolean runStatement(String sqlStatement) {
        boolean executeSuccess = false;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int rowCountBefore = getRowsCustomerTable();
            stmt.execute(sqlStatement);
            int rowCountAfter = getRowsCustomerTable();
            if (rowCountBefore != rowCountAfter) {
                executeSuccess = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getClass() + ": " + ex.getMessage());
            }
        }
        return executeSuccess;
    }

    public ResultSet getResultSet(String sqlStatement) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlStatement);
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getClass() + ": " + ex.getMessage());
            }
        }
        return rs;
    }

    public int getRowsCustomerTable() {
        int numRows = -1;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rowCount = stmt.executeQuery("SELECT COUNT(*) FROM Customer;");
            numRows = rowCount.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
            return numRows;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getClass() + ": " + ex.getMessage());
            }
        }
        return numRows;
    }

    public boolean loadData(String sqlStatement) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
//            System.out.println(rs.next());
            data.clear();
            while (rs.next()) {
                data.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerFirstName"), rs.getString("CustomerLastName"), rs.getString("CustomerAddress"),
                        rs.getString("CustomerPostcode"), rs.getString("CustomerPhoneNo"), rs.getString("CustomerEmail")));
            }
            customersTable.setItems(data);
            tableInfo.setText(tInfo + " " + data.size());
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
            };
        }

    }

    public boolean loadAllCustomers() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER;");
            data.clear();
            while (rs.next()) {
                data.add(new Customer(rs.getString("CustomerID"), rs.getString("CustomerFirstName"), rs.getString("CustomerLastName"), rs.getString("CustomerAddress"),
                        rs.getString("CustomerPostcode"), rs.getString("CustomerPhoneNo"), rs.getString("CustomerEmail")));
            }
            customersTable.setItems(data);
            tableInfo.setText(tInfo + " " + data.size());
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getClass() + ": " + ex.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getClass() + ": " + ex.getMessage());
            }
        }
    }

    /**
     * **********************************
     * CLEAR THE TABLE OF ALL DATA ***********************************
     */
    public void clearTable() {
        data.clear();
        tableInfo.setText(tInfo + " " + data.size());
    }

    /**
     * **********************************
     * SEARCH FOR CUSTOMER(S) FROM THE DATABASE AND DISPLAY IN THE TABLE
     * ***********************************
     */
    public void search() {
        String searchString = searchField.getText();
        String searchBy = (String) searchOptions.getSelectionModel().getSelectedItem();

        // Check if selection was made on ChoiceBox
        if (searchBy == null) {
            showInformationBox("Select an option (from the dropdown) to search against.");
            return;
        } else if (searchString.equals("")) {
            showInformationBox("You must enter a search string before searching.");
            return;
        }

        String whereKey = "";
        int stringToUse = -1;
        switch (searchBy) {
            case "First Name":
                whereKey = "CustomerFirstName";
                stringToUse = 0;
                break;
            case "Last Name":
                whereKey = "CustomerLastName";
                stringToUse = 0;
                break;
            case "Vehicle Registration Number":
                whereKey = "VehicleRegNo";
                stringToUse = 1;
                break;
        }
        String sqlString = "Select * from Customer Where " + whereKey + " Like '%" + searchString + "%';";
        String sqlString2 = "Select Customer.*, Vehicle.VehicleRegNo FROM Customer LEFT JOIN Vehicle ON Customer.CustomerID=Vehicle.CustomerID"
                + " Where " + whereKey + " Like '%" + searchString + "%';";
        String[] sqlStrings = new String[]{sqlString, sqlString2};

        boolean searchSuccessfull = loadData(sqlStrings[stringToUse]);
        if (searchSuccessfull) {
            searchField.clear();
        }
    }

    /*
     * **********************************
     * ADD A CUSTOMER TO THE DATABASE USING INPUT FROM THE TEXT FIELDS
     * ***********************************
     */
    public boolean addCustomer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String postcode = postcodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        String emailAddress = emailAddressField.getText();

        if (firstName.equals("")
                || lastName.equals("")
                || address.equals("")
                || postcode.equals("")
                || phoneNumber.equals("")
                || emailAddress.equals("")) {
            showInformationBox("Ensure all required fields are filled in before attempting to add a new customer to the database.");
            return false;
        }

        int rows = getRowsCustomerTable() + 1;
        String rowNumber = Integer.toString(rows);
//        System.out.println(rowNumber);
        String sqlStatement = "INSERT INTO Customer (CustomerID,CustomerFirstName,CustomerLastName,CustomerAddress,CustomerPostcode,CustomerPhoneNo,CustomerEmail)"
                + " VALUES ('" + rowNumber + "','" + firstName + "','" + lastName + "','" + address + "','" + postcode + "','" + phoneNumber + "','" + emailAddress + "');";
        boolean added = runStatement(sqlStatement);
        if (!added) {
            System.out.println("Could not add Customer.");
            return false;
        } else {
            ArrayList<TextInputControl> textFields = new ArrayList<>();
            textFields.add(firstNameField);
            textFields.add(lastNameField);
            textFields.add(addressField);
            textFields.add(postcodeField);
            textFields.add(phoneNumberField);
            textFields.add(emailAddressField);
            for (TextInputControl textInput : textFields) {
                textInput.clear();
            }
            if (getRowsCustomerTable() == data.size() + 1) {
                loadAllCustomers();
            }
            return true;
        }
    }

    /*
     * ******************************************
     * EDIT AN EXISTING CUSTOMER IN THE DATABASE 
    *********************************************
     */
    public void editCustomer() {

    }

    /*
     * **********************************
     * DELETE CUSTOMER FROM THE DATABASE 
    ***********************************
     */
    public boolean deleteCustomer() {
        ObservableList<Customer> selectedCustomers = customersTable.getSelectionModel().getSelectedItems();
        if(selectedCustomers.size() == 0){
            return false;
        }
        boolean continueWithDelete;
        String message = "Are you sure you want to delete the customer: \n";
        
        String cFirstName = selectedCustomers.get(0).getFirstName();
        String cLastName = selectedCustomers.get(0).getLastName();
        
        continueWithDelete = showConfirmation(message+cFirstName+" "+cLastName+".");
        if(!continueWithDelete){
            return false;
        }
        
        for (Customer c : selectedCustomers) {
            int customerID = Integer.parseInt(c.getId());
            boolean deleted = runStatement("DELETE FROM Customer WHERE CustomerID='" + customerID + "';");
            if (!deleted) {
                System.out.println("Could not delete Customer with id " + c.getId() + ".");
                return false;
            }
        }
        loadAllCustomers();
        return true;
    }

    private void showDeleteConfirmation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteConfirmation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Delete Confirmation Required");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void allButtonOnEnter(ArrayList<Button> buttonList) {
        for (Iterator<Button> iterator = buttonList.iterator(); iterator.hasNext();) {
            final Button next = iterator.next();
            next.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        EventHandler eH = next.getOnAction();
                        eH.handle(ke);

                    }
                }
            });

        }
    }

    public void showInformationBox(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

        public boolean showConfirmation(String message) {
        boolean confirm = false;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        alert.getButtonTypes().setAll(yesButton,buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            // ... user chose Yes
            confirm = true;
            return confirm;
        } else {
            // ... user chose CANCEL or closed the dialog
            return confirm;
        }
    }
    

}
