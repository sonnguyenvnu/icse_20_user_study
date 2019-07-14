private List<OAuth2AccessToken> removeNulls(List<OAuth2AccessToken> accessTokens){
  List<OAuth2AccessToken> tokens=new ArrayList<OAuth2AccessToken>();
  for (  OAuth2AccessToken token : accessTokens) {
    if (token != null) {
      tokens.add(token);
    }
  }
  return tokens;
}
