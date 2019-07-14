private void scheduleNotificationRepeat(){
  try {
    Intent intent=new Intent(ApplicationLoader.applicationContext,NotificationRepeat.class);
    intent.putExtra("currentAccount",currentAccount);
    PendingIntent pintent=PendingIntent.getService(ApplicationLoader.applicationContext,0,intent,0);
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    int minutes=preferences.getInt("repeat_messages",60);
    if (minutes > 0 && personal_count > 0) {
      alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + minutes * 60 * 1000,pintent);
    }
 else {
      alarmManager.cancel(pintent);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
