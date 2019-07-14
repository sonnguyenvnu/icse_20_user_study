/** 
 * Converts text to upper case using  {@link Locale#US}.
 * @param text The text to convert.
 * @return The upper case text, or null if {@code text} is null.
 */
public static @PolyNull String toUpperInvariant(@PolyNull String text){
  return text == null ? text : text.toUpperCase(Locale.US);
}
