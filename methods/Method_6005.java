/** 
 * Converts text to lower case using  {@link Locale#US}.
 * @param text The text to convert.
 * @return The lower case text, or null if {@code text} is null.
 */
public static @PolyNull String toLowerInvariant(@PolyNull String text){
  return text == null ? text : text.toLowerCase(Locale.US);
}
