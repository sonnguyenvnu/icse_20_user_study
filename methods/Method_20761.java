/** 
 * e.g.: Tuesday, June 20, 2017
 */
public static @NonNull String fullDate(final @NonNull DateTime dateTime,final @NonNull Locale locale){
  try {
    return dateTime.toString(DateTimeFormat.fullDate().withLocale(locale).withZoneUTC());
  }
 catch (  final IllegalArgumentException e) {
    return mediumDate(dateTime,locale);
  }
}
