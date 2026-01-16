package com.example.comp2000_restaurant;

import android.content.Context;
import android.util.Log;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class Api {

    public static final String BASE_URL = "http://10.240.72.69/comp2000/coursework";
    private static final String STUDENT_ID = "10951727";
    
    private static RequestQueue requestQueue;
    private static final Gson gson = new Gson();

    // --- Callback Interfaces (All Included) ---
    public interface GeneralApiCallback {
        void onSuccess(String message);
        void onError(String message);
    }

    public interface LoginCallback {
        void onSuccess(User user);
        void onError(String message);
    }
    
    // Callback for fetching a user profile without a password check
    public interface GetUserCallback {
        void onSuccess(User user);
        void onError(String message);
    }

    private static void initQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    // --- User Methods (All Included) ---

    public static void createUser(Context context, User user, GeneralApiCallback callback) {
        initQueue(context);
        String url = BASE_URL + "/create_user/" + STUDENT_ID;
        try {
            JSONObject jsonRequest = new JSONObject(gson.toJson(user));
            JsonObjectRequest request = new JsonObjectRequest(1, url, jsonRequest, // 1 = POST
                    response -> callback.onSuccess("User created successfully"),
                    error -> {
                        Log.e("API_ERROR", "Create User: " + error.toString());
                        callback.onError("Failed to create user.");
                    });
            requestQueue.add(request);
        } catch (JSONException e) {
            Log.e("API_ERROR", "Create User JSONException: " + e.getMessage());
            callback.onError("An unexpected error occurred.");
        }
    }

    public static void loginUser(Context context, String username, String password, LoginCallback callback) {
        initQueue(context);
        String url = BASE_URL + "/read_user/" + STUDENT_ID + "/" + username;
        Log.d("API_REQUEST", "Login URL: " + url);

        JsonObjectRequest request = new JsonObjectRequest(0, url, null, // 0 = GET
                response -> {
                    try {
                        JSONObject userJson = response.getJSONObject("user");
                        User user = gson.fromJson(userJson.toString(), User.class);
                        if (user != null && user.getUsername() != null && password.equals(user.getPassword())) {
                            callback.onSuccess(user);
                        } else {
                            callback.onError("Invalid username or password.");
                        }
                    } catch (JSONException e) {
                        Log.e("API_ERROR", "Error parsing nested user object: " + e.getMessage());
                        callback.onError("Invalid data format received from server.");
                    }
                },
                error -> {
                    String errorMessage = "Invalid username or password.";
                    NetworkResponse response = error.networkResponse;
                    if (response != null) { Log.e("API_ERROR", "Login Error - Status: " + response.statusCode); }
                    callback.onError(errorMessage);
                });
        requestQueue.add(request);
    }

    public static void updateUser(Context context, User user, GeneralApiCallback callback) {
        initQueue(context);
        String url = BASE_URL + "/update_user/" + STUDENT_ID + "/" + user.getUsername();
        try {
            JSONObject jsonRequest = new JSONObject(gson.toJson(user));
            JsonObjectRequest request = new JsonObjectRequest(2, url, jsonRequest, // 2 = PUT
                    response -> callback.onSuccess("Profile updated successfully"),
                    error -> {
                        Log.e("API_ERROR", "Update User: " + error.toString());
                        callback.onError("Failed to update profile.");
                    });
            requestQueue.add(request);
        } catch (JSONException e) {
            Log.e("API_ERROR", "Update User JSONException: " + e.getMessage());
            callback.onError("An unexpected error occurred.");
        }
    }

    /**
     * NEW: Fetches a single user's public profile by their username.
     * This method does NOT check the password.
     */
    public static void getUserByUsername(Context context, String username, GetUserCallback callback) {
        initQueue(context);
        String url = BASE_URL + "/read_user/" + STUDENT_ID + "/" + username;

        JsonObjectRequest request = new JsonObjectRequest(0, url, null, // 0 = GET
                response -> {
                    try {
                        JSONObject userJson = response.getJSONObject("user");
                        User user = gson.fromJson(userJson.toString(), User.class);
                        if (user != null && user.getUsername() != null) {
                            callback.onSuccess(user);
                        } else {
                            callback.onError("Invalid user data format.");
                        }
                    } catch (JSONException e) {
                        Log.e("API_ERROR", "Error parsing user object: " + e.getMessage());
                        callback.onError("Invalid data from server.");
                    }
                },
                error -> {
                    Log.e("API_ERROR", "Get User Error: " + error.toString());
                    callback.onError("Could not fetch user profile.");
                });
        requestQueue.add(request);
    }
}
