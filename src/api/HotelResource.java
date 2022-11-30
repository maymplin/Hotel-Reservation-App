package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final ReservationService reservationService = ReservationService.getInstance();

    public HotelResource() {
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        try {
            CustomerService.addCustomer(email, firstName, lastName);
            System.out.println("Account added successfully.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room,
                                 Date CheckInDate, Date CheckOutDate) {
        return reservationService.reserveARoom(getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
    }

    public static Collection<Reservation> getCustomersReservations (String customerEmail) {
        return reservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
