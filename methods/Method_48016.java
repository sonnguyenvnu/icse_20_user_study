private static GregorianCalendar getCalendar(long timestamp){
  GregorianCalendar day=new GregorianCalendar(TimeZone.getTimeZone("GMT"),getLocale());
  day.setTimeInMillis(timestamp);
  return day;
}
