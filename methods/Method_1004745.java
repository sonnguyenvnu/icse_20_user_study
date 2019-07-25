/** 
 * Implementation method that returns the text to send via the Authenticate header on the next request.
 */
private String authenticate(Credentials credentials,URI requestURI) throws AuthenticationException {
  if (!(credentials instanceof SpnegoCredentials)) {
    throw new AuthenticationException("Invalid credentials type provided to " + this.getClass().getName() + "." + "Expected " + SpnegoCredentials.class.getName() + " but got " + credentials.getClass().getName());
  }
  final SpnegoCredentials spnegoCredentials=(SpnegoCredentials)credentials;
  try {
    initializeNegotiator(requestURI,spnegoCredentials);
    return getNegotiateToken();
  }
 catch (  GSSException e) {
    throw new AuthenticationException("Could not authenticate",e);
  }
catch (  UnknownHostException e) {
    throw new AuthenticationException("Could not authenticate",e);
  }
}
