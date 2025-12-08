package org.example.RoomsModule;

public class Room {
    private int roomId;
    private String roomNumber;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int roomId, String roomNumber, String roomType, double price, boolean isAvailable) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters
    public int getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setAvailable(boolean available) { this.isAvailable = available; }
}

