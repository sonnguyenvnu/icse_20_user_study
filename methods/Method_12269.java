public static Date strToDate(String dateTimeStr,String formatStr){
  DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(formatStr);
  DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
  return dateTime.toDate();
}
