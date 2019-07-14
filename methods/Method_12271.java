public static Date strToDate(String dateTimeStr){
  DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(STANDARD_FORMAT);
  DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
  return dateTime.toDate();
}
