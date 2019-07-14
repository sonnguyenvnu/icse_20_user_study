/** 
 * Create a DateBuilder, with initial settings for the current date and time in the given timezone and locale.
 */
public static DateBuilder newDateInTimeZoneAndLocale(TimeZone tz,Locale lc){
  return new DateBuilder(tz,lc);
}
