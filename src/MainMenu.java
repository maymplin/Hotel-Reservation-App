import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                case "1" -> findAndReserveARoom();
                case "2" -> printCustomerReservations();
                case "3" -> createAnAccount();
                case "4" -> getAdminMenu();
            }

        } while (!userChoice.equals("5"));

        scanner.close();
    }

//    Option 1: Find and reserve a room ----------------------------------------

    // TODO complete find and reserve a room functionality

    public void findAndReserveARoom() {

        Date checkIn;
        Date checkOut;
        final Integer DAYSOUT = 7;

        List<IRoom> availableRooms;

        // Prompt for check-in/checkout dates and print all available rooms
        checkIn = getDate("Enter check-in date - mm/dd/yyyy. (E.g. 02/01/2020)");
        checkOut = getDate("Enter check-out date - mm/dd/yyyy. (E.g. 02/08/2020)");

        if (!validateCheckInCheckOut(checkIn, checkOut)) {
            System.out.println("Check-out date must be after check-in date.");
        }

        availableRooms = findAvailableRooms(checkIn, checkOut);

        // If no available rooms for the specified dates,
        if (!availableRooms.isEmpty()) {
            printAvailableRooms(availableRooms);
        } else {
            System.out.println("There are no rooms available for the specified dates.\n");
            System.out.println("Checking for available rooms 7 days for your specified dates.");
            checkIn = setNewDate(checkIn, DAYSOUT);
            checkOut = setNewDate(checkOut, DAYSOUT);
            availableRooms = findAvailableRooms(checkIn, checkOut);
            if (availableRooms.isEmpty()) {
                System.out.println("Unfortunately there are no available rooms for these dates, either.");
            } else {
                System.out.println("\nNew check-in date: " + formatDateString(checkIn) +
                        " / New check-out date: " + formatDateString(checkOut));
                System.out.println("Availabe Rooms:");
                printAvailableRooms(availableRooms);
            }
        }

        System.out.println();
    }

    public Date getDate(String message) {
        String userInput;

        Date date = null;

        do {
            System.out.println(message);
            userInput = scanner.nextLine();
            try {
                date = parseDateString(userInput);
            } catch (ParseException ex) {
                System.out.println("Invalid input. Please enter a valid date.\n");
            }
        } while (date == null);

        return date;
    }

    public Date parseDateString(String inputDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.parse(inputDate);
    }

    // Verify that check-out date is after check-in date
    public boolean validateCheckInCheckOut(Date dateIn, Date dateOut) {
        return dateOut.after(dateIn);
    }


    // Find available rooms based on check-in/check-out dates
    public List<IRoom> findAvailableRooms(Date in, Date out) {

        return new ArrayList<>(HotelResource.findARoom(in, out));
    }

    public Date setNewDate(Date date, Integer addDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addDays);
        return calendar.getTime();
    }

    public String formatDateString(Date date) {
        final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE. MMM dd, yyyy");
        return DATE_FORMAT.format(date);
    }

    public void printAvailableRooms(List<IRoom> availableRooms) {
        availableRooms.forEach(System.out::println);
    }

//    Option 2: See my reservations --------------------------------------------

    public void printCustomerReservations() {

        String customerEmail;

        System.out.println("Please enter your account email:");
        customerEmail = scanner.nextLine();

        if (HotelResource.getCustomer(customerEmail) == null) {
            System.out.println("You do not have an account with us.");
        } else {
            List<Reservation> reservations = new ArrayList<>(HotelResource.getCustomersReservations(customerEmail));

            if (reservations.isEmpty()) {
                System.out.println("You do not have any reservations with us.");
            } else {
                reservations.forEach(System.out::println);
            }
        }

        System.out.println();
    }

//    Option 3: Create an account ----------------------------------------------

    public void createAnAccount() {

        String email;
        String firstName;
        String lastName;

        email = getAccountInput("Enter email (username@domain.com):");
        firstName = getAccountInput("First name:");
        lastName = getAccountInput("Last name:");
        HotelResource.createACustomer(email, firstName, lastName);

        System.out.println();
    }

    public String getAccountInput(String msg) {

        String userInput;

        do {
            System.out.println(msg);
            userInput = scanner.nextLine();

            if (userInput != null && !userInput.isEmpty()) {
                break;
            } else {
                System.out.println("This field cannot be empty.");
            }
        } while(true);

        return userInput;
    }

//    Option 4: Admin ----------------------------------------------------------

    public void getAdminMenu() {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.enterAdminMenu();
    }
}


