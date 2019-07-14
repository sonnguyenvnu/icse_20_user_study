/** 
 * Converts a datetime string as returned from the Ripple REST API into a java date object. The string is the UTC time in format yyyy-MM-dd'T'hh:mm:ss.SSS'Z' e.g. 2015-06-13T11:45:20.102Z
 * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
 */
public static Date ToDate(final String datetime) throws ParseException {
  final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
  format.setTimeZone(TimeZone.getTimeZone("UTC"));
  return format.parse(datetime);
}
