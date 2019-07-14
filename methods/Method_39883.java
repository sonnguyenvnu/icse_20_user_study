/** 
 * Returns <code>true</code> if client supports gzip encoding.
 */
public static boolean isGzipSupported(final HttpServletRequest request){
  String browserEncodings=request.getHeader(HEADER_ACCEPT_ENCODING);
  return (browserEncodings != null) && (browserEncodings.contains("gzip"));
}
