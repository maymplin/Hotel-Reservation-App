import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;

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

//                System.out.println("|" + adminChoice + "|\n");
            }
            switch (adminChoice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    addARoom();
                    break;
                case "5":
                    break;
                default:
            }
        } while (adminChoice != "6");

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

//    Option 4: Add a Room

    public void addARoom() {

        IRoom newRoom;
        String roomNumber;
        Double price;
        RoomType roomType;
        List<IRoom> newRooms = new ArrayList<IRoom>();
        boolean isDuplicateRoom = false;
        boolean isAddMore = false;

        do {
            roomNumber = getRoomNumber("\nEnter room number:");
            price = getPrice("Enter price per night:");
            roomType = getRoomType("Enter room type: (1 for single bed, 2 for double bed)");

            newRoom = new Room(roomNumber, price, roomType);

            for (IRoom room : newRooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    isDuplicateRoom = true;
                    break;
                }
            }

            if (!isDuplicateRoom) {
                newRooms.add(newRoom);
            }

            String answer = "";

            do {
                System.out.println("Would you like to add another room? Y/N");
                answer = sc.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    isAddMore = true;
                }
            } while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N"));
        } while (isAddMore);

        AdminResource.addRoom(newRooms);
    }

    public String getRoomNumber(String message) {

        String roomId;
        boolean isValidRoomNumber = false;

        do {
            System.out.println(message);
            roomId = sc.nextLine();
            if (validateDataType("roomNumber", roomId)) {
                isValidRoomNumber = true;
            }
        } while (!isValidRoomNumber);

        return roomId;
    }

    public Double getPrice(String message) {

        Double roomPrice = 0.0;
        boolean isValidPrice = false;

        do {
            System.out.println(message);
            String input = sc.nextLine();
            if (validateDataType("roomPrice", input)) {
                roomPrice = Double.parseDouble(input);
                isValidPrice = true;
            }
        } while (!isValidPrice);

        return roomPrice;
    }

    public RoomType getRoomType(String message) {

        while (true) {
            System.out.println(message);
            String input = sc.nextLine();
//            System.out.println("Room Type entered: " + input.getClass().getSimpleName());
            if (validateDataType("roomType", input)) {
                if (input.equals("1")) {
                    return RoomType.SINGLE;
                }
                if (input.equals("2")) {
                    return RoomType.DOUBLE;
                }
            }
        }
    }

    public boolean validateDataType(String type, String inputString) {
        if (inputString == null) {
            return false;
        }

        inputString = inputString.replace("\\s", "");

        if (type == "roomType") {
            if (!inputString.equals("1") && !inputString.equals("2")) {
                System.out.println("Not a valid room option.");
                return false;
            }
        }

        if (type == "roomNumber") {
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
                    break;
                }
            }

            if (type == "roomPrice") {
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
            }

            return true;
        }

    }
}