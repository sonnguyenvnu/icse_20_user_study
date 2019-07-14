/** 
 * Extracts MIME type from content type.
 * @param contentType MIME type.
 * @return MIME type for the given content type.
 */
public static String extractMimeType(final String contentType){
  final int ndx=contentType.indexOf(';');
  final String mime;
  if (ndx != -1) {
    mime=contentType.substring(0,ndx);
  }
 else {
    mime=contentType;
  }
  return mime;
}
