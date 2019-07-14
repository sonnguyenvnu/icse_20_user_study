public static String getUtcTimestamp(){
  DateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  format.setTimeZone(TimeZone.getTimeZone("GMT"));
  return format.format(new Date());
}
