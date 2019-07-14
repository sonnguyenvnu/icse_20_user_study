/** 
 * Returns Bearer token.
 */
public static String resolveAuthBearerToken(final HttpServletRequest request){
  String header=request.getHeader(HEADER_AUTHORIZATION);
  if (header == null) {
    return null;
  }
  int ndx=header.indexOf("Bearer ");
  if (ndx == -1) {
    return null;
  }
  return header.substring(ndx + 7).trim();
}
