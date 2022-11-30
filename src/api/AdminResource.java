package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static final ReservationService reservationService = ReservationService.getInstance();

    public AdminResource() {
    }

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {

        return new ArrayList<>(reservationService.getAllRooms().values());
    }

    public static Collection<Customer> getCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
