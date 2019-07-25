/** 
 * Append path segments to path
 * @param segments path segments
 * @return this
 * @throws IllegalArgumentException if any path segments are null
 */
public UriBuilder segment(String... segments) throws IllegalArgumentException {
  checkSsp();
  if (segments == null)   throw new IllegalArgumentException("Segments parameter is null");
  for (  String segment : segments)   appendPath(segment,true);
  return this;
}
