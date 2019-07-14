public OAuth2Authentication readAuthentication(String token){
  return this.authenticationStore.get(token);
}
