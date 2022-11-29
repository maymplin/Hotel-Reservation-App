import api.AdminResource;
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

        if(!validateCheckInNotBeforeToday(checkIn)) {
            System.out.println("Check-in date must be today or in the future.\n");
            return;
        }

        checkOut = getDate("Enter check-out date - mm/dd/yyyy. (E.g. 02/08/2020)");

        if (!validateCheckInBeforeCheckOut(checkIn, checkOut)) {
            System.out.println("Check-out date must be after check-in date.\n");
            return;
        }

        availableRooms = findAvailableRooms(checkIn, checkOut);

        // If no available rooms for the specified dates,
        if (availableRooms.isEmpty()) {
            availableRooms = checkAlternateDates(checkIn, checkOut, DAYSOUT);
        }

        if (!availableRooms.isEmpty()) {
            printAvailableRooms(availableRooms);
            Reservation newReservation = reserveARoom(availableRooms, checkIn, checkOut);

            if (newReservation != null) {
                System.out.println(newReservation);
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

    public boolean validateCheckInNotBeforeToday(Date dateIn) {
        Date yesterday = new Date();
        yesterday = setNewDate(yesterday, -1);

        return dateIn.after(yesterday);
    }

    // Verify that check-out date is after check-in date
    public boolean validateCheckInBeforeCheckOut(Date dateIn, Date dateOut) {
        return dateOut.after(dateIn);
    }

    // Find available rooms based on check-in/check-out dates
    public List<IRoom> findAvailableRooms(Date in, Date out) {

        return new ArrayList<>(HotelResource.findARoom(in, out));
    }

    public void printAvailableRooms(List<IRoom> availableRooms) {
        availableRooms.forEach(System.out::println);
        System.out.println();
    }

    public String formatDateString(Date date) {
        final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE. MMM dd, yyyy");
        return DATE_FORMAT.format(date);
    }

    public List<IRoom> checkAlternateDates(Date dateIn, Date dateOut, Integer daysToAdd) {
        System.out.println("There are no rooms available for the specified dates.\n");
        System.out.println("Checking for available rooms 7 days for your specified dates.");

        dateIn = setNewDate(dateIn, daysToAdd);
        dateOut = setNewDate(dateOut, daysToAdd);

        List<IRoom> availableRooms = findAvailableRooms(dateIn, dateOut);

        if (availableRooms.isEmpty()) {
            System.out.println("Unfortunately there are no available rooms for these dates, either.");
        } else {
            System.out.println("\nNew check-in date: " + formatDateString(dateIn) +
                    " / New check-out date: " + formatDateString(dateOut));
        }

        return availableRooms;
    }

    public Date setNewDate(Date date, Integer addDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addDays);
        return calendar.getTime();
    }

    public Reservation reserveARoom(List<IRoom> available, Date checkIn, Date checkOut) {

        String bookRoom;

        do {
            System.out.println("Would you like to book a room? Y/N");
            bookRoom = scanner.nextLine();
        } while (bookRoom == null || (bookRoom.equalsIgnoreCase("Y") &&
                bookRoom.equalsIgnoreCase("N")));

        if (bookRoom.equalsIgnoreCase("Y")) {
            String customerEmail = checkAccount();

            if (!customerEmail.isEmpty()) {
                String roomId = getRoomNumber(available);
                if (!roomId.isEmpty()) {
                    return HotelResource.bookARoom(customerEmail,
                            HotelResource.getRoom(roomId), checkIn, checkOut);
                }
            }
        }
        return null;
    }

    public String checkAccount() {
        do {
            System.out.println("Do you have an account with us? Y/N");
            String hasAccount = scanner.nextLine();

            if (hasAccount.equalsIgnoreCase("Y")) {
                System.out.println("Enter email: (Format: username@domain.com)");
                String accountEmail = scanner.nextLine();
                if (AdminResource.getCustomer(accountEmail) != null) {
                    return accountEmail;
                } else {
                    System.out.println("That account is not in our system.\n");
                }
            } else if (hasAccount.equalsIgnoreCase("N")) {
                System.out.println("Please return to Main Menu and create an account first.");
                return "";
            }
        } while (true);
    }

    public String getRoomNumber(List<IRoom> availableRooms) {
        do {
            System.out.println("Enter the room number that you would like to reserve: ");
            String roomNumber = scanner.nextLine();

            boolean isValidRoom = checkRoomAvailable(roomNumber, availableRooms);

            if (!isValidRoom) {
                System.out.println("The room number you entered is not available.\n");
                System.out.println("Would you like to reserve another room? Y/N");
                String reserveRoom = scanner.nextLine();

                if (reserveRoom.equalsIgnoreCase("N")) {
                    return "";
                } else if (!reserveRoom.equalsIgnoreCase(("Y"))) {
                    System.out.println("That is not a valid input.\n");
                }
            } else {
                return roomNumber;
            }
        } while (true);
    }

    public boolean checkRoomAvailable(String roomId, List<IRoom> availableRooms) {
        for (IRoom room : availableRooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return true;
            }
        }
        return false;
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


