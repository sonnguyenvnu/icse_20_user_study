/** 
 * Strips content type information from requests data header.
 * @param dataHeader data header string
 * @return content type or an empty string if no content type defined
 */
private String getContentType(final String dataHeader){
  String token="Content-Type:";
  int start=dataHeader.indexOf(token);
  if (start == -1) {
    return StringPool.EMPTY;
  }
  start+=token.length();
  return dataHeader.substring(start).trim();
}
