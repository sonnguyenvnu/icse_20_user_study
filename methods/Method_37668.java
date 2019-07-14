/** 
 * Simply returns MIME type or <code>null</code> if no type is found.
 */
public static String lookupMimeType(final String ext){
  return MIME_TYPE_MAP.get(ext.toLowerCase());
}
