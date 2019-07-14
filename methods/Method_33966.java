public OAuthConsumerToken getAccessToken(OAuthConsumerToken requestToken,String verifier) throws OAuthRequestFailedException {
  ProtectedResourceDetails details=getProtectedResourceDetailsService().loadProtectedResourceDetailsById(requestToken.getResourceId());
  return getAccessToken(details,requestToken,verifier);
}
