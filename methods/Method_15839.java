protected Consumer<AccessTokenInfo> createOnTokenChanged(Supplier<AccessTokenInfo> tokenGetter,String grantType){
  return token -> {
    readWriteLock.writeLock().lock();
    AccessTokenInfo tokenInfo=tokenGetter.get();
    try {
      token.setGrantType(grantType);
      token.setServerId(serverConfig.getId());
      if (tokenInfo != null) {
        token.setId(tokenInfo.getId());
        token.setUpdateTime(System.currentTimeMillis());
        oAuth2UserTokenRepository.update(tokenInfo.getId(),token);
      }
 else {
        token.setCreateTime(System.currentTimeMillis());
        token.setUpdateTime(System.currentTimeMillis());
        oAuth2UserTokenRepository.insert(token);
      }
    }
  finally {
      readWriteLock.writeLock().unlock();
    }
  }
;
}
