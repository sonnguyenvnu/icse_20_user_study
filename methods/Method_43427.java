public static Date toDate(String timeStamp){
  try {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'.'SSS");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    return sdf.parse(timeStamp);
  }
 catch (  ParseException e) {
    try {
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      return sdf.parse(timeStamp);
    }
 catch (    ParseException e1) {
      return null;
    }
  }
}
