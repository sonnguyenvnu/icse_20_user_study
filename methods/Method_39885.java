/** 
 * Decodes the "Authorization" header and retrieves the password from it. Returns <code>null</code> if the header is not present.
 */
public static String resolveAuthPassword(final HttpServletRequest request){
  String header=request.getHeader(HEADER_AUTHORIZATION);
  if (header == null) {
    return null;
  }
  if (!header.contains("Basic ")) {
    return null;
  }
  final String encoded=header.substring(header.indexOf(' ') + 1);
  final String decoded=new String(Base64.decode(encoded));
  return decoded.substring(decoded.indexOf(':') + 1);
}
