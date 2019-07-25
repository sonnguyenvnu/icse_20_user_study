@Override public void schedule(long delayInMilliseconds){
  long nextAlarmInMilliseconds=System.currentTimeMillis() + delayInMilliseconds;
  Log.d(TAG,"Schedule next alarm at " + nextAlarmInMilliseconds);
  AlarmManager alarmManager=(AlarmManager)service.getSystemService(Service.ALARM_SERVICE);
  if (Build.VERSION.SDK_INT >= 23) {
    Log.d(TAG,"Alarm scheule using setExactAndAllowWhileIdle, next: " + delayInMilliseconds);
    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,nextAlarmInMilliseconds,pendingIntent);
  }
 else   if (Build.VERSION.SDK_INT >= 19) {
    Log.d(TAG,"Alarm scheule using setExact, delay: " + delayInMilliseconds);
    alarmManager.setExact(AlarmManager.RTC_WAKEUP,nextAlarmInMilliseconds,pendingIntent);
  }
 else {
    alarmManager.set(AlarmManager.RTC_WAKEUP,nextAlarmInMilliseconds,pendingIntent);
  }
}
