public void start(Context c){
  AlarmManager manager=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
  int interval=1000 * 60 * Reddit.notificationTime;
  long currentTime=System.currentTimeMillis();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    manager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,currentTime + interval,pendingIntent);
  }
 else {
    manager.set(AlarmManager.RTC_WAKEUP,currentTime + interval,pendingIntent);
  }
}
