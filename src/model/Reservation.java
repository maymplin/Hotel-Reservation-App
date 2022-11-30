package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE. MMM dd, yyyy");

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        String dateIn = DATE_FORMAT.format(checkInDate);
        String dateOut = DATE_FORMAT.format(checkOutDate);
        return "Reservation for - " + customer +
                "\n" + room +
                " | Check-in: " + dateIn +
                " | Check-out: " + dateOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return getRoom().equals(that.getRoom()) && getCheckInDate().equals(that.getCheckInDate()) && getCheckOutDate().equals(that.getCheckOutDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoom(), getCheckInDate(), getCheckOutDate());
    }
}