public static String format(Date d){
  if (d == null) {
    return "";
  }
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  return sdf.format(d);
}
