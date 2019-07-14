public void init(){
  Assert.notNull(requestBuilderFactory,"requestBuilderFactory can not be null!");
  Assert.notNull(serverConfig,"configEntity can not be null!");
  accessTokenRequest=createRequest(serverConfig.getAccessTokenUrl());
  applyBasicAuthParam(accessTokenRequest);
}
