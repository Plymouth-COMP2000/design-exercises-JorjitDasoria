package com.example.comp2000_restaurant;

public class Reservation {
    private int id; // ID from the database
    private String customerName;
    private int tableSize;
    private String dateTime;
    private int tableNumber;

    // Constructor for creating a new reservation (ID is set by the database)
    public Reservation(String customerName, int tableSize, String dateTime, int tableNumber) {
        this.customerName = customerName;
        this.tableSize = tableSize;
        this.dateTime = dateTime;
        this.tableNumber = tableNumber;
    }

    // Constructor for reading a reservation from the database
    public Reservation(int id, String customerName, int tableSize, String dateTime, int tableNumber) {
        this.id = id;
        this.customerName = customerName;
        this.tableSize = tableSize;
        this.dateTime = dateTime;
        this.tableNumber = tableNumber;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public int getTableSize() { return tableSize; }
    public void setTableSize(int tableSize) { this.tableSize = tableSize; }
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
}
