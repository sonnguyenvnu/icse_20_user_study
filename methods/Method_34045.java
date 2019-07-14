public void authorizeRequestToken(String requestToken,String verifier,Authentication authentication) throws AuthenticationException {
  OAuthProviderTokenImpl tokenImpl=readToken(requestToken);
  if (tokenImpl == null) {
    throw new InvalidOAuthTokenException("Invalid token: " + requestToken);
  }
 else   if (isExpired(tokenImpl)) {
    removeToken(requestToken);
    onTokenRemoved(tokenImpl);
    throw new ExpiredOAuthTokenException("Expired token.");
  }
 else   if (tokenImpl.isAccessToken()) {
    throw new InvalidOAuthTokenException("Request to authorize an access token.");
  }
  tokenImpl.setUserAuthentication(authentication);
  tokenImpl.setTimestamp(System.currentTimeMillis());
  tokenImpl.setVerifier(verifier);
  storeToken(requestToken,tokenImpl);
}
