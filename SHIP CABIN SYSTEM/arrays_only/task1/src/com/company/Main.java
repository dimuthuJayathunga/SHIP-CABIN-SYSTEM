package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/*--------------------- Ship passenger boarding menu---------------------------*/

public class Main {

    public static void main(String[] args) {

        String[] cruiseShip = new String[12];
        initialise(cruiseShip);
        while (true){
            cruiseMenu(cruiseShip);
        }

    }

    public static void initialise(String[] cruiseShip) {
        for(int i = 0; i < 12; i++){
            cruiseShip[i] = "e";
        }
    }

    public static void cruiseMenu(String[] cruiseShip){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=======================Boarding Menu=========================\n");

        System.out.println(" A : Add customer to cabin ");
        System.out.println(" V : View all cabins ");
        System.out.println(" E : Display Empty cabins ");
        System.out.println(" D : Delete customer from cabin ");
        System.out.println(" F : Find cabin from customer name ");
        System.out.println(" S : Store program data into file ");
        System.out.println(" L : Load program data from file ");
        System.out.println(" O : View passengers ordered alphabetically by name ");
        System.out.println(" EXT : Exit the Program ");
        System.out.print("\n============================================================\n");
        System.out.print("\nEnter your Input : ");

        String input = scanner.next().toLowerCase(Locale.ROOT);
        String userInput = scanner.nextLine();

        switch (input){
            case "a": addCustomer(cruiseShip);
                break;
            case "v" : viewCabins(cruiseShip);
                break;
            case "e" : emptyCabins(cruiseShip);
                break;
            case "d" : deleteCustomer(cruiseShip);
                break;
            case "f" : findCustomerCabin(cruiseShip);
                break;
            case "s" : storeData(cruiseShip);
                break;
            case "l" : loadData();
                break;
            case "o" : alphabeticalPassengerOrder(cruiseShip);
                break;
            case "ext" : ext(cruiseShip);
                break;
            default:
                System.out.println("Invalid input!!");
        }


    }

/*---------------------Add customer to cabin---------------------------*/

    private static void addCustomer(String[] cruiseShip) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try{
                System.out.println("Enter the cabin No :");
                int cabinNo = scanner.nextInt();
                if (0 <= cabinNo && cabinNo <= 12 ){
                    if (cabinNo == 12){
                        break;
                    }
                    if (cruiseShip[cabinNo].equals("e")){
                        System.out.println("Enter the customer name :");
                        String customerName = scanner.next();
                        cruiseShip[cabinNo] = customerName;
                    }
                    else {
                        System.out.println("A customer is already booked the cabin \n   Enter a another cabin number");
                    }
                }else {
                    System.out.println("Invalid input !!");
                }

            } catch (InputMismatchException x){
                System.out.println("Please enter an integer");
                break;
            }
        }


    }

    /*---------------------View all cabins---------------------------*/

    private static void viewCabins(String[] cruiseShip) {
        for (int i = 0; i < 12; i++) {
            if ( cruiseShip[i].equals("e")){
                System.out.println("Cabin " + i + " is empty");
            }else{
                System.out.println("Cabin " + i + ": " + cruiseShip[i]);
            }
        }
    }

    /*---------------------Display Empty cabins---------------------------*/

    private static void emptyCabins(String[] cruiseShip) {
        System.out.println("-----Empty cabins-----");
        for (int i = 0; i < 12; i++) {
            if (cruiseShip[i].equals("e")) {
                System.out.println("Cabin " + i);
            }
        }
    }

    /*---------------------Delete customer from cabin---------------------------*/

    private static void deleteCustomer(String[] cruiseShip) {
        Scanner scanner = new Scanner(System.in);
        boolean passengerExist = false;
        System.out.println("Enter customers name :");
        String userInput = scanner.next().toLowerCase(Locale.ROOT);
        for (int i = 0; i < 12; i++){
            if (cruiseShip[i].toLowerCase(Locale.ROOT).equals(userInput)){
                cruiseShip[i] = "e";
                passengerExist = true;
                System.out.println("Successfully deleted.");
                break;
            }
        }
        if (!passengerExist) {
            System.out.println("Check the passenger's name and try again.");
        }
    }

    /*---------------------Find cabin from customer name---------------------------*/

    private static void findCustomerCabin(String[] cruiseShip) {
        Scanner scanner = new Scanner(System.in);
        boolean existPassenger = false;
        System.out.println("Enter customers name :");
        String userInput = scanner.next().toLowerCase(Locale.ROOT);
        for (int i = 0; i < 12; i++){
            if (cruiseShip[i].toLowerCase(Locale.ROOT).equals(userInput)){
                existPassenger = true;
                System.out.println("Cabin number :" + i);
            }
        }
        if(!existPassenger){
            System.out.println("Check the passenger's name and try again.");
        }
    }

    /*---------------------Store program data into file---------------------------*/

    private static void storeData(String[] cruiseShip) {
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            for (int i = 0; i < 12; i++) {
                myWriter.write("Cabin : " + i + "    " + "Passengers Name : ");
                if(!cruiseShip[i].equals("e")) {
                    myWriter.write(cruiseShip[i]);
                } else {
                    myWriter.write("empty cabin");
                }
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Data successfully saved");
        } catch (IOException x ) {
            System.out.println("Error!!" + x );
        }
    }

    /*---------------------Load program data from file---------------------------*/

    private static void loadData() {
        try {
            File file = new File("Data.txt");
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                System.out.println(readFile.nextLine());
            }
            System.out.println("----------------------------------------------");
        } catch (IOException x) {
            System.out.println("Error! " + x );
        }
    }

    /*---------------------View passengers ordered alphabetically by name---------------------------*/

    private static void alphabeticalPassengerOrder(String[] cruiseShip) {
        String tempName = null;
        for (int i = 0; i < cruiseShip.length; i++) {
            for (int j = i+1 ; j < cruiseShip.length; j++) {
                if (cruiseShip[i].compareTo(cruiseShip[j]) > 0){
                    tempName = cruiseShip[i];
                    cruiseShip[i] = cruiseShip[j];
                    cruiseShip[j] = tempName;
                }
            }
        }
        System.out.println("The names in the alphabetical order are : ");
        for (String s : cruiseShip) {
            if (s.equals("e")) {
            } else {
                System.out.println(s + " ");
            }
        }

    }

    private static void ext(String[] cruiseShip) {
        System.out.println("\nThank you For Trying the Application!");

    }

}
