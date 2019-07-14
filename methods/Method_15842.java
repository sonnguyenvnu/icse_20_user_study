@Override public OAuth2Session byAccessToken(String accessToken){
  Supplier<AccessTokenInfo> supplier=() -> oAuth2UserTokenRepository.findByAccessToken(accessToken);
  AccessTokenInfo tokenInfo=supplier.get();
  if (tokenInfo == null) {
    throw new NotFoundException("access_token not found");
  }
  CachedOAuth2Session session=new CachedOAuth2Session(tokenInfo);
  session.setServerConfig(serverConfig);
  session.setRequestBuilderFactory(requestBuilderFactory);
  session.onTokenChanged(createOnTokenChanged(supplier,null));
  session.init();
  return session;
}
