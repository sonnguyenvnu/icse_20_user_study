/** 
 * Like  {@link #parseShortSecond(String)} using additional timezone information provided in anoffset String, like "+0100" for CET.
 * @throws ParseException 
 */
public Calendar parse(final String timeString,final String UTCOffset) throws ParseException {
  if (timeString == null || timeString.isEmpty()) {
    return Calendar.getInstance(UTCtimeZone);
  }
  if (UTCOffset == null || UTCOffset.isEmpty()) {
    return Calendar.getInstance(UTCtimeZone);
  }
  return parse(timeString,UTCDiff(UTCOffset));
}
