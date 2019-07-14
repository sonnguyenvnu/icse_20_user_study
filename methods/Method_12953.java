public static String getDefaultDateString(Date date){
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  return simpleDateFormat.format(date);
}
