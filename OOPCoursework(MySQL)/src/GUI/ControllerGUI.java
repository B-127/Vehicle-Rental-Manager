package GUI;

import ManagerConsole.Schedule;
import ManagerConsole.WestminsterVehicleRentalManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import static ManagerConsole.WestminsterVehicleRentalManager.getDbConnection;

public class ControllerGUI implements Initializable {

    @FXML
    private TableView<GUIVehicle> vecTable;

    @FXML
    private TableColumn<GUIVehicle,String> col_vecType;

    @FXML
    private TableColumn<GUIVehicle,String> col_plateNo;

    @FXML
    private TableColumn<GUIVehicle,String> col_make;

    @FXML
    private TableColumn<GUIVehicle,String> col_model;

    @FXML
    private TableColumn<GUIVehicle,String> col_yearOfRelease;

    @FXML
    private TableColumn<GUIVehicle,String> col_fuelType;

    @FXML
    private TableColumn<GUIVehicle,String> col_fuelConsumption;

    @FXML
    private TableColumn<GUIVehicle,String> col_color;

    @FXML
    private TableColumn<GUIVehicle,String> col_engineCapacity;

    @FXML
    private TableColumn<GUIVehicle,String> col_rentalPrice;

    @FXML
    private TableColumn<GUIVehicle,String> col_availability;

    @FXML
    private TableColumn<GUIVehicle,String> col_noOfDoors;

    @FXML
    private TableColumn<GUIVehicle,String> col_transmission;

    @FXML
    private TableColumn<GUIVehicle,String> col_seatingCapacity;

    @FXML
    private TableColumn<GUIVehicle,String> col_startType;

    @FXML
    private TableColumn<GUIVehicle,String> col_brakeType;

    private ObservableList <GUIVehicle> vecObList;
    private GUIdbConnector GUIdb;

    @FXML
    private TextField book_plateNo;

    @FXML
    private TextField book_fullName;

    @FXML
    private TextField book_nic;

    @FXML
    private TextField book_contact;

    @FXML
    private DatePicker book_start;

    @FXML
    private DatePicker book_end;

    @FXML
    private Button book_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Displaying the database in the TableView once the GUI is initialized.
        GUIdb= new GUIdbConnector();
            try {
                Connection GUIconn= GUIdb.getDbConnection();
                vecObList=FXCollections.observableArrayList();                                              //loading the database into an observable arraylist before loading it into the table view.
                ResultSet GUIrs= GUIconn.createStatement().executeQuery("Select * from vehicleDb");

                while (GUIrs.next()){
                    vecObList.add(new GUIVehicle(GUIrs.getString(1),GUIrs.getString(2),GUIrs.getString(3),GUIrs.getString(4),GUIrs.getString(5),GUIrs.getString(6),
                            GUIrs.getString(7), GUIrs.getString(8),GUIrs.getString(9), GUIrs.getString(10),GUIrs.getString(11),GUIrs.getString(12),
                            GUIrs.getString(13),GUIrs.getString(14),GUIrs.getString(15),GUIrs.getString(16)));
                }
            } catch (Exception ex) {
               System.err.println("Error"+ex);
            }
            col_vecType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
            col_plateNo.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
            col_make.setCellValueFactory(new PropertyValueFactory<>("make"));
            col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_yearOfRelease.setCellValueFactory(new PropertyValueFactory<>("yearOfRelease"));
            col_fuelType.setCellValueFactory(new PropertyValueFactory<>("fuelType"));
            col_fuelConsumption.setCellValueFactory(new PropertyValueFactory<>("fuelConsumption"));
            col_color.setCellValueFactory(new PropertyValueFactory<>("color"));
            col_engineCapacity.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
            col_rentalPrice.setCellValueFactory(new PropertyValueFactory<>("vehicleRentalPrice"));
            col_availability.setCellValueFactory(new PropertyValueFactory<>("vehicleAvailability"));
            col_noOfDoors.setCellValueFactory(new PropertyValueFactory<>("noOfDoors"));
            col_transmission.setCellValueFactory(new PropertyValueFactory<>("transmission"));
            col_seatingCapacity.setCellValueFactory(new PropertyValueFactory<>("seatingCapacity"));
            col_startType.setCellValueFactory(new PropertyValueFactory<>("startType"));
            col_brakeType.setCellValueFactory(new PropertyValueFactory<>("brakingType"));

