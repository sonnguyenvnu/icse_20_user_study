@Override public OAuth2Session byAuthorizationCode(String code){
  AuthorizationCodeSession authorizationCodeSession=new AuthorizationCodeSession();
  authorizationCodeSession.setCode(code);
  authorizationCodeSession.setRequestBuilderFactory(requestBuilderFactory);
  authorizationCodeSession.setServerConfig(serverConfig);
  authorizationCodeSession.init();
  return authorizationCodeSession;
}
