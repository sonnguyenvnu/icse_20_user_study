public OAuthConsumerToken getAccessToken(ProtectedResourceDetails details,OAuthConsumerToken requestToken,String verifier){
  URL accessTokenURL;
  try {
    accessTokenURL=new URL(details.getAccessTokenURL());
  }
 catch (  MalformedURLException e) {
    throw new IllegalStateException("Malformed URL for obtaining an access token.",e);
  }
  String httpMethod=details.getAccessTokenHttpMethod();
  Map<String,String> additionalParameters=new TreeMap<String,String>();
  if (details.isUse10a()) {
    if (verifier == null) {
      throw new UnverifiedRequestTokenException("Unverified request token: " + requestToken);
    }
    additionalParameters.put(OAuthConsumerParameter.oauth_verifier.toString(),verifier);
  }
  Map<String,String> specifiedParams=details.getAdditionalParameters();
  if (specifiedParams != null) {
    additionalParameters.putAll(specifiedParams);
  }
  return getTokenFromProvider(details,accessTokenURL,httpMethod,requestToken,additionalParameters);
}
