public static String getNowTime(){
  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
  return dateFormat.format(new Date());
}
