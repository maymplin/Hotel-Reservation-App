import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HotelReservationApplication {

    public static void main(String[] args) {

        addTestData();

        MainMenu mainMenu = new MainMenu();

        mainMenu.enterMainMenu();
    }

    public static void addTestData() {

        // Create customers
//        Customer thomas = new Customer("T.H.", "Eliot", "tseliot@fourquartets.com");
//        Customer erik = new Customer("Erik", "Satie", "esatie@gymnopedies.com");
//        Customer bertrand = new Customer("Bertrand", "Russell", "brussell@logic.com");
//        Customer marie = new Customer("Marie", "Curie", "mcurie@radiation.com");
//        Customer john = new Customer("John", "Locke", "jlocke@empiricism.com");
//        Customer godfrey = new Customer("G.H.", "Hardy", "ghhardy@maths.com");

        CustomerService.addCustomer("tseliot@fourquartets.com", "T.H.", "Eliot");
        CustomerService.addCustomer("esatie@gymnopedies.com", "Erik", "Satie");
        CustomerService.addCustomer("brussell@logic.com", "Bertrand", "Russell");
        CustomerService.addCustomer("mcurie@radiation.com", "Marie", "Curie");
        CustomerService.addCustomer("jlocke@empiricism.com", "John", "Locke");
        CustomerService.addCustomer("ghhardy@maths.com", "G.H.", "Hardy");


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

        rooms.forEach(ReservationService::addRoom);

        // Create reservations
        ReservationService.reserveARoom(
                CustomerService.getCustomer("tseliot@fourquartets.com"),
                room101,
                new Date(2022-1900, Calendar.NOVEMBER, 26),
                new Date(2022-1900, Calendar.NOVEMBER, 30)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("tseliot@fourquartets.com"),
                room202,
                new Date(2023-1900, Calendar.JANUARY, 6),
                new Date(2023-1900, Calendar.JANUARY, 10)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("esatie@gymnopedies.com"),
                room102,
                new Date(2022-1900, Calendar.DECEMBER, 27),
                new Date(2023-1900, Calendar.JANUARY, 5)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("brussell@logic.com"),
                room103,
                new Date(2022-1900, Calendar.DECEMBER, 15),
                new Date(2022-1900, Calendar.DECEMBER, 19)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("mcurie@radiation.com"),
                room201,
                new Date(2022-1900, Calendar.NOVEMBER, 29),
                new Date(2022-1900, Calendar.DECEMBER, 7)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("jlocke@empiricism.com"),
                room202,
                new Date(2022-1900, Calendar.DECEMBER, 31),
                new Date(2023-1900, Calendar.JANUARY, 3)
        );
        ReservationService.reserveARoom(
                CustomerService.getCustomer("ghhardy@maths.com"),
                room203,
                new Date(2022-1900, Calendar.DECEMBER, 3),
                new Date(2022-1900, Calendar.DECEMBER, 16)
        );


//        Reservation thomas101 = new Reservation(thomas, room101, new Date(2022, 11, 26), new Date(2022, 11, 30));
//        Reservation erik102 = new Reservation(erik, room102, new Date(2022, 12, 27), new Date(2023, 1, 5));
//        Reservation bertrand103 = new Reservation(bertrand, room103, new Date(2022, 12, 15), new Date(2022, 12, 19));
//        Reservation marie201 = new Reservation(marie, room201, new Date(2022, 11, 29), new Date(2022, 12, 07));
//        Reservation john202 = new Reservation(john, room202, new Date(2022, 12, 31), new Date(2023, 1, 3));
//        Reservation godfrey203 = new Reservation(godfrey, room203, new Date(2022, 12, 03), new Date(2022, 12, 16));
    }
}
