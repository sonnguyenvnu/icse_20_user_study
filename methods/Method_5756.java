/** 
 * Generates a cache key out of the given  {@link Uri}.
 * @param uri Uri of a content which the requested key is for.
 */
public static String generateKey(Uri uri){
  return uri.toString();
}
