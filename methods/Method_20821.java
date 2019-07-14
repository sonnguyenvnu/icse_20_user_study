/** 
 * Returns a formatted number for the specified locale.
 */
public static @NonNull String format(final int value,final @NonNull Locale locale){
  return NumberFormat.getIntegerInstance(locale).format(value);
}
