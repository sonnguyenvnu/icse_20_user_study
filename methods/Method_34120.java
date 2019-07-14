public static OAuth2AccessToken valueOf(Map<String,String> tokenParams){
  DefaultOAuth2AccessToken token=new DefaultOAuth2AccessToken(tokenParams.get(ACCESS_TOKEN));
  if (tokenParams.containsKey(EXPIRES_IN)) {
    long expiration=0;
    try {
      expiration=Long.parseLong(String.valueOf(tokenParams.get(EXPIRES_IN)));
    }
 catch (    NumberFormatException e) {
    }
    token.setExpiration(new Date(System.currentTimeMillis() + (expiration * 1000L)));
  }
  if (tokenParams.containsKey(REFRESH_TOKEN)) {
    String refresh=tokenParams.get(REFRESH_TOKEN);
    DefaultOAuth2RefreshToken refreshToken=new DefaultOAuth2RefreshToken(refresh);
    token.setRefreshToken(refreshToken);
  }
  if (tokenParams.containsKey(SCOPE)) {
    Set<String> scope=new TreeSet<String>();
    for (StringTokenizer tokenizer=new StringTokenizer(tokenParams.get(SCOPE)," ,"); tokenizer.hasMoreTokens(); ) {
      scope.add(tokenizer.nextToken());
    }
    token.setScope(scope);
  }
  if (tokenParams.containsKey(TOKEN_TYPE)) {
    token.setTokenType(tokenParams.get(TOKEN_TYPE));
  }
  return token;
}
