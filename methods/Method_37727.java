/** 
 * Formats time to HTTP date/time format. Note that number of milliseconds is lost.
 */
public static String formatHttpDate(final long millis){
  final Date date=new Date(millis);
  return HTTP_DATE_FORMAT.format(date);
}
