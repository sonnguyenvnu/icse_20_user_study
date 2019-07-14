/** 
 * Parses an xs:dateTime attribute value, returning the parsed timestamp in milliseconds since the epoch.
 * @param value The attribute value to decode.
 * @return The parsed timestamp in milliseconds since the epoch.
 * @throws ParserException if an error occurs parsing the dateTime attribute value.
 */
public static long parseXsDateTime(String value) throws ParserException {
  Matcher matcher=XS_DATE_TIME_PATTERN.matcher(value);
  if (!matcher.matches()) {
    throw new ParserException("Invalid date/time format: " + value);
  }
  int timezoneShift;
  if (matcher.group(9) == null) {
    timezoneShift=0;
  }
 else   if (matcher.group(9).equalsIgnoreCase("Z")) {
    timezoneShift=0;
  }
 else {
    timezoneShift=((Integer.parseInt(matcher.group(12)) * 60 + Integer.parseInt(matcher.group(13))));
    if ("-".equals(matcher.group(11))) {
      timezoneShift*=-1;
    }
  }
  Calendar dateTime=new GregorianCalendar(TimeZone.getTimeZone("GMT"));
  dateTime.clear();
  dateTime.set(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)) - 1,Integer.parseInt(matcher.group(3)),Integer.parseInt(matcher.group(4)),Integer.parseInt(matcher.group(5)),Integer.parseInt(matcher.group(6)));
  if (!TextUtils.isEmpty(matcher.group(8))) {
    final BigDecimal bd=new BigDecimal("0." + matcher.group(8));
    dateTime.set(Calendar.MILLISECOND,bd.movePointRight(3).intValue());
  }
  long time=dateTime.getTimeInMillis();
  if (timezoneShift != 0) {
    time-=timezoneShift * 60000;
  }
  return time;
}
