/** 
 * e.g.: June 20, 2017
 */
public static @NonNull String longDate(final @NonNull DateTime dateTime,final @NonNull Locale locale){
  return dateTime.toString(DateTimeFormat.longDate().withLocale(locale).withZoneUTC());
}
