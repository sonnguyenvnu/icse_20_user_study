protected void onValidSignature(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException {
  ConsumerAuthentication authentication=(ConsumerAuthentication)SecurityContextHolder.getContext().getAuthentication();
  OAuthProviderToken authToken=createOAuthToken(authentication);
  if (!authToken.getConsumerKey().equals(authentication.getConsumerDetails().getConsumerKey())) {
    throw new IllegalStateException("The consumer key associated with the created auth token is not valid for the authenticated consumer.");
  }
  String tokenValue=authToken.getValue();
  String callback=authentication.getOAuthParameters().get(OAuthConsumerParameter.oauth_callback.toString());
  StringBuilder responseValue=new StringBuilder(OAuthProviderParameter.oauth_token.toString()).append('=').append(OAuthCodec.oauthEncode(tokenValue)).append('&').append(OAuthProviderParameter.oauth_token_secret.toString()).append('=').append(OAuthCodec.oauthEncode(authToken.getSecret()));
  if (callback != null) {
    responseValue.append('&').append(OAuthProviderParameter.oauth_callback_confirmed.toString()).append("=true");
  }
  response.setContentType(getResponseContentType());
  response.getWriter().print(responseValue.toString());
  response.flushBuffer();
}
