/** 
 * Read a resource.
 * @param details The details of the resource.
 * @param url The URL of the resource.
 * @param httpMethod The http method.
 * @param token The token.
 * @param additionalParameters Any additional request parameters.
 * @param additionalRequestHeaders Any additional request parameters.
 * @return The resource.
 */
protected InputStream readResource(ProtectedResourceDetails details,URL url,String httpMethod,OAuthConsumerToken token,Map<String,String> additionalParameters,Map<String,String> additionalRequestHeaders){
  url=configureURLForProtectedAccess(url,token,details,httpMethod,additionalParameters);
  String realm=details.getAuthorizationHeaderRealm();
  boolean sendOAuthParamsInRequestBody=!details.isAcceptsAuthorizationHeader() && (("POST".equalsIgnoreCase(httpMethod) || "PUT".equalsIgnoreCase(httpMethod)));
  HttpURLConnection connection=openConnection(url);
  try {
    connection.setRequestMethod(httpMethod);
  }
 catch (  ProtocolException e) {
    throw new IllegalStateException(e);
  }
  Map<String,String> reqHeaders=details.getAdditionalRequestHeaders();
  if (reqHeaders != null) {
    for (    Map.Entry<String,String> requestHeader : reqHeaders.entrySet()) {
      connection.setRequestProperty(requestHeader.getKey(),requestHeader.getValue());
    }
  }
  if (additionalRequestHeaders != null) {
    for (    Map.Entry<String,String> requestHeader : additionalRequestHeaders.entrySet()) {
      connection.setRequestProperty(requestHeader.getKey(),requestHeader.getValue());
    }
  }
  int responseCode;
  String responseMessage;
  try {
    connection.setDoOutput(sendOAuthParamsInRequestBody);
    connection.connect();
    if (sendOAuthParamsInRequestBody) {
      String queryString=getOAuthQueryString(details,token,url,httpMethod,additionalParameters);
      OutputStream out=connection.getOutputStream();
      out.write(queryString.getBytes("UTF-8"));
      out.flush();
      out.close();
    }
    responseCode=connection.getResponseCode();
    responseMessage=connection.getResponseMessage();
    if (responseMessage == null) {
      responseMessage="Unknown Error";
    }
  }
 catch (  IOException e) {
    throw new OAuthRequestFailedException("OAuth connection failed.",e);
  }
  if (responseCode >= 200 && responseCode < 300) {
    try {
      return connection.getInputStream();
    }
 catch (    IOException e) {
      throw new OAuthRequestFailedException("Unable to get the input stream from a successful response.",e);
    }
  }
 else   if (responseCode == 400) {
    throw new OAuthRequestFailedException("OAuth authentication failed: " + responseMessage);
  }
 else   if (responseCode == 401) {
    String authHeaderValue=connection.getHeaderField("WWW-Authenticate");
    if (authHeaderValue != null) {
      Map<String,String> headerEntries=StringSplitUtils.splitEachArrayElementAndCreateMap(StringSplitUtils.splitIgnoringQuotes(authHeaderValue,','),"=","\"");
      String requiredRealm=headerEntries.get("realm");
      if ((requiredRealm != null) && (!requiredRealm.equals(realm))) {
        throw new InvalidOAuthRealmException(String.format("Invalid OAuth realm. Provider expects \"%s\", when the resource details specify \"%s\".",requiredRealm,realm),requiredRealm);
      }
    }
    throw new OAuthRequestFailedException("OAuth authentication failed: " + responseMessage);
  }
 else {
    throw new OAuthRequestFailedException(String.format("Invalid response code %s (%s).",responseCode,responseMessage));
  }
}
