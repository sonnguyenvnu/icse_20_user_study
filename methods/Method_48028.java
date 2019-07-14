public static long getUpcomingTimeInMillis(int hour,int minute){
  Calendar calendar=DateUtils.getStartOfTodayCalendar();
  calendar.set(Calendar.HOUR_OF_DAY,hour);
  calendar.set(Calendar.MINUTE,minute);
  calendar.set(Calendar.SECOND,0);
  Long time=calendar.getTimeInMillis();
  if (DateUtils.getLocalTime() > time)   time+=DateUtils.DAY_LENGTH;
  return applyTimezone(time);
}
