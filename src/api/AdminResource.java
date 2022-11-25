package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {

    public AdminResource() {
    }

    public Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

public static void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        List<IRoom> allRooms = new ArrayList<>(ReservationService.getAllRooms().values());

        return allRooms;
    }

    public Collection<Customer> getCustomers() {
        return CustomerService.getAllCustomers();
    }

    public void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
