protected void onValidSignature(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
  ConsumerAuthentication authentication=(ConsumerAuthentication)SecurityContextHolder.getContext().getAuthentication();
  String token=authentication.getConsumerCredentials().getToken();
  OAuthAccessProviderToken accessToken=null;
  if (StringUtils.hasText(token)) {
    OAuthProviderToken authToken=getTokenServices().getToken(token);
    if (authToken == null) {
      throw new AccessDeniedException("Invalid access token.");
    }
 else     if (!authToken.isAccessToken()) {
      throw new AccessDeniedException("Token should be an access token.");
    }
 else     if (authToken instanceof OAuthAccessProviderToken) {
      accessToken=(OAuthAccessProviderToken)authToken;
    }
  }
 else   if ((!(authentication.getConsumerDetails() instanceof ExtraTrustConsumerDetails)) || ((ExtraTrustConsumerDetails)authentication.getConsumerDetails()).isRequiredToObtainAuthenticatedToken()) {
    throw new InvalidOAuthParametersException(messages.getMessage("ProtectedResourceProcessingFilter.missingToken","Missing auth token."));
  }
  Authentication userAuthentication=authHandler.createAuthentication(request,authentication,accessToken);
  SecurityContextHolder.getContext().setAuthentication(userAuthentication);
  chain.doFilter(request,response);
}
