public void start(Context c){
  AlarmManager manager=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
  Calendar cal=Calendar.getInstance();
  cal.set(Calendar.HOUR_OF_DAY,Reddit.cachedData.getInt("hour",0));
  cal.set(Calendar.MINUTE,Reddit.cachedData.getInt("minute",0));
  if (cal.getTimeInMillis() < System.currentTimeMillis()) {
    cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR) + 1);
  }
  manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
}
