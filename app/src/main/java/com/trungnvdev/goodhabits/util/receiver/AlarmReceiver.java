package com.trungnvdev.goodhabits.util.receiver;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.trungnvdev.goodhabits.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {
    public   static  String TYPE_ONE_TIME = "OneTimeAlarm";
    public   static  String TYPE_REPEATING ="GoodHabits";
    private  static  String EXTRA_MESSAGE ="message";
    private  static  String EXTRA_TYPE ="type";

    private  static  int ID_ONETIME= 100;
    private  static  int ID_REPEATING= 101;
    private  static  String CHENAL_ID ="notification_id";
    private  static  String CHENAL_NAME ="notification_name";


    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String type =intent.getStringExtra(EXTRA_TYPE);
        String message =intent.getStringExtra(EXTRA_MESSAGE);

        String title = type.equalsIgnoreCase(TYPE_ONE_TIME) ? TYPE_ONE_TIME : TYPE_REPEATING;
//        int notifiid = type.equalsIgnoreCase(TYPE_ONE_TIME) ? ID_ONETIME : ID_REPEATING;
        showAlarmNotification(context, title, message, new Random().nextInt() );

    }

    private void showAlarmNotification(Context context, String title, String message, int notifi) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri soundalarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ffffffff")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setVibrate(new long[]{1000,1000,1000,1000})
                .setSound(soundalarm);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHENAL_ID, CHENAL_NAME,  NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{1000,1000,1000,1000});
            builder.setChannelId(CHENAL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Notification notification = builder.build();
        if (notificationManager != null){
            notificationManager.notify(notifi, notification);
        }
    }


    public  void setRepeatingAlarm(Context context, String  type, String time, String message, String id){
        if (isDateInvalid(time, "HH:mm")) return;
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        intent.setData(Uri.parse(id));
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_TYPE,type);

        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
       }
    }

    public  void cancelAlarm(Context context, String  type, String id){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setData(Uri.parse(id));
        int requescode = type.equalsIgnoreCase(TYPE_ONE_TIME) ? ID_ONETIME : ID_REPEATING;
         PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requescode, intent, PendingIntent.FLAG_CANCEL_CURRENT) ;
         if (alarmManager != null){
             alarmManager.cancel(pendingIntent);
         }

    }

    public static void cancelReminder(Context context, String id) {

        Intent intent = new Intent();
        intent.setData(Uri.parse(id));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }

//    public boolean isAlarmset(Context context,String type){
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        int requescode = type.equalsIgnoreCase(TYPE_ONE_TIME) ? ID_ONETIME : ID_REPEATING;
//        return PendingIntent.getBroadcast(context, requescode, intent, PendingIntent.FLAG_NO_CREATE) != null;
//    }

    public boolean isDateInvalid(String date, String format){
        DateFormat dateFormat =new SimpleDateFormat(format, Locale.getDefault());
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return  false;
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }
}
