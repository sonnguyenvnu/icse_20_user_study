/** 
 * <p>Gets a date/time formatter instance using the specified style, time zone and locale.</p>
 * @param dateStyle date style: FULL, LONG, MEDIUM, or SHORT, null indicates no date in format
 * @param timeStyle time style: FULL, LONG, MEDIUM, or SHORT, null indicates no time in format
 * @param timeZone  optional time zone, overrides time zone offormatted date, null means use default Locale
 * @param locale    optional locale, overrides system locale
 * @return a localized standard date/time formatter
 * @throws IllegalArgumentException if the Locale has no date/timepattern defined
 */
private F getDateTimeInstance(final Integer dateStyle,final Integer timeStyle,final TimeZone timeZone,Locale locale){
  if (locale == null) {
    locale=Locale.getDefault();
  }
  final String pattern=getPatternForStyle(dateStyle,timeStyle,locale);
  return getInstance(pattern,timeZone,locale);
}
