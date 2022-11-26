package model;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    // TODO write a Room constructor
    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String RoomNumber) {
        this.roomNumber = RoomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.getPrice();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean isFree() {
        return getRoomPrice() == 0;
    }

    // TODO re-write Room.toString()

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + " / " +
                roomType + " / " +
                "Room Price: $" + price;
    }
}
