/** 
 * Parses a  {@code MutablePeriod} from the specified string using a formatter.
 * @param str  the string to parse, not null
 * @param formatter  the formatter to use, not null
 * @since 2.0
 */
public static MutablePeriod parse(String str,PeriodFormatter formatter){
  return formatter.parsePeriod(str).toMutablePeriod();
}
