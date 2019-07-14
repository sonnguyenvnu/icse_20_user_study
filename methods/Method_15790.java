@Bean @ConditionalOnMissingBean(OAuth2RequestBuilderFactory.class) public SimpleOAuth2RequestBuilderFactory simpleOAuth2RequestBuilderFactory(RequestBuilder requestBuilder,AuthenticationBuilderFactory authenticationBuilderFactory){
  SimpleOAuth2RequestBuilderFactory builderFactory=new SimpleOAuth2RequestBuilderFactory();
  builderFactory.setRequestBuilder(requestBuilder);
  builderFactory.setDefaultConvertHandler(new HswebResponseConvertSupport(authenticationBuilderFactory));
  builderFactory.setDefaultResponseJudge(new DefaultResponseJudge());
  return builderFactory;
}
