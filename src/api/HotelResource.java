package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

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

    public IRoom getRoom(String roomNumber) {
        return ReservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room,
                                 Date CheckInDate, Date CheckOutDate) {
        return ReservationService.reserveARoom(getCustomer(customerEmail), room, CheckInDate, CheckInDate);
    }

    public static Collection<Reservation> getCustomersReservations (String customerEmail) {
        return ReservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return ReservationService.findRooms(checkIn, checkOut);
    }
}
