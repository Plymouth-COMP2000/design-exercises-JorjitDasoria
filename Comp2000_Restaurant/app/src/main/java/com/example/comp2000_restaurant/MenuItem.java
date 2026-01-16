package com.example.comp2000_restaurant;

public class MenuItem {
    private int id; // Added ID field
    private String name;
    private String description;
    private double price;
    private int imageUrl;

    public MenuItem(int id, String name, String description, double price, int imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getImageUrl() { return imageUrl; }
    public void setImageUrl(int imageUrl) { this.imageUrl = imageUrl; }
}
