/** 
 * Returns a string with only the first character capitalized.
 */
public static @NonNull String sentenceCase(final @NonNull String str){
  return str.length() <= 1 ? str.toUpperCase(Locale.getDefault()) : str.substring(0,1).toUpperCase(Locale.getDefault()) + str.substring(1).toLowerCase(Locale.getDefault());
}
