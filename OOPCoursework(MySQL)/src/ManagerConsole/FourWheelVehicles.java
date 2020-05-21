package ManagerConsole;

import java.util.Scanner;

public class FourWheelVehicles extends Vehicle{
    private String noOfDoors;
    private String transmission;
    private String seatingCapacity;

    Scanner carScn= new Scanner(System.in);
    //Class Constructors with the first one being the default constructor
    //and the second a constructor with all the instances.
    public FourWheelVehicles() {
        super();
    }

    public FourWheelVehicles(String plateNo, String make, String model, String yearOfRelease, String fuelType, String fuelConsumption, String color, String engineCapacity, double vehicleRentalPrice, String vehicleAvailability,String noOfDoors, String transmission, String seatingCapacity) {
        super(plateNo, make, model, yearOfRelease, fuelType, fuelConsumption, color, engineCapacity, vehicleRentalPrice, vehicleAvailability);
        this.noOfDoors = noOfDoors;
        this.transmission = transmission;
        this.seatingCapacity = seatingCapacity;
    }

    public FourWheelVehicles( String noOfDoors, String transmission, String seatingCapacity) {
        super();
    }

    //The getter and setter methods for this class.
    public String getNoOfDoors() {
        return noOfDoors;
    }

    public void setNoOfDoors(String noOfDoors) {
        this.noOfDoors = noOfDoors;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return "FourWheelVehicles{" +
                "noOfDoors='" + noOfDoors + '\'' +
                ", transmission='" + transmission + '\'' +
                ", seatingCapacity='" + seatingCapacity + '\'' +
                '}';
    }

    //the method to add details which are specific to those of four wheel vehicles.

    public void addFourWheelVehicleDetails() {

        addVehicleDetails();

        System.out.print("Enter the no of doors: ");
        setNoOfDoors(carScn.nextLine());

        System.out.print("Enter the Transmission: ");
        setTransmission(carScn.nextLine());

        System.out.print("Enter the Seating Capacity: ");
        setSeatingCapacity(carScn.nextLine());
    }
}
