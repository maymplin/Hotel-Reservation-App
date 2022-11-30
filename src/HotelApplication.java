import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelApplication {

    public static void main(String[] args) {

        addTestData();

        MainMenu mainMenu = new MainMenu();

        mainMenu.enterMainMenu();
    }

    public static void addTestData() {

        final ReservationService reservationService = ReservationService.getInstance();
        final CustomerService customerService = CustomerService.getInstance();

        // add customers
        customerService.addCustomer("tseliot@fourquartets.com", "T.H.", "Eliot");
        customerService.addCustomer("esatie@gymnopedies.com", "Erik", "Satie");
        customerService.addCustomer("brussell@logic.com", "Bertrand", "Russell");
        customerService.addCustomer("mcurie@radiation.com", "Marie", "Curie");
        customerService.addCustomer("jlocke@empiricism.com", "John", "Locke");
        customerService.addCustomer("ghhardy@maths.com", "G.H.", "Hardy");

        // Create rooms
        Room room101 = new Room("101", 239.48, RoomType.SINGLE);
        Room room102 = new Room("102", 486.23, RoomType.SINGLE);
        Room room103 = new Room("103", 318.29, RoomType.SINGLE);
        Room room201 = new Room("201", 628.14, RoomType.DOUBLE);
        Room room202 = new Room("202", 528.38, RoomType.DOUBLE);
        Room room203 = new Room("203", 842.50, RoomType.DOUBLE);

        List<IRoom> rooms = new ArrayList<>(
                List.of(room101, room102, room103, room201, room202, room203)
        );

        rooms.forEach(room -> reservationService.addRoom(room));

        // Create reservations
        reservationService.reserveARoom(
                customerService.getCustomer("tseliot@fourquartets.com"),
                room101,
                new Date(2022-1900, Calendar.NOVEMBER, 26),
                new Date(2022-1900, Calendar.NOVEMBER, 30)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("tseliot@fourquartets.com"),
                room202,
                new Date(2023-1900, Calendar.JANUARY, 6),
                new Date(2023-1900, Calendar.JANUARY, 10)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("esatie@gymnopedies.com"),
                room102,
                new Date(2022-1900, Calendar.DECEMBER, 27),
                new Date(2023-1900, Calendar.JANUARY, 5)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("brussell@logic.com"),
                room103,
                new Date(2022-1900, Calendar.DECEMBER, 15),
                new Date(2022-1900, Calendar.DECEMBER, 19)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("mcurie@radiation.com"),
                room201,
                new Date(2022-1900, Calendar.NOVEMBER, 29),
                new Date(2022-1900, Calendar.DECEMBER, 7)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("jlocke@empiricism.com"),
                room202,
                new Date(2022-1900, Calendar.DECEMBER, 31),
                new Date(2023-1900, Calendar.JANUARY, 3)
        );
        reservationService.reserveARoom(
                customerService.getCustomer("ghhardy@maths.com"),
                room203,
                new Date(2022-1900, Calendar.DECEMBER, 3),
                new Date(2022-1900, Calendar.DECEMBER, 16)
        );

    }
}
