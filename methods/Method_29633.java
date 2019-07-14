public static String formatYYYYMMddHHMM(Date date){
  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
  return sdf.format(date);
}
