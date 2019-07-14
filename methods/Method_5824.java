/** 
 * Builds a  {@link Uri} for the specified raw resource identifier.
 * @param rawResourceId A raw resource identifier (i.e. a constant defined in {@code R.raw}).
 * @return The corresponding {@link Uri}.
 */
public static Uri buildRawResourceUri(int rawResourceId){
  return Uri.parse(RAW_RESOURCE_SCHEME + ":///" + rawResourceId);
}
