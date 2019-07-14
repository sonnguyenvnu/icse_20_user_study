/** 
 * Check media type like "application/json".
 * @param mediaType media type
 * @return true if the media type is valid
 */
protected boolean hasMatchingMediaType(MediaType mediaType){
  if (mediaType != null) {
    String subtype=mediaType.getSubtype();
    return (("json".equalsIgnoreCase(subtype)) || (subtype.endsWith("+json")) || ("javascript".equals(subtype)) || ("x-javascript".equals(subtype)) || ("x-json".equals(subtype)) || ("x-www-form-urlencoded".equalsIgnoreCase(subtype)) || (subtype.endsWith("x-www-form-urlencoded")));
  }
  return true;
}
