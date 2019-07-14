public static String formatCSTTime(String date,String format) throws ParseException {
  SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
  Date d=sdf.parse(date);
  return DateUtil.getDateFormat(d,format);
}
