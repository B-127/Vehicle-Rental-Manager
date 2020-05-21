package GUI;

import ManagerConsole.Schedule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class receiptController implements Initializable {

    @FXML
    private Label platNo_input;

    @FXML
    private Label cusName_input;

    @FXML
    private Label cusNIC_input;

    @FXML
    private Label cusCon_input;

    @FXML
    private Label cusStart_input;

    @FXML
    private Label cusEnd_input;

    @FXML
    private Label price;

    //creating a new object for schedule for the calculations.
    ManagerConsole.Schedule bookSchedule= new Schedule();

    //A new file which maintains the customer list report if formed.
    File cusFile= new File("customerList.txt");
    PrintWriter customerListPrintWriter;

    //the section which adds the date to the customer report
    GregorianCalendar dateRightNow= new GregorianCalendar();
    int dateNow= dateRightNow.get(Calendar.DATE);
    int monthNow= dateRightNow.get(Calendar.MONTH);
    int yearNow= dateRightNow.get(Calendar.YEAR);

    //setting up the writer.
    {
        try {
            customerListPrintWriter = new PrintWriter(new FileWriter(cusFile, true),true);
            customerListPrintWriter.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~Westminster Vehicle Rental Manager Customer List "+dateNow+"/"+monthNow+"/"+yearNow+" ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void passVecData(String vehicleNo) throws Exception {
        platNo_input.setText(vehicleNo);                                                //setting the plate no label according to the data passed from the first scene
        customerListPrintWriter.println("Vehicle Plate No:"+vehicleNo);
    }

    public void passCusData(String customerName,String customerNIC, String customerContact) {
        cusName_input.setText(customerName);                                            //setting the customer name label according to the data passed from the first scene
        cusNIC_input.setText(customerNIC);                                              //setting the customer NIC label according to the data passed from the first scene
        cusCon_input.setText(customerContact);                                          //setting the customer contact numberlabel according to the data passed from the first scene
        customerListPrintWriter.println("Customer Name:"+customerName+"\nCustomer National ID:"+customerNIC+"\nCustomer Contact No:"+customerContact);              //printing the data gained into the customerList.txt file
    }

    public void passBookData(LocalDate startSchedule, LocalDate endSchedule){
        cusStart_input.setText(String.valueOf(startSchedule));                          //setting the start date label according to the data passed from the first scene
        cusEnd_input.setText(String.valueOf(endSchedule));                              //setting the end date label according to the data passed from the first scene
        bookSchedule.setPickup(startSchedule);                                          //passing the information into the schedule class so that the calculation of the span can be done.
        bookSchedule.setDropOff(endSchedule);
        customerListPrintWriter.println("Start Date:"+startSchedule+"\nEnd Date:"+endSchedule);                    //printing the data gained into the customerList.txt file
    }

    //the method that calculates the price of the booking.
    public void passPrice(String vehicleNo) throws Exception {
        int duration= bookSchedule.calculateSpan();

        //The statements below are to get the vehicle rental price matching to the vehicle plate number given.
        Statement priceCheck = GUIdbConnector.getDbConnection().createStatement();
        ResultSet priceSelect = priceCheck.executeQuery("SELECT vehicleRentalPrice from vehicleDb where plateNo='" + vehicleNo + "'");

        while(priceSelect.next()){
            double rentalPrice= (double) priceSelect.getObject("vehicleRentalPrice");               //getting the vehicle rental price of the vehicle by passing it as an object
            double bookingPrice= duration*rentalPrice;

            price.setText(String.valueOf(bookingPrice));
        }
    }

    //the method which updates the vehicle availability once it has been booked.
    public void availabilityStatus(String vehicleNo) throws Exception{
        Statement availabilityCheck = GUIdbConnector.getDbConnection().createStatement();
        String availabilityEdit = "UPDATE vehicleDb SET `vehicleAvailability` = 'No' WHERE `plateNo` = '" + vehicleNo + "'";
        availabilityCheck.executeUpdate(availabilityEdit);
    }

    //the method invoked by the button "Go back to main menu" to return to the main menu of the interface
    public void backMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent backVectableViewParent = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
        Scene backVectableViewScene = new Scene(backVectableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(backVectableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

