/** 
 * e.g.: Dec 17, 2015 6:35:05 PM.
 */
public static @NonNull String mediumDateTime(final @NonNull DateTime dateTime,final @NonNull DateTimeZone dateTimeZone,final @NonNull Locale locale){
  return dateTime.toString(DateTimeFormat.mediumDateTime().withLocale(locale).withZone(dateTimeZone));
}
