@Override public OAuth2AccessToken grant(String grantType,TokenRequest tokenRequest){
  OAuth2AccessToken token=super.grant(grantType,tokenRequest);
  if (token != null) {
    DefaultOAuth2AccessToken norefresh=new DefaultOAuth2AccessToken(token);
    if (!allowRefresh) {
      norefresh.setRefreshToken(null);
    }
    token=norefresh;
  }
  return token;
}
