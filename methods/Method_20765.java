/** 
 * e.g.: Dec 17, 2015.
 */
public static @NonNull String mediumDate(final @NonNull DateTime dateTime,final @NonNull Locale locale){
  return dateTime.toString(DateTimeFormat.mediumDate().withLocale(locale).withZoneUTC());
}
