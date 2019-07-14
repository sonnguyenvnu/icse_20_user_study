/** 
 * <p>Gets a date formatter instance using the specified style, time zone and locale.</p>
 * @param style    date style: FULL, LONG, MEDIUM, or SHORT
 * @param timeZone optional time zone, overrides time zone offormatted date
 * @param locale   optional locale, overrides system locale
 * @return a localized standard date formatter
 * @throws IllegalArgumentException if the Locale has no datepattern defined
 */
public static FastDateFormat getDateInstance(final int style,final TimeZone timeZone,final Locale locale){
  return cache.getDateInstance(style,timeZone,locale);
}
