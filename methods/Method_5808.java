/** 
 * Handles a redirect.
 * @param originalUrl The original URL.
 * @param location The Location header in the response.
 * @return The next URL.
 * @throws IOException If redirection isn't possible.
 */
private static URL handleRedirect(URL originalUrl,String location) throws IOException {
  if (location == null) {
    throw new ProtocolException("Null location redirect");
  }
  URL url=new URL(originalUrl,location);
  String protocol=url.getProtocol();
  if (!"https".equals(protocol) && !"http".equals(protocol)) {
    throw new ProtocolException("Unsupported protocol redirect: " + protocol);
  }
  return url;
}
