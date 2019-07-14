/** 
 * Returns Bearer token or  {@code null} if not set.
 */
public String tokenAuthentication(){
  final String value=headers.get(HEADER_AUTHORIZATION);
  if (value == null) {
    return null;
  }
  final int ndx=value.indexOf("Bearer ");
  if (ndx == -1) {
    return null;
  }
  return value.substring(ndx + 7).trim();
}
