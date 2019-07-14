public OAuth2Authentication readAuthenticationForRefreshToken(String token){
  return this.refreshTokenAuthenticationStore.get(token);
}
