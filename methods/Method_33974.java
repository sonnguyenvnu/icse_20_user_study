/** 
 * Get the consumer token with the given parameters and URL. The determination of whether the retrieved token is an access token depends on whether a request token is provided.
 * @param details      The resource details.
 * @param tokenURL     The token URL.
 * @param httpMethod   The http method.
 * @param requestToken The request token, or null if none.
 * @param additionalParameters The additional request parameter.
 * @return The token.
 */
protected OAuthConsumerToken getTokenFromProvider(ProtectedResourceDetails details,URL tokenURL,String httpMethod,OAuthConsumerToken requestToken,Map<String,String> additionalParameters){
  boolean isAccessToken=requestToken != null;
  if (!isAccessToken) {
    requestToken=new OAuthConsumerToken();
  }
  TreeMap<String,String> requestHeaders=new TreeMap<String,String>();
  if ("POST".equalsIgnoreCase(httpMethod)) {
    requestHeaders.put("Content-Type","application/x-www-form-urlencoded");
  }
  InputStream inputStream=readResource(details,tokenURL,httpMethod,requestToken,additionalParameters,requestHeaders);
  String tokenInfo;
  try {
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    byte[] buffer=new byte[1024];
    int len=inputStream.read(buffer);
    while (len >= 0) {
      out.write(buffer,0,len);
      len=inputStream.read(buffer);
    }
    tokenInfo=new String(out.toByteArray(),"UTF-8");
  }
 catch (  IOException e) {
    throw new OAuthRequestFailedException("Unable to read the token.",e);
  }
  StringTokenizer tokenProperties=new StringTokenizer(tokenInfo,"&");
  Map<String,String> tokenPropertyValues=new TreeMap<String,String>();
  while (tokenProperties.hasMoreElements()) {
    try {
      String tokenProperty=(String)tokenProperties.nextElement();
      int equalsIndex=tokenProperty.indexOf('=');
      if (equalsIndex > 0) {
        String propertyName=OAuthCodec.oauthDecode(tokenProperty.substring(0,equalsIndex));
        String propertyValue=OAuthCodec.oauthDecode(tokenProperty.substring(equalsIndex + 1));
        tokenPropertyValues.put(propertyName,propertyValue);
      }
 else {
        tokenProperty=OAuthCodec.oauthDecode(tokenProperty);
        tokenPropertyValues.put(tokenProperty,null);
      }
    }
 catch (    DecoderException e) {
      throw new OAuthRequestFailedException("Unable to decode token parameters.");
    }
  }
  String tokenValue=tokenPropertyValues.remove(OAuthProviderParameter.oauth_token.toString());
  if (tokenValue == null) {
    throw new OAuthRequestFailedException("OAuth provider failed to return a token.");
  }
  String tokenSecret=tokenPropertyValues.remove(OAuthProviderParameter.oauth_token_secret.toString());
  if (tokenSecret == null) {
    throw new OAuthRequestFailedException("OAuth provider failed to return a token secret.");
  }
  OAuthConsumerToken consumerToken=new OAuthConsumerToken();
  consumerToken.setValue(tokenValue);
  consumerToken.setSecret(tokenSecret);
  consumerToken.setResourceId(details.getId());
  consumerToken.setAccessToken(isAccessToken);
  if (!tokenPropertyValues.isEmpty()) {
    consumerToken.setAdditionalParameters(tokenPropertyValues);
  }
  return consumerToken;
}
