/** 
 * e.g.: Jan 14, 2016 2:20 PM.
 */
public static @NonNull String mediumDateShortTime(final @NonNull DateTime dateTime,final @NonNull DateTimeZone dateTimeZone,final @NonNull Locale locale){
  final String mediumShortStyle=DateTimeFormat.patternForStyle("MS",locale);
  final DateTimeFormatter formatter=DateTimeFormat.forPattern(mediumShortStyle).withZone(dateTimeZone).withLocale(locale);
  return dateTime.toString(formatter);
}
