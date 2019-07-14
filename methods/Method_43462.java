public static Date adaptDatetime(String datetime){
  try {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    return sdf.parse(datetime);
  }
 catch (  ParseException e) {
    throw new RuntimeException(e);
  }
}
