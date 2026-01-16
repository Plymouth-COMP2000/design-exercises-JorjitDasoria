package com.example.comp2000_restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    // UPDATE: Incremented version to force a database rebuild with the correct data
    private static final int DATABASE_VERSION = 5;

    // Users table (for local staff login)
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_ROLE = "role";

    // Menu items table
    private static final String TABLE_MENU_ITEMS = "menu_items";
    private static final String COLUMN_MENU_ID = "id";
    private static final String COLUMN_MENU_NAME = "name";
    private static final String COLUMN_MENU_DESCRIPTION = "description";
    private static final String COLUMN_MENU_PRICE = "price";
    private static final String COLUMN_MENU_IMAGE_URL = "image_url";

    // Reservations table
    private static final String TABLE_RESERVATIONS = "reservations";
    private static final String COLUMN_RESERVATION_ID = "id";
    private static final String COLUMN_RESERVATION_NAME = "customer_name";
    private static final String COLUMN_RESERVATION_GUESTS = "guests";
    private static final String COLUMN_RESERVATION_DATETIME = "datetime";
    private static final String COLUMN_RESERVATION_TABLE = "table_number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "( "
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_USERNAME + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT, "
                + COLUMN_USER_ROLE + " TEXT )";
        db.execSQL(CREATE_USERS_TABLE);

        // Create Menu Items Table
        String CREATE_MENU_ITEMS_TABLE = "CREATE TABLE " + TABLE_MENU_ITEMS + "( "
                + COLUMN_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MENU_NAME + " TEXT, "
                + COLUMN_MENU_DESCRIPTION + " TEXT, "
                + COLUMN_MENU_PRICE + " REAL, "
                + COLUMN_MENU_IMAGE_URL + " INTEGER )";
        db.execSQL(CREATE_MENU_ITEMS_TABLE);

        // Create Reservations Table
        String CREATE_RESERVATIONS_TABLE = "CREATE TABLE " + TABLE_RESERVATIONS + "( "
                + COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RESERVATION_NAME + " TEXT, "
                + COLUMN_RESERVATION_GUESTS + " INTEGER, "
                + COLUMN_RESERVATION_DATETIME + " TEXT, "
                + COLUMN_RESERVATION_TABLE + " INTEGER )";
        db.execSQL(CREATE_RESERVATIONS_TABLE);

        addInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This process wipes all old data and rebuilds the database from scratch
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
        onCreate(db);
    }

    private void addInitialData(SQLiteDatabase db) {
        // Add a local staff user for authentication
        addUser(db, "123456", "password", "staff");

        // Add all menu items
        addMenuItem(db, "Margherita Pizza", "Classic cheese and tomato pizza.", 12.99, R.drawable.pizza_pic);
        addMenuItem(db, "Cheeseburger", "Juicy beef burger with cheddar cheese.", 8.99, R.drawable.toast_slice);
        addMenuItem(db, "Spaghetti Carbonara", "Creamy pasta with bacon and parmesan.", 14.50, R.drawable.pasta);
        addMenuItem(db, "Caesar Salad", "Fresh romaine with Caesar dressing.", 9.75, R.drawable.salad);
        addMenuItem(db, "Sirloin Steak", "8oz sirloin with chips and salad.", 22.00, R.drawable.steak);
        addMenuItem(db, "Tomato Soup", "Homemade tomato soup with bread.", 6.50, R.drawable.soup);
        addMenuItem(db, "Chocolate Cake", "Rich, moist chocolate cake.", 7.00, R.drawable.cake);
        addMenuItem(db, "Espresso", "A strong shot of black coffee.", 2.50, R.drawable.coffee);
        addMenuItem(db, "English Tea", "A classic pot of breakfast tea.", 3.00, R.drawable.tea);
    }
    
    // --- Helper & User Methods ---

    private void addUser(SQLiteDatabase db, String username, String password, String role) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_ROLE, role);
        db.insert(TABLE_USERS, null, values);
    }

    public boolean checkUser(String username, String password, String role) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_USER_ID},
                COLUMN_USER_USERNAME + "=? AND " + COLUMN_USER_PASSWORD + "=? AND " + COLUMN_USER_ROLE + "=?",
                new String[]{username, password, role}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    // --- Menu Item Methods ---

    private void addMenuItem(SQLiteDatabase db, String name, String description, double price, int imageResId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MENU_NAME, name);
        values.put(COLUMN_MENU_DESCRIPTION, description);
        values.put(COLUMN_MENU_PRICE, price);
        values.put(COLUMN_MENU_IMAGE_URL, imageResId);
        db.insert(TABLE_MENU_ITEMS, null, values);
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU_ITEMS, null);
        if (cursor.moveToFirst()) {
            do {
                menuItems.add(new MenuItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return menuItems;
    }

    public int updateMenuItem(MenuItem menuItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MENU_NAME, menuItem.getName());
        values.put(COLUMN_MENU_DESCRIPTION, menuItem.getDescription());
        values.put(COLUMN_MENU_PRICE, menuItem.getPrice());
        int rows = db.update(TABLE_MENU_ITEMS, values, COLUMN_MENU_ID + " = ?", new String[]{String.valueOf(menuItem.getId())});
        db.close();
        return rows;
    }

    // --- Reservation Methods ---

    public boolean addReservation(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESERVATION_NAME, reservation.getCustomerName());
        values.put(COLUMN_RESERVATION_GUESTS, reservation.getTableSize());
        values.put(COLUMN_RESERVATION_DATETIME, reservation.getDateTime());
        values.put(COLUMN_RESERVATION_TABLE, reservation.getTableNumber());
        long result = db.insert(TABLE_RESERVATIONS, null, values);
        db.close();
        return result != -1;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RESERVATIONS, null);
        if (cursor.moveToFirst()) {
            do {
                reservationList.add(new Reservation(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return reservationList;
    }
}
