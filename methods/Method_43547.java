public static Date toDate(String datetime){
  SimpleDateFormat sdf;
  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
  try {
    return sdf.parse(datetime);
  }
 catch (  ParseException e) {
    return EPOCH;
  }
}
