private static Date adaptDate(String date){
  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  Date result;
  try {
    result=format.parse(date);
  }
 catch (  ParseException e) {
    throw new ExchangeException("Date/time parse error: " + e.getMessage());
  }
  return result;
}
