package ManagerConsole;

import java.sql.*;
import java.util.*;
import java.io.*;

public class WestminsterVehicleRentalManager implements RentalVehicleManager {
    ArrayList<Vehicle> vecArrList= new ArrayList<Vehicle>();

    public static Connection getDbConnection() throws Exception{                //This method connects the MySql Database.
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/oopcwdb";                 //oopcwdb is the name of my database.
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch(Exception e){System.out.println(e);}
        return null;
    }

    //This method is to create the table in the database oopcwdb if the table doesn't exist beforehand.
    public static void createDbTable() throws Exception{
        try{
            Connection con = getDbConnection();
            PreparedStatement createVehicleTable = con.prepareStatement("CREATE TABLE IF NOT EXISTS vehicleDb(vehicleType VARCHAR(20), plateNo VARCHAR(20), make VARCHAR(20), model VARCHAR(20), yearOfRelease INT, fuelType VARCHAR(20), fuelConsumption DOUBLE, color VARCHAR(20), engineCapacity INT, vehicleRentalPrice DOUBLE, noOfDoors INT, transmission VARCHAR(20), seatingCapacity INT, startType VARCHAR(20), brakingType VARCHAR(20), vehicleAvailability VARCHAR(20))");
            createVehicleTable.executeUpdate();

        }catch(Exception e){System.out.println(e);}
        finally{
            System.out.println("The DB has been checked.");
        }
    }

