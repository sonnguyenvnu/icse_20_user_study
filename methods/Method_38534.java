/** 
 * Extract keep-alive timeout.
 */
public static String extractKeepAliveTimeout(final String keepAlive){
  return extractHeaderParameter(keepAlive,"timeout",',');
}
