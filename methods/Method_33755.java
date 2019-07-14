public static String formatDataTime(long timeMilliseconds){
  try {
    SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    String date=sDateFormat.format(timeMilliseconds);
    return date;
  }
 catch (  Exception e) {
    return getData();
  }
}
