/** 
 * Parses the HTTP date/time format. Returns <code>-1</code> if given string is invalid.
 */
public static long parseHttpTime(final String time){
  if (time == null) {
    return -1;
  }
  try {
    return TimeUtil.HTTP_DATE_FORMAT.parse(time).getTime();
  }
 catch (  ParseException e) {
    return -1;
  }
}
