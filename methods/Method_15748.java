@Override public void onApplicationEvent(AuthorizationSuccessEvent event){
  UserToken token=UserTokenHolder.currentToken();
  String tokenType=(String)event.getParameter("token_type").orElse(defaultTokenType);
  if (token != null) {
    userTokenManager.signOutByToken(token.getToken());
  }
  GeneratedToken newToken=userTokenGenerators.stream().filter(generator -> generator.getSupportTokenType().equals(tokenType)).findFirst().orElseThrow(() -> new UnsupportedOperationException(tokenType)).generate(event.getAuthentication());
  userTokenManager.signIn(newToken.getToken(),newToken.getType(),event.getAuthentication().getUser().getId(),newToken.getTimeout());
  event.getResult().putAll(newToken.getResponse());
}
