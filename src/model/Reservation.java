package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    // TODO re-write Reservation.toString

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