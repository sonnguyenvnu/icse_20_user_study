public OAuth2UserTokenEntity selectByAccessToken(String accessToken){
  Assert.notNull(accessToken,"token can not be null!");
  return createQuery().where(OAuth2UserTokenEntity.accessToken,accessToken).single();
}
