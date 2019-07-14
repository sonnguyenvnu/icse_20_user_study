/** 
 * e.g.: Jan 14, 2016 2:20 PM.
 */
public static @NonNull String mediumDateShortTime(final @NonNull DateTime dateTime){
  return mediumDateShortTime(dateTime,DateTimeZone.getDefault(),Locale.getDefault());
}
