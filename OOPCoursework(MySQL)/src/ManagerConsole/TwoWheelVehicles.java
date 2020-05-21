package ManagerConsole;

import java.util.Scanner;

public class TwoWheelVehicles extends Vehicle{
    private String startType;    //Kick-start or Electric-start
    private String brakingType;  //Disc or Drum Brakes

    //Class Constructors with the first one being the default constructor
    //and the second a constructor with all the instances.
    Scanner motorbikeScn= new Scanner(System.in);

    public TwoWheelVehicles() {
        super();
    }

    public TwoWheelVehicles(String plateNo, String make, String model, String yearOfRelease, String fuelType, String fuelConsumption, String color, String engineCapacity, double vehicleRentalPrice, String vehicleAvailability, String startType, String brakingType) {
        super(plateNo, make, model, yearOfRelease, fuelType, fuelConsumption, color,  engineCapacity, vehicleRentalPrice, vehicleAvailability);
        this.startType = startType;
        this.brakingType = brakingType;
    }

    //The getter and setter methods for this class.

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }

    public String getBrakingType() {
        return brakingType;
    }

    public void setBrakingType(String brakingType) {
        this.brakingType = brakingType;
    }

    @Override
    public String toString() {
        return "TwoWheelVehicles{" +
                "startType='" + startType + '\'' +
                ", brakingType='" + brakingType + '\'' +
                '}';
    }

    //this method is used to add the details which are specific to two wheel vehicles.

    public void addTwoWheelVehicleDetails() {

        addVehicleDetails();

        System.out.print("Enter the Start Type (Electric-Start or Kick-Start): ");
        setStartType(motorbikeScn.nextLine());

        System.out.print("Enter the Braking Type (Disc or Drum): ");
        setBrakingType(motorbikeScn.nextLine());
    }
}
