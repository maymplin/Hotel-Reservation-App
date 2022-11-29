package service;

import model.Customer;
import model.IRoom;
import model.Reservation;


import java.util.*;

public class ReservationService {

    private static final Map<String, IRoom> allRooms = new HashMap<>();
    private static final Map<String, List<Reservation>> allReservations = new HashMap<>();
    private static final Map<String, List<Reservation>> customerReservations = new HashMap<>();

    public ReservationService() {
    }

    public static Map<String, IRoom> getAllRooms() {
             return allRooms;
    }

    public static void addRoom(IRoom room) {
        if (room != null) {
            allRooms.put(room.getRoomNumber(), room);
        }
    }

    public static IRoom getARoom (String roomId) {
        if (roomId != null && !roomId.equals("")) {
            return allRooms.get(roomId);
        }
        return null;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate) {

        if (customer != null && room != null && checkInDate != null & checkOutDate != null) {
            Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);

            String roomId = room.getRoomNumber();

            if (!allReservations.containsKey(roomId)) {
                allReservations.put(roomId, new ArrayList());
            }

            allReservations.get(roomId).add(newReservation);
            sortReservations(allReservations.get(roomId));

            addCustomerReservation(newReservation, customer.getEmail());

            return newReservation;
        }

        return null;
    }

    public static void addCustomerReservation(Reservation reservation, String customerEmail) {
        if (!customerReservations.containsKey(customerEmail)) {
            customerReservations.put(customerEmail, new ArrayList());
        }
        customerReservations.get(customerEmail).add(reservation);
        sortReservations(customerReservations.get(customerEmail));
    }

    static void sortReservations(List<Reservation> reservations) {
        reservations.sort((o1, o2)
                -> o1.getRoom().getRoomNumber().compareTo(o2.getRoom().getRoomNumber()));
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();

        for (String roomId : allRooms.keySet()) {
            if (!allReservations.containsKey(roomId)) {
                availableRooms.add(allRooms.get(roomId));
                continue;
            }
            if (checkAvailability(roomId, checkInDate, checkOutDate)) {
                availableRooms.add(allRooms.get(roomId));
            }
        }

        return availableRooms;
    }

    public static boolean checkAvailability(String roomId, Date checkIn, Date checkOut) {
        List<Reservation> currentReservations = new ArrayList<>(allReservations.get(roomId));

        for (Reservation reservation : currentReservations) {
            Date reservationIn = reservation.getCheckInDate();
            Date reservationOut = reservation.getCheckOutDate();

            if (reservationIn.before(checkIn) && reservationOut.after(checkOut))
                return false;
            if (reservationIn.after(checkIn) && reservationIn.before(checkOut))
                return false;
            if (reservationOut.after(checkIn) && reservationOut.before(checkOut))
                return false;
        }
        return true;
    }

    public static Collection<Reservation> getCustomerReservation(Customer customer) {

        if (customerReservations.containsKey(customer.getEmail())) {
            return customerReservations.get(customer.getEmail());
        }
        return new ArrayList<>();
    }

    public static void printAllReservation() {
        if (!allReservations.isEmpty()) {
            allReservations.forEach((key, value) -> {
                for (Reservation v : value) {
                    System.out.println(v);
                }
            });
        } else {
            System.out.println("There are currently no reservations.");
        }
    }
}
