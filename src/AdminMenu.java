import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    static final Scanner sc = new Scanner(System.in);

    public AdminMenu() {
    }

    public void enterAdminMenu() {

        String adminChoice = "";

        do {
            printAdminMenu();

            if (sc.hasNext()) {
                adminChoice = sc.nextLine();
                adminChoice = adminChoice.replaceAll("\\s", "");
            }
            switch (adminChoice) {
                case "1":
                    printAllCustomers();
                    break;
                case "2":
                    printAllRooms();
                    break;
                case "3":
                    break;
                case "4":
                    addARoom();
                    break;
            }
        } while (!adminChoice.equals("5"));

        System.out.println();
    }

    public void printAdminMenu() {
        System.out.println("Admin Menu");
        printDivider();
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to Main Menu");
        printDivider();
        System.out.println("Please select a number for the menu option");
    }

    public void printDivider() {
        System.out.println("---------------------------------------------");
    }

//    Option 1: See all customers ----------------------------------------------

    public void printAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>(AdminResource.getCustomers());

        if (allCustomers.isEmpty()) {
            System.out.println("There are no registered customers.");
        } else {
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }
        }
    }

//    Option 2: See all rooms --------------------------------------------------

    public void printAllRooms() {
        AdminResource adminResource = new AdminResource();
        List<IRoom> allRooms = new ArrayList<>(adminResource.getAllRooms());

        for (IRoom room : allRooms) {
            System.out.println(room);
        }
    }


//    Option 4: Add a Room -----------------------------------------------------

    public void addARoom() {

        IRoom newRoom;
        String roomNumber;
        Double price;
        RoomType roomType;
        List<IRoom> newRooms = new ArrayList<>();
        boolean isAddMore = true;

        while (isAddMore) {
            isAddMore = false;
            roomNumber = getRoomNumber("\nEnter room number:", newRooms);
            price = getPrice("Enter price per night:");
            roomType = getRoomType("Enter room type: (1 for single bed, 2 for double bed)");

            newRoom = new Room(roomNumber, price, roomType);
            newRooms.add(newRoom);

            String answer;

            do {
                System.out.println("Would you like to add another room? Y/N");
                answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    isAddMore = true;
                }
            } while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"));
        }

        AdminResource.addRoom(newRooms);
    }

    public String getRoomNumber(String message, List<IRoom> rooms) {

        String roomId;
        boolean isValidRoomNumber = false;

        do {
            System.out.println(message);
            roomId = sc.nextLine();
            if (validateRoomNumberInput(roomId, rooms)) {
                isValidRoomNumber = true;
            }
        } while (!isValidRoomNumber);

        return roomId;
    }

    public boolean validateRoomNumberInput(String inputString, List<IRoom> newRooms) {
        if (inputString == null) {
            return false;
        }

        inputString = inputString.replace("\\s", "");

        Integer r;

        try {
            r = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid room number.\n");
            return false;
        }

        if (r <= 0) {
            System.out.println("Room number must be a positive number.");
            return false;
        }

        List<IRoom> allHotelRooms = new ArrayList<>(AdminResource.getAllRooms());

        for (IRoom room : allHotelRooms) {
            if (room.getRoomNumber().equals(inputString)) {
                System.out.println("Room number already exists.");
                return false;
            }
        }

        for (IRoom newRoom : newRooms) {
            if (newRoom.getRoomNumber().equals(inputString)) {
                System.out.println("You've just added this room.");
                return false;
            }
        }
        return true;
    }

    public Double getPrice(String message) {

        Double roomPrice = 0.0;
        boolean isValidPrice = false;

        do {
            System.out.println(message);
            String input = sc.nextLine();
            if (validateRoomPriceInput(input)) {
                roomPrice = Double.parseDouble(input);
                roomPrice = Math.round(roomPrice * 100.0) / 100.0;
                isValidPrice = true;
            }
        } while (!isValidPrice);

        return roomPrice;
    }

    public boolean validateRoomPriceInput(String inputString) {
        if (inputString == null) {
            return false;
        }

        inputString = inputString.replace("\\s", "");

        Double d;

        try {
            d = Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid price.\n");
            return false;
        }

        if (d < 0) {
            System.out.println("Price must be greater than or equal to 0.\n");
            return false;
        }

        return true;
    }

    public RoomType getRoomType(String message) {

        while (true) {
            System.out.println(message);
            String input = sc.nextLine();

            if (validateRoomTypeInput(input)) {
                if (input.equals("1")) {
                    return RoomType.SINGLE;
                }
                if (input.equals("2")) {
                    return RoomType.DOUBLE;
                }
            }
        }
    }

    public boolean validateRoomTypeInput(String inputString) {
        if (inputString == null) {
            return false;
        }

        inputString = inputString.replace("\\s", "");

        if (!inputString.equals("1") && !inputString.equals("2")) {
            System.out.println("Not a valid room option.");
            return false;
        }

        return true;
    }
}