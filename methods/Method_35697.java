private static String formatMessage(String message){
  DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  String date=df.format(new Date());
  return String.format("%s %s",date,message);
}
