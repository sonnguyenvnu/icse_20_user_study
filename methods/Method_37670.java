/** 
 * Returns  {@code true} if given value is one of the registered MIME extensions.
 */
public static boolean isRegisteredExtension(final String extension){
  return MIME_TYPE_MAP.containsKey(extension);
}
