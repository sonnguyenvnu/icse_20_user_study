/** 
 * Parses a  {@code MutableDateTime} from the specified string using a formatter.
 * @param str  the string to parse, not null
 * @param formatter  the formatter to use, not null
 * @since 2.0
 */
public static MutableDateTime parse(String str,DateTimeFormatter formatter){
  return formatter.parseDateTime(str).toMutableDateTime();
}
