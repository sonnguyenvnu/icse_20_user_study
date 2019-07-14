@Override @Cacheable(cacheNames="oauth2-user-token",key="'a-t:'+#accessToken") public AccessTokenInfo findByAccessToken(String accessToken){
  return Optional.ofNullable(selectByAccessToken(accessToken)).map(tokenInfoMapping()).orElse(null);
}
