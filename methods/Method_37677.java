/** 
 * Encodes the given URI path segment with the given encoding.
 */
public static String encodePathSegment(final String segment,final String encoding){
  return encodeUriComponent(segment,encoding,URIPart.PATH_SEGMENT);
}
