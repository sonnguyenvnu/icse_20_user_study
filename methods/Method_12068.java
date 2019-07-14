public static String dateToStr(Date date,String formatStr){
  if (date == null) {
    return StringUtils.EMPTY;
  }
  DateTime dateTime=new DateTime(date);
  return dateTime.toString(formatStr);
}
