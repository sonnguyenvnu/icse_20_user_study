private static SimpleDateFormat dateParserNoMillis(){
  SimpleDateFormat dateParserNoMillis=new SimpleDateFormat(DATE_FORMAT_NO_MILLIS);
  dateParserNoMillis.setTimeZone(TIME_ZONE);
  return dateParserNoMillis;
}
