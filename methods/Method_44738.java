public static Date stringToDate(String dateString){
  try {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    return sdf.parse(dateString);
  }
 catch (  ParseException e) {
    return new Date(0);
  }
}
