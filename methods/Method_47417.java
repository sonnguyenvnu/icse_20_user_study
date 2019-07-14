public static String getDate(long f,String year){
  String date=DATE_NO_MINUTES.format(f);
  if (date.substring(date.length() - 4,date.length()).equals(year))   date=date.substring(0,date.length() - 6);
  return date;
}
