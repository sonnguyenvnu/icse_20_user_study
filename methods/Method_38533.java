/** 
 * @see #extractHeaderParameter(String,String,char)
 */
public static String extractContentTypeCharset(final String contentType){
  return extractHeaderParameter(contentType,"charset",';');
}