    @Override
    public void addVehicle() throws Exception {
        Console back= new Console();
        Connection connec= getDbConnection();
        createDbTable();
        Statement parkingCheck = connec.createStatement();
        ResultSet result = parkingCheck.executeQuery("select count(*) from vehicleDb");

        //this count is to make sure that only up to 50 vehicles can be entered into the system.
        int count = 0;
        while (result.next()) {
            count++;
        }
        if (count<50) {
            Vehicle fourWheelVec = new FourWheelVehicles();
            Vehicle twoWheelVec = new TwoWheelVehicles();
            System.out.print("Enter type of Vehicle, (Car, Van, SUV, Bus, Motorbike or Scooter)::");

            Scanner addVecScn = new Scanner(System.in);

            String vecType = addVecScn.nextLine().toLowerCase();

            do {
                if (!vecType.equals("car") && !vecType.equals("motorbike") && !vecType.equals("scooter") && !vecType.equals("suv") && !vecType.equals("van") && !vecType.equals("bus")) {
                    System.out.print("Invalid Input! Please enter Car, Van, SUV, Bus, Motorbike or Scooter: ");

                    vecType = addVecScn.nextLine().toLowerCase();
                }
            } while (!vecType.equals("car") && !vecType.equals("motorbike")&& !vecType.equals("scooter")&& !vecType.equals("suv") && !vecType.equals("van") && !vecType.equals("bus"));

            if (vecType.equals("car") || vecType.equals("van") || vecType.equals("suv")|| vecType.equals("bus"))  {
                System.out.println("You have chosen to enter a "+vecType);
                ((FourWheelVehicles) fourWheelVec).addFourWheelVehicleDetails();
                vecArrList.add(fourWheelVec);

                try {
                    Connection con = getDbConnection();
                    PreparedStatement carInsert = con.prepareStatement("INSERT INTO vehicleDb VALUES ('"+vecType+"','" + fourWheelVec.getPlateNo() + "','" + fourWheelVec.getMake() + "','" + fourWheelVec.getModel() + "','" + fourWheelVec.getYearOfRelease() + "','" + fourWheelVec.getFuelType() + "','" + fourWheelVec.getFuelConsumption() + "','" + fourWheelVec.getColor() + "','" + fourWheelVec.getEngineCapacity() + "','" + fourWheelVec.getVehicleRentingPrice()+ "','" + fourWheelVec.getVehicleAvailability() +"','"+ ((FourWheelVehicles) fourWheelVec).getNoOfDoors() + "','" + ((FourWheelVehicles) fourWheelVec).getTransmission() + "','" + ((FourWheelVehicles) fourWheelVec).getSeatingCapacity() + "','" +null+"' ,'" +null+ "')");

                    carInsert.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    System.out.println("Insert Completed.");
                    System.out.print("Do you wish to enter another vehicle?::");
                    String yesOrNo= back.yesOrNo();
                    if (yesOrNo.equals("y")){addVehicle();}
                    else{back.menu();}
                }
            } else {
                System.out.println("You have chosen to enter a "+vecType);
                ((TwoWheelVehicles) twoWheelVec).addTwoWheelVehicleDetails();
                vecArrList.add(twoWheelVec);

                try {
                    Connection con = getDbConnection();
                    PreparedStatement bikeInsert = con.prepareStatement("INSERT INTO vehicleDb VALUES ('"+vecType+"','" + twoWheelVec.getPlateNo() + "','" + twoWheelVec.getMake() + "','" + twoWheelVec.getModel() + "','" + twoWheelVec.getYearOfRelease() + "','" + twoWheelVec.getFuelType() + "','" + twoWheelVec.getFuelConsumption() + "','" + twoWheelVec.getColor() + "','" + twoWheelVec.getEngineCapacity() + "','" + twoWheelVec.getVehicleRentingPrice() +"','" + twoWheelVec.getVehicleAvailability() +"','" + 0 + "','" +null+ "','" +0+"','" + ((TwoWheelVehicles) twoWheelVec).getStartType() + "','" + ((TwoWheelVehicles) twoWheelVec).getBrakingType() + "')");

                    bikeInsert.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    System.out.println("Insert Completed.");
                    //allows the user to enter another vehicle if needed, without having to return to the main menu
                    System.out.print("Do you wish to enter another vehicle?::");
                    String yesOrNo= back.yesOrNo();
                    if (yesOrNo.equals("y")){addVehicle();}
                    else{back.menu();}
                }
            }
        }
        else {
            System.out.println("The vehicle parking spots are all full!");
            back.menu();
        }
    }
    @Override
    public void deleteVehicle() throws Exception {
        Console back= new Console();
        Scanner delScanner= new Scanner(System.in);
        System.out.print("Please enter the plate number of the vehicle you want to delete::");
        String delVec= delScanner.nextLine().toLowerCase();

        //the statements below are used to select the vehicles in the database where the plate no is similar to the one entered by the user.
        Statement parkingCheck = getDbConnection().createStatement();
        ResultSet delSelect = parkingCheck.executeQuery("SELECT * from vehicleDb where plateNo='"+delVec+"'");

        if (delSelect.next()) {
            String deleteRow = "DELETE from vehicleDb where plateNo='" + delVec + "'";
            parkingCheck.executeUpdate(deleteRow);
            System.out.println("This Vehicle has successfully been deleted from the database!" +
                    "" +
                    "");
            System.out.print("Do you wish to delete another vehicle?::");

            String yesOrNo= back.yesOrNo();
            if (yesOrNo.equals("y")){deleteVehicle();}
            else{back.menu();}
        }
        else{
            System.out.println("This vehicle doesn't exist in the database!");
            //allowing the user to delete another vehicle without having to returning to the main menu again.
            System.out.print("Do you wish to try entering another plate number?::");

            String yesOrNo= back.yesOrNo();
            if (yesOrNo.equals("y")){deleteVehicle();}
            else{back.menu();}
        }
    }

