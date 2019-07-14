public OAuthProviderToken getToken(String token) throws AuthenticationException {
  OAuthProviderTokenImpl tokenImpl=readToken(token);
  if (tokenImpl == null) {
    throw new InvalidOAuthTokenException("Invalid token: " + token);
  }
 else   if (isExpired(tokenImpl)) {
    removeToken(token);
    onTokenRemoved(tokenImpl);
    throw new ExpiredOAuthTokenException("Expired token.");
  }
  return tokenImpl;
}
