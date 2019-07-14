public static String getSmartDate(Date date){
  return date == null ? "" : getSmartDate(date.getTime());
}
