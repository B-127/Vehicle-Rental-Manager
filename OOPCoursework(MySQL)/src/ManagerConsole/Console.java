package ManagerConsole;

import GUI.LaunchGUI;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Console {
    WestminsterVehicleRentalManager wvrm= new WestminsterVehicleRentalManager();             //object of the class westminsterVehicleRentalManager is created to allow this console to access the methods present.

    //This is the menu method which is displayed to the user upon choosing the manager open in the first menu of the console.
    public void menu() throws Exception {
        Scanner menusc= new Scanner(System.in);
        System.out.println("WESTMINSTER VEHICLE RENTAL SERVICE MENU");
        System.out.println("            1.Add Vehicle");
        System.out.println("           2.Delete Vehicle");
        System.out.println("       3.Print List of Vehicles");
        System.out.println("           4.Update List");
        System.out.println("          5.Generate Report");
        System.out.println("               6.Exit");
        System.out.print("Enter your choice::");

        while (!menusc.hasNextInt()) {
            System.out.print("Invalid Input Type! Please enter a value between 1-6: ");
            menusc.nextInt();
        }
        int choice= menusc.nextInt();

        do{
            if (choice>6 || choice<1) {
                System.out.print("Invalid Range! Please enter a value between 1-6: ");

                while (!menusc.hasNextInt()) {
                    System.out.print("Invalid Input Type! Please enter a value between 1-6: ");
                    menusc.nextInt();
                }
                choice= menusc.nextInt();
                break;
            }
        }while (choice>6 || choice<1);

        switch(choice) {
            case 1:
            {
                System.out.println("Add Vehicle");
                wvrm.addVehicle();
                break;
            }
            case 2:
            {
                System.out.println("Delete Vehicle");
                wvrm.deleteVehicle();
                break;
            }
            case 3:
            {
                System.out.println("Printing List of Vehicles");
                wvrm.printList();
                break;
            }
            case 4:
            {
                System.out.println("Update List");
                wvrm.update();
                break;
            }
            case 5:
            {
                System.out.println("The report has been generated.");
                wvrm.generateReport();
                break;
            }
            case 6:
            {
                System.out.println("6.Exit");
                System.out.print("You have chosen to exit the system! Do you wish to continue? (Y/N) :::");
                String yesOrNo= yesOrNo();
                if (yesOrNo.equals("y")) {System.exit(0);}
                else if (yesOrNo.equals("n")) {menu();}
                break;
            }
        }
    }

    //This method is used to validate the user input when a scenario where the user has to confirm yes or no in the system.
    public String yesOrNo(){
        Scanner yN= new Scanner(System.in);
        String yesNo= yN.nextLine().toLowerCase();
        do{
            if (!yesNo.equals("y") && !yesNo.equals("n")) {
                System.out.print("Invalid Input! Please enter either Y or N: ");

                while (!yN.hasNext()) {
                    System.out.print("Invalid Input! Please enter either Y or N: ");
                    yN.nextLine().toLowerCase();
                }
                yesNo= yN.next().toLowerCase();
                break;
            }
        }while (!yesNo.equals("y") && !yesNo.equals("n"));
        return yesNo;
    }

    public static void main(String[] args) throws Exception {
        String greetingMessage="Welcome to the Westminster Vehicle Rental Service!";  //greeting message when anyone accesses the program

        //The below code is to provide the greeting message along with the appropriate greeting for time of the day.
        GregorianCalendar timeRightNow= new GregorianCalendar();
        int hourNow= timeRightNow.get(Calendar.HOUR_OF_DAY);

        if (hourNow<12) {
            System.out.println("Good Morning!\n" + greetingMessage);
        }
        else if (hourNow>12 && hourNow<18) {
            System.out.println("Good Afternoon!\n" + greetingMessage);
        }
        else {
            System.out.println("Good Evening!\n" + greetingMessage);
        }

        //The code below is for the user to select if he/she is a manager or a customer.
        System.out.println("Please enter the number corresponding to your title:");
        System.out.println("1. Customer");
        System.out.println("2. Manager");
        System.out.println("3. Exit System");
        System.out.print(":::");

        Scanner scn= new Scanner (System.in);
        while (!scn.hasNextInt()) {
            System.out.print("Invalid Input! Please enter a value between 1 and 3: ");
            scn.next();
        }
        int titleNo= scn.nextInt();

        do{
            if (titleNo>3 || titleNo<1) {
                System.out.print("Invalid Range! Please enter a value between 1 and 3: ");

                while (!scn.hasNextInt()) {
                    System.out.print("Invalid Input! Please enter a value between 1 and 3: ");
                    scn.nextInt();
                }
                titleNo= scn.nextInt();
                break;
            }
        }while (titleNo>3 || titleNo<1);

        if(titleNo==1){
            javafx.application.Application.launch(LaunchGUI.class);                     //if the user is a customer and selects option one this code opens up the Graphical User interface.
        }

        //This section is a login which ensures the person accessing is the manager by asking the manager for the password.
        if (titleNo==2) {
            int managerPassword= 1990;
            Scanner passwordScn= new Scanner(System.in);
            System.out.print("Please enter the password:");


            while (!passwordScn.hasNextInt()) {
                System.out.print("Invalid Password Type! Please Re-enter: ");
                passwordScn.nextInt();
            }
            int passwordEntered= passwordScn.nextInt();

            do{
                if (passwordEntered!=managerPassword) {
                    System.out.print("Password Incorrect! Please Re-enter: ");

                    while (!passwordScn.hasNextInt()) {
                        System.out.print("Invalid Password Type! Please Re-enter: ");
                        passwordScn.nextInt();

                    }
                    passwordEntered=passwordScn.nextInt();
                    break;
                }
            }while (passwordEntered!=managerPassword);

            if (passwordEntered==managerPassword)
            {
                System.out.println("Login Successful");
                Console obj= new Console();
                obj.menu();
            }
            passwordScn.close();
        }
        if(titleNo==3){
            System.exit(0);                                         //to allow the user to exit the system from the first screen.
        }
        scn.close();
    }
}
