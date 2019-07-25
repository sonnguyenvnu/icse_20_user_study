/** 
 * Authenticate on the bridge as the specified user. This function verifies that the specified username is valid and will use it for subsequent requests if it is, otherwise an UnauthorizedException is thrown and the internal username is not changed.
 * @param username username to authenticate
 * @throws UnauthorizedException thrown if authentication failed
 */
public void authenticate(String username) throws IOException, ApiException {
  try {
    this.username=username;
    getLights();
  }
 catch (  Exception e) {
    this.username=null;
    throw new UnauthorizedException(e.toString());
  }
}
