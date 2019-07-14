@ConditionalOnMissingBean(OAuth2ClientConfigRepository.class) @Bean public SimpleClientConfigRepository simpleClientService(OAuth2ClientDao oAuth2ClientDao){
  return new SimpleClientConfigRepository(oAuth2ClientDao);
}
