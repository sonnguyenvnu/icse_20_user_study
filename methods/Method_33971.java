/** 
 * Internal use of configuring the URL for protected access, the resource details already having been loaded.
 * @param url          The URL.
 * @param requestToken The request token.
 * @param details      The details.
 * @param httpMethod   The http method.
 * @param additionalParameters Any additional request parameters.
 * @return The configured URL.
 */
protected URL configureURLForProtectedAccess(URL url,OAuthConsumerToken requestToken,ProtectedResourceDetails details,String httpMethod,Map<String,String> additionalParameters){
  String file;
  if (!"POST".equalsIgnoreCase(httpMethod) && !"PUT".equalsIgnoreCase(httpMethod) && !details.isAcceptsAuthorizationHeader()) {
    StringBuilder fileb=new StringBuilder(url.getPath());
    String queryString=getOAuthQueryString(details,requestToken,url,httpMethod,additionalParameters);
    fileb.append('?').append(queryString);
    file=fileb.toString();
  }
 else {
    file=url.getFile();
  }
  try {
    if ("http".equalsIgnoreCase(url.getProtocol())) {
      URLStreamHandler streamHandler=getStreamHandlerFactory().getHttpStreamHandler(details,requestToken,this,httpMethod,additionalParameters);
      return new URL(url.getProtocol(),url.getHost(),url.getPort(),file,streamHandler);
    }
 else     if ("https".equalsIgnoreCase(url.getProtocol())) {
      URLStreamHandler streamHandler=getStreamHandlerFactory().getHttpsStreamHandler(details,requestToken,this,httpMethod,additionalParameters);
      return new URL(url.getProtocol(),url.getHost(),url.getPort(),file,streamHandler);
    }
 else {
      throw new OAuthRequestFailedException("Unsupported OAuth protocol: " + url.getProtocol());
    }
  }
 catch (  MalformedURLException e) {
    throw new IllegalStateException(e);
  }
}
