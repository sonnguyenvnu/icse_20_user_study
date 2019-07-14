public OAuthConsumerToken getUnauthorizedRequestToken(ProtectedResourceDetails details,String callback) throws OAuthRequestFailedException {
  URL requestTokenURL;
  try {
    requestTokenURL=new URL(details.getRequestTokenURL());
  }
 catch (  MalformedURLException e) {
    throw new IllegalStateException("Malformed URL for obtaining a request token.",e);
  }
  String httpMethod=details.getRequestTokenHttpMethod();
  Map<String,String> additionalParameters=new TreeMap<String,String>();
  if (details.isUse10a()) {
    additionalParameters.put(OAuthConsumerParameter.oauth_callback.toString(),callback);
  }
  Map<String,String> specifiedParams=details.getAdditionalParameters();
  if (specifiedParams != null) {
    additionalParameters.putAll(specifiedParams);
  }
  return getTokenFromProvider(details,requestTokenURL,httpMethod,null,additionalParameters);
}
