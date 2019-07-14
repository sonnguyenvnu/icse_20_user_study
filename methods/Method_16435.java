@Bean @ConditionalOnBean(UserTokenManager.class) public OAuth2GrantEventListener oAuth2GrantEventListener(UserTokenManager userTokenManager){
  return new OAuth2GrantEventListener(userTokenManager);
}
