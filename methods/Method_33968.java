public InputStream readProtectedResource(URL url,OAuthConsumerToken accessToken,String httpMethod) throws OAuthRequestFailedException {
  if (accessToken == null) {
    throw new OAuthRequestFailedException("A valid access token must be supplied.");
  }
  ProtectedResourceDetails resourceDetails=getProtectedResourceDetailsService().loadProtectedResourceDetailsById(accessToken.getResourceId());
  if ((!resourceDetails.isAcceptsAuthorizationHeader()) && !"POST".equalsIgnoreCase(httpMethod) && !"PUT".equalsIgnoreCase(httpMethod)) {
    throw new IllegalArgumentException("Protected resource " + resourceDetails.getId() + " cannot be accessed with HTTP method " + httpMethod + " because the OAuth provider doesn't accept the OAuth Authorization header.");
  }
  return readResource(resourceDetails,url,httpMethod,accessToken,resourceDetails.getAdditionalParameters(),null);
}
