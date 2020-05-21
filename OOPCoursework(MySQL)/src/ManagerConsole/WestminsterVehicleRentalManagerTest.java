package ManagerConsole;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

public class WestminsterVehicleRentalManagerTest {
    private ArrayList<Vehicle> testVecArrList= new ArrayList<>();
    private Vehicle testFourWheel= new FourWheelVehicles("1966","Suzuki","Alto","2006","Petrol","70","Purple","800",15.00,"yes","4","Manual","5");
    private Vehicle testTwoWheel= new TwoWheelVehicles("1977","Bajaj","Discover","2007","Petrol","82","Blue","125",10.00,"no","ElectricStart","Drum");

    @Test
    public void addVehicle() {
        testVecArrList.add(testFourWheel);
        testVecArrList.add(testTwoWheel);

        for (Vehicle vehicle:testVecArrList) {
            System.out.println(vehicle);
        }
    }
    @Test
    public void deleteVehicle() {
        testVecArrList.add(testFourWheel);
        testVecArrList.add(testTwoWheel);

        String plateNo="1977";

        for (Vehicle vehicle:testVecArrList) {
            if(vehicle.getPlateNo().equals(plateNo)){
                testVecArrList.remove(vehicle);
                break;
            }
        }
        System.out.println(testVecArrList);

    }
    @Test
    public void printList() {
        testVecArrList.add(testFourWheel);
        testVecArrList.add(testTwoWheel);

        for (Vehicle vehicle:testVecArrList) {
            System.out.println(vehicle);
        }
    }

    @Test
    public void update() {
        testVecArrList.add(testFourWheel);
        testVecArrList.add(testTwoWheel);

        String plateNo="1966";

        for (Vehicle vehicle:testVecArrList) {
            if(vehicle.getPlateNo().equals(plateNo)){
                //testVecArrList.set(12,""));
            }
        }
    }

    @Test
    public void generateReport() throws Exception {
        Console back= new Console();
        //getting the date right now so that it could be added into the report that is generated.
        GregorianCalendar dateRightNow= new GregorianCalendar();
        int dateNow= dateRightNow.get(Calendar.DATE);
        int monthNow= dateRightNow.get(Calendar.MONTH);
        int yearNow= dateRightNow.get(Calendar.YEAR);

        //statements to select all the data from the vehicleDb
        Statement addVec = WestminsterVehicleRentalManager.getDbConnection().createStatement();
        ResultSet result = addVec.executeQuery("select * from vehicleDb");

        //Creating a new file to write the report in.
        File file = new File("testVehicleList.txt");
        try(PrintWriter printWrite = new PrintWriter(new FileWriter(file, true), true)) {
            printWrite.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Westminster Vehicle Rental Manager Test Report "+dateNow+"/"+monthNow+"/"+yearNow+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printWrite.printf( " %-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s  %-10.10s%n","vecType","plateNo","make", "model","yearOfRelease","fuelType","fuelConsumption","color","engineCapacity","vehicleRentalPrice" ,"vehicleAvailability","noOfDoors" , "transmission" , "seatingCapacity" , "startType" ,"brakingType");
            while (result.next()) {
                String vecType = result.getObject(1).toString();
                String plateNo = result.getObject(2).toString();
                String make = result.getObject(3).toString();
                String model = result.getObject(4).toString();
                String yearOfRelease = result.getObject(5).toString();
                String fuelType = result.getObject(6).toString();
                String fuelConsumption = result.getObject(7).toString();
                String color = result.getObject(8).toString();
                String engineCapacity = result.getObject(9).toString();
                String vehicleRentalPrice = result.getObject(10).toString();
                String vehicleAvailability = result.getObject(11).toString();
                String noOfDoors = result.getObject(12).toString();
                String transmission = result.getObject(13).toString();
                String seatingCapacity = result.getObject(14).toString();
                String startType = result.getObject(15).toString();
                String brakingType = result.getObject(16).toString();

                printWrite.printf(" %-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s  %-10.10s%n", vecType, plateNo,make,model,yearOfRelease,fuelType,fuelConsumption,color,engineCapacity,vehicleRentalPrice,vehicleAvailability,noOfDoors,transmission,seatingCapacity,startType,brakingType);
                printWrite.println("");
                printWrite.println("");
                printWrite.println("");
            }}
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}