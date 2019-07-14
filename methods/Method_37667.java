/** 
 * Registers MIME type for provided extension. Existing extension type will be overridden.
 */
public static void registerMimeType(final String ext,final String mimeType){
  MIME_TYPE_MAP.put(ext,mimeType);
}
