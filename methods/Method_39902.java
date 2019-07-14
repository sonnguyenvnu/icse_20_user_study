/** 
 * Returns  {@code true} if request has JSON content type.
 */
public static boolean isJsonRequest(HttpServletRequest servletRequest){
  final String contentType=servletRequest.getContentType();
  if (contentType == null) {
    return false;
  }
  return contentType.equals(MimeTypes.MIME_APPLICATION_JSON);
}
