/** 
 * Parses a  {@code MutableInterval} from the specified string.<p> The String formats are described by  {@link ISODateTimeFormat#dateTimeParser()}and  {@link ISOPeriodFormat#standard()}, and may be 'datetime/datetime', 'datetime/period' or 'period/datetime'.
 * @param str  the string to parse, not null
 * @since 2.0
 */
public static MutableInterval parse(String str){
  return new MutableInterval(str);
}
