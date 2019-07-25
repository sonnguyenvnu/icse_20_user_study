/** 
 * Unlink the current user from the bridge.
 * @throws UnauthorizedException thrown if the user no longer exists
 */
public void unlink() throws IOException, ApiException {
  requireAuthentication();
  Result result=http.delete(getRelativeURL("config/whitelist/" + enc(username)));
  handleErrors(result);
}
