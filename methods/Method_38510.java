/** 
 * Enables token-based authentication.
 */
public HttpRequest tokenAuthentication(final String token){
  if (token != null) {
    headerOverwrite(HEADER_AUTHORIZATION,"Bearer " + token);
  }
  return this;
}
