public static Date toDate(QueryParameter parameter){
  try {
    return parameter.isPresent() ? new ISO8601DateFormat().parse(parameter.firstValue()) : null;
  }
 catch (  ParseException e) {
    throw new IllegalArgumentException(parameter.firstValue() + " is not a valid ISO8601 date");
  }
}
