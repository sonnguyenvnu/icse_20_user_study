public static String format(Date date){
  if (date == null) {
    return null;
  }
  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
  f.setTimeZone(TimeZone.getTimeZone("UTC"));
  return f.format(date);
}
