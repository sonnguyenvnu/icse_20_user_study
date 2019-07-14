public GregorianCalendar toCalendar(){
  GregorianCalendar day=new GregorianCalendar(TimeZone.getTimeZone("GMT"));
  day.setTimeInMillis(unixTime);
  return day;
}