    @Override
    public void printList() throws Exception {
        Console back= new Console();
        //the spacing between the output has been given below before the names of the columns and the data
        System.out.printf( " %-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s  %-10.10s%n","vecType","plateNo","make", "model","yearOfRelease","fuelType","fuelConsumption","color","engineCapacity","vehicleRentalPrice" ,"vehicleAvailability","noOfDoors" , "transmission" , "seatingCapacity" , "startType" ,"brakingType");

        //These statements are to order the list of vehicles alphabetically
        Statement parkingCheck = getDbConnection().createStatement();
        ResultSet result = parkingCheck.executeQuery("select * from vehicleDb order by make");

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

            System.out.printf( " %-10.10s  %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s  %-10.10s%n", vecType, plateNo,make,model,yearOfRelease,fuelType,fuelConsumption,color,engineCapacity,vehicleRentalPrice,vehicleAvailability,noOfDoors,transmission,seatingCapacity,startType,brakingType);
        }
        System.out.println("" +
                "" +
                "" );
        back.menu();
    }

    @Override
    public void update() throws Exception {
        Console back= new Console();
        Scanner updateScn = new Scanner(System.in);
        System.out.print("Please enter the plate number of the vehicle you wish to edit::");
        String updatePlateNo = updateScn.nextLine();

        //these statements are to select the vehicle which is being brought up for editing.
        Statement updateCheck = getDbConnection().createStatement();
        ResultSet updateSelect = updateCheck.executeQuery("SELECT * from vehicleDb where plateNo='" + updatePlateNo + "'");

        //asking the user if they want to change the rental price or the availability
        if (updateSelect.next()) {
            System.out.print("Please select the vehicle detail you wish to change according to the corresponding number\n" +
                    "1.Vehicle Rental Price\n" +
                    "2.Vehicle Availability\n" +
                    ":::");

            while (!updateScn.hasNextInt()) {
                System.out.print("Invalid Input Type! Please enter a value either 1 or 2: ");
                updateScn.nextInt();
            }
            int updateSelectNo = updateScn.nextInt();

            do {
                if (updateSelectNo > 2 || updateSelectNo < 1) {
                    System.out.print("Invalid Range! Please enter a value either 1 or 2: ");

                    while (!updateScn.hasNextInt()) {
                        System.out.print("Invalid Input Type! Please enter a value either 1 or 2: ");
                        updateScn.nextInt();
                    }
                    updateSelectNo = updateScn.nextInt();
                    break;
                }
            } while (updateSelectNo > 2 || updateSelectNo < 1);

            switch (updateSelectNo) {
                case 1: {
                    //editing the vehicle rental price
                    System.out.print("Please enter the new Vehicle Rental Price::");
                    Double VRPSave = updateScn.nextDouble();

                    String VRPEdit = "UPDATE vehicleDb SET `vehicleRentalPrice` = '" + VRPSave + "' WHERE `plateNo` = '" + updatePlateNo + "'";
                    updateCheck.executeUpdate(VRPEdit);
                    System.out.println("The Vehicle Rental Price has been successfully updated!");
                    break;
                }
                case 2: {
                    //editing the vehicle availability
                    System.out.print("Please enter the Vehicle Availability::");
                    String VASave = updateScn.next();

                    String VAEdit = "UPDATE vehicleDb SET `vehicleAvailability` = '" + VASave + "' WHERE `plateNo` = '" + updatePlateNo + "'";
                    updateCheck.executeUpdate(VAEdit);
                    System.out.println("The Vehicle Availability has been successfully updated!");
                    break;
                }
            }
            //asking user if they want to edit another vehicle.
            System.out.print("Do you wish to edit another vehicle?::");

            String yesOrNo= back.yesOrNo();
            if (yesOrNo.equals("y")){update();}
            else{back.menu();}
        }
    }
    @Override
    public void generateReport() throws Exception {
        Console back= new Console();
        //getting the date right now so that it could be added into the report that is generated.
        GregorianCalendar dateRightNow= new GregorianCalendar();
        int dateNow= dateRightNow.get(Calendar.DATE);
        int monthNow= dateRightNow.get(Calendar.MONTH);
        int yearNow= dateRightNow.get(Calendar.YEAR);

        //statements to select all the data from the vehicleDb
        Statement addVec = getDbConnection().createStatement();
        ResultSet result = addVec.executeQuery("select * from vehicleDb");

        //Creating a new file to write the report in.
        File file = new File("vehicleList.txt");
        try(PrintWriter printWrite = new PrintWriter(new FileWriter(file, true), true)) {
            printWrite.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Westminster Vehicle Rental Manager Report "+dateNow+"/"+monthNow+"/"+yearNow+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
            catch (IOException e) {
                e.printStackTrace();
            }
        back.menu();
       }
}







