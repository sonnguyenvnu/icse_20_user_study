/** 
 * Converts a  {@link String} to an {@link Integer}, or null if the integer cannot be parsed.
 */
public static @Nullable Integer toInteger(final @Nullable String s){
  if (s != null) {
    try {
      return Integer.parseInt(s);
    }
 catch (    final @NonNull NumberFormatException e) {
      return null;
    }
  }
  return null;
}
