package com.example.comp2000_restaurant;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    private String id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String contact;

    @SerializedName("usertype")
    private String userType;

    /**
     * Constructor for creating a new user during signup.
     */
    public User(String username, String password, String firstname, String lastname, String email, String contact) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.userType = "guest"; // Default new users to guest
    }

    // --- Getters and Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
