public static String formatYYYYMMddHHMMSS(Date date){
  if (date == null) {
    return null;
  }
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  return sdf.format(date);
}
