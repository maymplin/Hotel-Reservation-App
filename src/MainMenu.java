import model.Customer;
import service.CustomerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public MainMenu() {

    }

    public void printMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application\n");
        printDivider();
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        printDivider();
        System.out.println("Please select a number for the menu option");
    }

    public void printDivider() {
        System.out.println("---------------------------------------------");
    }

    public void enterMainMenu() {

        String userChoice = "";

        do {
            printMainMenu();
            if (scanner.hasNext())
                userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    findAndReserveARoom();
                    break;
                case "2":
                    break;
                case "3":
                    createAnAccount();
                    break;
                case "4":
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.enterAdminMenu();
                    break;
                default:
            }

        } while (!userChoice.equals("5"));

        scanner.close();
    }

//    Option 1: Find and reserve a room ----------------------------------------

    // TODO complete find and reserve a room functionality

    public void findAndReserveARoom() {

        final Scanner s = new Scanner(System.in);

        Boolean isValidInput = false;
        Date checkIn = null;
        Date checkOut = null;

        checkIn = getDate("Enter checkIn Date mm/dd/yyyy. (E.g. 02/01/2020)");
        checkOut = getDate("Enter checkOut Date mm/dd/yyyy. (E.g. 02/08/2020)");
        System.out.println(checkOut);
    }

    public Date parseDateString(String inputDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.parse(inputDate);
    }

    public Date getDate(String message) {
        Scanner s = new Scanner(System.in);
        String userInput;

        Date date = null;

        do {
            System.out.println(message);
            userInput = s.nextLine();
            try {
                date = parseDateString(userInput);
            } catch (ParseException ex) {
                System.out.println("Invalid input. Please enter a valid date.\n");
            }
        } while (date == null);

        return date;
    }

//    Option 3: Create an account ----------------------------------------------

//    TODO debug email validation
    public void createAnAccount() {

        String email;
        String firstName;
        String lastName;

        email = getAccountInput("Enter email (username@domain.com):");
        firstName = getAccountInput("First name:");
        lastName = getAccountInput("Last name:");

        try {
            CustomerService.addCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException ex) {
            ex.getLocalizedMessage();
        }

        System.out.println();
    }

    public String getAccountInput(String msg) {

        String userInput = null;

        do {
            System.out.println(msg);
            userInput = scanner.nextLine();
        } while(userInput == null);

        return userInput;
    }
}