            vecTable.setItems(vecObList);
    }

    //This method is to show all the vehicles in the database if the filters have been used before
    public void all()throws Exception{
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));

            vecTable.setItems(vecObList);
        }
    }

    //filters the vehicles over 1000cc
    public void over1000cc() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where engineCapacity>1000");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filters the vehicles under 1000cc
    public void under1000cc() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where engineCapacity<1000");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }

        vecTable.setItems(vecObList);
    }

    //filtering the vehicles according to the ascending year of release. showing the most recently released vehicles first.
    public void ascYearOfRel() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb order by yearOfRelease ASC");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }

        vecTable.setItems(vecObList);
    }

    //filtering the vehicles according to the descending year of release. showing the most oldest released vehicles first.
    public void descYearOfRel() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb order by yearOfRelease DESC");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filtering the vehicles and displaying those which run on petrol
    public void petrol() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where fuelType='petrol'");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filtering the vehicles and displaying those which run on diesel
    public void diesel() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where fuelType='diesel'");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filtering vehicles which run on manual transmission.
    public void manual() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where transmission='manual'");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filtering vehicles which run on automatic transmission.
    public void automatic() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where transmission='automatic'");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //filtering the vehicles according to those which are available right now
    public void VecAvailable() throws Exception {
        Connection filConn= getDbConnection();
        vecObList=FXCollections.observableArrayList();
        ResultSet filrs= filConn.createStatement().executeQuery("Select * from vehicleDb where vehicleAvailability='yes'");

        while (filrs.next()){
            vecObList.add(new GUIVehicle(filrs.getString(1),filrs.getString(2),filrs.getString(3),filrs.getString(4),filrs.getString(5),filrs.getString(6),
                    filrs.getString(7), filrs.getString(8),filrs.getString(9), filrs.getString(10),filrs.getString(11),filrs.getString(12),
                    filrs.getString(13),filrs.getString(14),filrs.getString(15),filrs.getString(16)));
        }
        vecTable.setItems(vecObList);
    }

    //this method is load and display the customer receipt once the "Book Vehicle" button is pressed.
    public void bookReceipt(javafx.event.ActionEvent actionEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("receipt.fxml"));
        Parent vectableViewParent = loader.load();

        Scene vectableViewScene = new Scene(vectableViewParent);

        //The statements below are used to check if the vehicle chosen is available for renting.
        Statement availabilityCheck = GUIdbConnector.getDbConnection().createStatement();
        ResultSet vecAvailability = availabilityCheck.executeQuery("SELECT * from vehicleDb WHERE (`plateNo` = '" + book_plateNo.getText() + "') AND (vehicleAvailability='yes')");

        if (vecAvailability.next()){
            receiptController controller = loader.getController();                      //loads the controller of the fxml file.

            controller.passVecData(book_plateNo.getText());                             //passed data to the plate number label of the next scene

            controller.passCusData(book_fullName.getText(),book_nic.getText(),book_contact.getText());  //passed data to the customer name,NIC and contact number labels of the next scene

            LocalDate start = book_start.getValue();
            LocalDate end= book_end.getValue();
            controller.passBookData(start,end);                                         //passing the dates picked by the user from the date picker to the start and end date label in the next scene.

            controller.passPrice(book_plateNo.getText());                               //passing the vehicle plate number so that calculations on the price can be done.
            controller.availabilityStatus(book_plateNo.getText());                      //passing the vehicle plate number so that the availability status in the database can be updated.
        }
        //accessing the controller and call the methods

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(vectableViewScene);                                             //setting and displaying the customer receipt scene
        window.show();
    }
}
