package com.example.comp2000_restaurant;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "restaurant_notifications";
    private static final String CHANNEL_NAME = "Restaurant Notifications";
    private static final String CHANNEL_DESC = "Notifications for reservations and status changes.";

    /**
     * Creates the notification channel required for Android 8.0 (Oreo) and above.
     * This should be called once when your app starts or before you post the first notification.
     * @param context The application context.
     */
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    /**
     * Displays a simple notification.
     * @param context The context from which this is called.
     * @param notificationId A unique ID for this notification to prevent them from stacking.
     * @param title The title of the notification.
     * @param content The main text of the notification.
     */
    public static void showNotification(Context context, int notificationId, String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // A default icon
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // Dismiss the notification when the user taps on it

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        try {
            notificationManager.notify(notificationId, builder.build());
        } catch (SecurityException e) {
            // This can happen if the user has denied the POST_NOTIFICATIONS permission
            e.printStackTrace();
        }
    }
}
