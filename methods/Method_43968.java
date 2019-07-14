/** 
 * Converts an rfc1123 formatted Date String to a Java Date rfc1123 format: EEE, dd MMM yyyy HH:mm:ss zzz
 * @param rfc1123FormattedDate
 * @return Date
 * @throws com.fasterxml.jackson.databind.exc.InvalidFormatException
 */
public static Date fromRfc1123DateString(String rfc1123FormattedDate,Locale locale) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
  SimpleDateFormat rfc1123DateFormat=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",locale);
  try {
    return rfc1123DateFormat.parse(rfc1123FormattedDate);
  }
 catch (  ParseException e) {
    throw new InvalidFormatException("Error parsing as date",rfc1123FormattedDate,Date.class);
  }
}
