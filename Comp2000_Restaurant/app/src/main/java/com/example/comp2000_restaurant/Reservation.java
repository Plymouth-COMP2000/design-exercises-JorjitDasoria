package com.example.comp2000_restaurant;

public class Reservation {
    private String customerName;
    private int tableSize;
    private String dateTime;
    private int tableNumber;

    public Reservation(String customerName, int tableSize, String dateTime, int tableNumber) {
        this.customerName = customerName;
        this.tableSize = tableSize;
        this.dateTime = dateTime;
        this.tableNumber = tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableSize() {
        return tableSize;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}
