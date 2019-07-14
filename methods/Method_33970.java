/** 
 * Create a configured URL.  If the HTTP method to access the resource is "POST" or "PUT" and the "Authorization" header isn't supported, then the OAuth parameters will be expected to be sent in the body of the request. Otherwise, you can assume that the given URL is ready to be used without further work.
 * @param url         The base URL.
 * @param accessToken The access token.
 * @param httpMethod The HTTP method.
 * @param additionalParameters Any additional request parameters.
 * @return The configured URL.
 */
public URL configureURLForProtectedAccess(URL url,OAuthConsumerToken accessToken,String httpMethod,Map<String,String> additionalParameters) throws OAuthRequestFailedException {
  return configureURLForProtectedAccess(url,accessToken,getProtectedResourceDetailsService().loadProtectedResourceDetailsById(accessToken.getResourceId()),httpMethod,additionalParameters);
}
