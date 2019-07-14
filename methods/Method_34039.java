protected OAuthProviderTokenImpl readToken(String token){
  return tokenStore.get(token);
}
