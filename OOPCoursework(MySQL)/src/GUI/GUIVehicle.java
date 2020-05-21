package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GUIVehicle {
    private StringProperty vehicleType;
    private StringProperty plateNo;
    private StringProperty make;
    private StringProperty model;
    private StringProperty yearOfRelease;
    private StringProperty fuelType;
    private StringProperty fuelConsumption;
    private StringProperty color;
    private StringProperty engineCapacity;
    private StringProperty vehicleRentalPrice;
    private StringProperty vehicleAvailability;
    private StringProperty noOfDoors;
    private StringProperty transmission;
    private StringProperty seatingCapacity;
    private StringProperty startType;
    private StringProperty brakingType;

    public GUIVehicle(){
        super();
    }

    public GUIVehicle(String vehicleType, String plateNo, String make, String model, String yearOfRelease, String fuelType, String fuelConsumption, String color, String engineCapacity, String vehicleRentalPrice, String vehicleAvailability, String noOfDoors, String transmission, String seatingCapacity, String startType, String brakingType) {
        this.vehicleType = new SimpleStringProperty(vehicleType);
        this.plateNo = new SimpleStringProperty(plateNo);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.yearOfRelease = new SimpleStringProperty(yearOfRelease);
        this.fuelType = new SimpleStringProperty(fuelType);
        this.fuelConsumption = new SimpleStringProperty(fuelConsumption);
        this.color = new SimpleStringProperty(color);
        this.engineCapacity = new SimpleStringProperty(engineCapacity);
        this.vehicleRentalPrice = new SimpleStringProperty(vehicleRentalPrice);
        this.vehicleAvailability = new SimpleStringProperty(vehicleAvailability);
        this.noOfDoors = new SimpleStringProperty(noOfDoors);
        this.transmission = new SimpleStringProperty(transmission);
        this.seatingCapacity = new SimpleStringProperty(seatingCapacity);
        this.startType = new SimpleStringProperty(startType);
        this.brakingType = new SimpleStringProperty(brakingType);
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public StringProperty vehicleTypeProperty() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType.set(vehicleType);
    }

    public String getPlateNo() {
        return plateNo.get();
    }

    public StringProperty plateNoProperty() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo.set(plateNo);
    }

    public String getMake() {
        return make.get();
    }

    public StringProperty makeProperty() {
        return make;
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getYearOfRelease() {
        return yearOfRelease.get();
    }

    public StringProperty yearOfReleaseProperty() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease.set(yearOfRelease);
    }

    public String getFuelType() {
        return fuelType.get();
    }

    public StringProperty fuelTypeProperty() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType.set(fuelType);
    }

    public String getFuelConsumption() {
        return fuelConsumption.get();
    }

    public StringProperty fuelConsumptionProperty() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption.set(fuelConsumption);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getEngineCapacity() {
        return engineCapacity.get();
    }

    public StringProperty engineCapacityProperty() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity.set(engineCapacity);
    }

    public String getVehicleRentalPrice() {
        return vehicleRentalPrice.get();
    }

    public StringProperty vehicleRentalPriceProperty() {
        return vehicleRentalPrice;
    }

    public void setVehicleRentalPrice(String vehicleRentalPrice) {
        this.vehicleRentalPrice.set(vehicleRentalPrice);
    }

    public String getVehicleAvailability() {
        return vehicleAvailability.get();
    }

    public StringProperty vehicleAvailabilityProperty() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(String vehicleAvailability) {
        this.vehicleAvailability.set(vehicleAvailability);
    }

    public String getNoOfDoors() {
        return noOfDoors.get();
    }

    public StringProperty noOfDoorsProperty() {
        return noOfDoors;
    }

    public void setNoOfDoors(String noOfDoors) {
        this.noOfDoors.set(noOfDoors);
    }

    public String getTransmission() {
        return transmission.get();
    }

    public StringProperty transmissionProperty() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission.set(transmission);
    }

    public String getSeatingCapacity() {
        return seatingCapacity.get();
    }

    public StringProperty seatingCapacityProperty() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity) {
        this.seatingCapacity.set(seatingCapacity);
    }

    public String getStartType() {
        return startType.get();
    }

    public StringProperty startTypeProperty() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType.set(startType);
    }

    public String getBrakingType() {
        return brakingType.get();
    }

    public StringProperty brakingTypeProperty() {
        return brakingType;
    }

    public void setBrakingType(String brakingType) {
        this.brakingType.set(brakingType);
    }
}
