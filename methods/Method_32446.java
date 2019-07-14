/** 
 * Parses an  {@code Interval} from the specified string.<p> The String formats are described by  {@link ISODateTimeFormat#dateTimeParser()}and  {@link ISOPeriodFormat#standard()}, and may be 'datetime/datetime', 'datetime/period' or 'period/datetime'. <p> This method operates by parsing in the default time-zone. Any offset contained within the string being parsed will be normalised to the offset of the default time-zone. See also  {@link #parseWithOffset(String)}.
 * @param str  the string to parse, not null
 * @since 2.0
 */
public static Interval parse(String str){
  return new Interval(str);
}
