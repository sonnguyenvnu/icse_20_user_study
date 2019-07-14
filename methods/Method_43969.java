/** 
 * Converts an RFC3339 formatted Date String to a Java Date RFC3339 format: yyyy-MM-dd HH:mm:ss
 * @param rfc3339FormattedDate RFC3339 formatted Date
 * @return an {@link Date} object
 * @throws InvalidFormatException the RFC3339 formatted Date is invalid or cannot be parsed.
 * @see <a href="https://tools.ietf.org/html/rfc3339">The Internet Society - RFC 3339</a>
 */
public static Date fromRfc3339DateString(String rfc3339FormattedDate) throws InvalidFormatException {
  SimpleDateFormat rfc3339DateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  try {
    return rfc3339DateFormat.parse(rfc3339FormattedDate);
  }
 catch (  ParseException e) {
    throw new InvalidFormatException("Error parsing as date",rfc3339FormattedDate,Date.class);
  }
}
