/** 
 * @return true if the mime type is one of our whitelisted mimetypes that we support beyondwhat the native platform supports.
 */
public static boolean isNonNativeSupportedMimeType(String mimeType){
  return ADDITIONAL_ALLOWED_MIME_TYPES.containsValue(mimeType);
}
