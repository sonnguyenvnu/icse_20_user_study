public static String now(){
  Date date=new Date();
  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  String dateString=formatter.format(date);
  return dateString;
}
