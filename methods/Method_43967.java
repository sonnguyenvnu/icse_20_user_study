/** 
 * Converts an ISO 8601 formatted Date String to a Java Date ISO 8601 format: yyyy-MM-dd'T'HH:mm:ss
 * @param iso8601FormattedDate
 * @return Date
 * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
 */
public static Date fromISO8601DateString(String iso8601FormattedDate) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
  SimpleDateFormat iso8601Format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
  try {
    return iso8601Format.parse(iso8601FormattedDate);
  }
 catch (  ParseException e) {
    throw new InvalidFormatException("Error parsing as date",iso8601FormattedDate,Date.class);
  }
}
