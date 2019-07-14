/** 
 * Extracts encoding from a given content type.
 * @param contentType     content type.
 * @param defaultEncoding Default encoding to be used if extract returns {@code null}. If defaultEncoding is  {@code null},  {@link JoddCore#encoding} will be used.
 * @return Encoding from the content type.
 * @see #extractEncoding(String)
 */
public static String extractEncoding(final String contentType,String defaultEncoding){
  String encoding=extractEncoding(contentType);
  if (encoding == null) {
    if (defaultEncoding == null) {
      defaultEncoding=JoddCore.encoding;
    }
    encoding=defaultEncoding;
  }
  return encoding;
}
