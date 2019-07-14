public static Long toEpoch(Date dateTime,String timeZone){
  Calendar timeOffset=Calendar.getInstance(TimeZone.getTimeZone(timeZone));
  timeOffset.set(Calendar.MILLISECOND,0);
  timeOffset.set(Calendar.SECOND,0);
  timeOffset.set(Calendar.MINUTE,0);
  timeOffset.set(Calendar.HOUR_OF_DAY,0);
  long midnightOffSet=timeOffset.getTime().getTime();
  long localTimestamp=dateTime.getTime();
  return timeOffset == null ? null : midnightOffSet + localTimestamp;
}
