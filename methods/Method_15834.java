@Override public OAuth2SessionBuilder create(String serverId){
  OAuth2ServerConfig configEntity=oAuth2ServerConfigService.findById(serverId);
  if (null == configEntity || !DataStatus.STATUS_ENABLED.equals(configEntity.getStatus())) {
    throw new NotFoundException("server not found!");
  }
  return new SimpleOAuth2SessionBuilder(oAuth2UserTokenService,configEntity,oAuth2RequestBuilderFactory,lockManager.getReadWriteLock("oauth2-server-lock." + serverId));
}
