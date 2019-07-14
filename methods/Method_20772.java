/** 
 * e.g.: 4:20 PM
 */
public static @NonNull String shortTime(final @NonNull DateTime dateTime,final @NonNull Locale locale){
  return dateTime.toString(DateTimeFormat.shortTime().withLocale(locale).withZoneUTC());
}
