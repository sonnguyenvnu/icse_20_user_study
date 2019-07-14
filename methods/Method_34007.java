/** 
 * Default implementation returns the user authentication associated with the auth token, if the token is provided. Otherwise, the consumer authentication is returned.
 * @param request The request that was successfully authenticated.
 * @param authentication The consumer authentication (details about how the request was authenticated).
 * @param authToken The OAuth token associated with the authentication. This token MAY be null if no authenticated token was needed to successfullyauthenticate the request (for example, in the case of 2-legged OAuth).
 * @return The authentication.
 */
public Authentication createAuthentication(HttpServletRequest request,ConsumerAuthentication authentication,OAuthAccessProviderToken authToken){
  if (authToken != null) {
    Authentication userAuthentication=authToken.getUserAuthentication();
    if (userAuthentication instanceof AbstractAuthenticationToken) {
      ((AbstractAuthenticationToken)userAuthentication).setDetails(new OAuthAuthenticationDetails(request,authentication.getConsumerDetails()));
    }
    return userAuthentication;
  }
  return authentication;
}
