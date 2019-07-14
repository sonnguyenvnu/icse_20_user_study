/** 
 * Extracts media-type from value of "Content Type" header.
 */
public static String extractMediaType(final String contentType){
  int index=contentType.indexOf(';');
  if (index == -1) {
    return contentType;
  }
  return contentType.substring(0,index);
}
