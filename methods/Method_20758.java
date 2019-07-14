/** 
 * e.g.: December 2015.
 */
public static @NonNull String estimatedDeliveryOn(final @NonNull DateTime dateTime,final @NonNull Locale locale){
  return dateTime.toString(DateTimeFormat.forPattern(localePattern(locale)).withLocale(locale).withZoneUTC());
}
