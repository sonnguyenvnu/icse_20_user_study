public static Date toDate(String dateString){
  if (dateString == null)   return null;
  try {
    return dateParser().parse(dateString);
  }
 catch (  ParseException e) {
    try {
      return dateParserNoMillis().parse(dateString);
    }
 catch (    ParseException e1) {
      throw new ExchangeException("Illegal date/time format",e1);
    }
  }
}
