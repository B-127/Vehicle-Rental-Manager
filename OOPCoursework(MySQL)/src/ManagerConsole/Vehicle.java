package ManagerConsole;

import java.util.Scanner;

abstract class Vehicle {
    private String plateNo;
    private String make;
    private String model;
    private String yearOfRelease;
    private String fuelType;			 //Petrol,Diesel or Electric
    private String fuelConsumption;      //measured in Kilometers(KM) per Litre
    private String color;
    private String engineCapacity;          //measured in Cubic Centimeters (CC)
    private double vehicleRentingPrice;  //the rent per day for the vehicle
    private String vehicleAvailability;

    Scanner vecScn= new Scanner(System.in);
    //Class Constructors with the first one being the default constructor
    //and the second a constructor with all the instances.

    public Vehicle() {
        super();
    }                  //empty constructor for the vehicle class

    public Vehicle(String plateNo, String make, String model, String yearOfRelease, String fuelType,
                   String fuelConsumption, String color, String engineCapacity, double vehicleRentingPrice, String vehicleAvailability) {
        super();
        this.plateNo = plateNo;
        this.make = make;
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.fuelType = fuelType;
        this.fuelConsumption = fuelConsumption;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.vehicleRentingPrice = vehicleRentingPrice;
        this.vehicleAvailability=vehicleAvailability;
    }

    //The getter and setter methods for this class.
    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getVehicleRentingPrice() {
        return vehicleRentingPrice;
    }

    public void setVehicleRentingPrice(double vehicleRentingPrice) {
        this.vehicleRentingPrice = vehicleRentingPrice;
    }

    public String getVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(String vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNo='" + plateNo + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", fuelConsumption='" + fuelConsumption + '\'' +
                ", color='" + color + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", vehicleRentingPrice=" + vehicleRentingPrice +
                ", vehicleAvailability='" + vehicleAvailability + '\'' +
                '}';
    }

    //This method is used to get the vehicle details of the vehicles that are entered into the database

    public void addVehicleDetails() {
        System.out.print("Enter the vehicle plate no: ");
        setPlateNo(vecScn.nextLine());

        System.out.print("Enter the vehicle make: ");
        setMake(vecScn.nextLine());

        System.out.print("Enter the vehicle model: ");
        setModel(vecScn.nextLine());

        System.out.print("Enter the vehicle Year of Release : ");
        setYearOfRelease(vecScn.nextLine());

        System.out.print("Enter the vehicle Fuel Type (Petrol or Diesel): ");
        setFuelType(vecScn.nextLine());

        System.out.print("Enter the vehicle Fuel Consumption (KM/Litre): ");
        setFuelConsumption(vecScn.nextLine());

        System.out.print("Enter the vehicle colour: ");
        setColor(vecScn.nextLine());

        System.out.print("Enter the vehicle engine capacity (CC): ");
        setEngineCapacity(vecScn.nextLine());

        System.out.print("Enter the vehicle rental price (per day): ");
        setVehicleRentingPrice(vecScn.nextDouble());

        System.out.print("Vehicle Availability (Yes/No): ");
        setVehicleAvailability(vecScn.next().toLowerCase());
    }
}
