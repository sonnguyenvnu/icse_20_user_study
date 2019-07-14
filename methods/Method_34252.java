public void afterPropertiesSet() throws Exception {
  Assert.state(tokenGranter != null,"TokenGranter must be provided");
  Assert.state(clientDetailsService != null,"ClientDetailsService must be provided");
  defaultOAuth2RequestFactory=new DefaultOAuth2RequestFactory(getClientDetailsService());
  if (oAuth2RequestFactory == null) {
    oAuth2RequestFactory=defaultOAuth2RequestFactory;
  }
}
