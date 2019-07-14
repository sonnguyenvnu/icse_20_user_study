public static String dateToStr(Date date){
  if (date == null) {
    return StringUtils.EMPTY;
  }
  DateTime dateTime=new DateTime(date);
  return dateTime.toString(STANDARD_FORMAT);
}
