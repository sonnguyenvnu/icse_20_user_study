public static String formatYYYYMMdd(Date date){
  SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
  return sf.format(date);
}
