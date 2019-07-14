private TokenGranter tokenGranter(){
  if (tokenGranter == null) {
    tokenGranter=new TokenGranter(){
      @Override public OAuth2AccessToken grant(      String grantType,      TokenRequest tokenRequest){
        if (delegate == null) {
          delegate=new CompositeTokenGranter(getDefaultTokenGranters());
        }
        return delegate.grant(grantType,tokenRequest);
      }
    }
;
  }
  return tokenGranter;
}
