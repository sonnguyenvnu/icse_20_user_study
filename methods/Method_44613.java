private static Date extractTimestampInput(String strDate){
  final List<String> dateFormats=Arrays.asList("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","yyyy-MM-dd'T'HH:mm:ss'Z'");
  for (  String format : dateFormats) {
    SimpleDateFormat sdf=new SimpleDateFormat(format);
    try {
      return sdf.parse(strDate);
    }
 catch (    ParseException e) {
    }
  }
  throw new IllegalArgumentException("Invalid input for date. Given '" + strDate + "', expecting format yyyy-MM-dd'T'HH:mm:ss.SSS'Z' or yyyy-MM-dd'T'HH:mm:ss.SS'Z'.");
}
