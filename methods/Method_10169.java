private static String getISO8601Time(){
  final Date nowDate=new Date();
  final SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  df.setTimeZone(new SimpleTimeZone(0,"GMT"));
  return df.format(nowDate);
}
