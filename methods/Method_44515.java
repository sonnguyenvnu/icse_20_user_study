public static Date toDate(String dateString){
  try {
    return dateParserNoMillis().parse(dateString);
  }
 catch (  ParseException e) {
    OffsetDateTime offsetDateTime=OffsetDateTime.parse(dateString);
    return new Date(offsetDateTime.toInstant().toEpochMilli());
  }
}
