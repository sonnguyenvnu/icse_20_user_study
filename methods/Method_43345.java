private static SimpleDateFormat dateParser(){
  SimpleDateFormat dateParser=new SimpleDateFormat(DATE_FORMAT);
  dateParser.setTimeZone(TIME_ZONE);
  return dateParser;
}
