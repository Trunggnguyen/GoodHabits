package com.trungnvdev.goodhabits.util.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.trungnvdev.goodhabits.MainActivity;
import com.trungnvdev.goodhabits.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageReceiver extends FirebaseMessagingService {

	@Override
	public void
	onMessageReceived(RemoteMessage remoteMessage) {
		if (remoteMessage.getNotification() != null) {
			showNotification(
					remoteMessage.getNotification().getTitle(),
					remoteMessage.getNotification().getBody());
		}
	}
	public void showNotification(String title,
								String message) {
		Intent intent
				= new Intent(this, MainActivity.class);
		// Assign channel ID
		String channel_id = "notification_channel";
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingIntent
				= PendingIntent.getActivity(
				this, 0, intent,
				PendingIntent.FLAG_ONE_SHOT);

		// Create a Builder object using NotificationCompat
		// class. This will allow control over all the flags
		NotificationCompat.Builder builder
				= new NotificationCompat
				.Builder(getApplicationContext(),
				channel_id)
				.setSmallIcon(R.drawable.ic_bell)
				.setAutoCancel(true)
				.setVibrate(new long[]{1000, 1000, 1000,
						1000, 1000})
				.setOnlyAlertOnce(true)
				.setContentIntent(pendingIntent);

		// A customized design for the notification can be
		// set only for Android versions 4.1 and above. Thus
		// condition for the same is checked here.
		if (Build.VERSION.SDK_INT
				>= Build.VERSION_CODES.JELLY_BEAN) {
		} // If Android Version is lower than Jelly Beans,
		// customized layout cannot be used and thus the
		// layout is set as follows
		else {
			builder = builder.setContentTitle(title)
					.setContentText(message)
					.setSmallIcon(R.drawable.ic_bell);
		}
		// Create an object of NotificationManager class to
		// notify the
		// user of events that happen in the background.
		NotificationManager notificationManager
				= (NotificationManager) getSystemService(
				Context.NOTIFICATION_SERVICE);
		// Check if the Android Version is greater than Oreo
		if (Build.VERSION.SDK_INT
				>= Build.VERSION_CODES.O) {
			NotificationChannel notificationChannel
					= new NotificationChannel(
					channel_id, "web_app",
					NotificationManager.IMPORTANCE_HIGH);
			notificationManager.createNotificationChannel(
					notificationChannel);
		}
		notificationManager.notify(0, builder.build());
	}
}
