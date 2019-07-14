@Override protected void validateAdditionalParameters(ConsumerDetails consumerDetails,Map<String,String> oauthParams){
  super.validateAdditionalParameters(consumerDetails,oauthParams);
  String token=oauthParams.get(OAuthConsumerParameter.oauth_token.toString());
  if (token == null) {
    throw new InvalidOAuthParametersException(messages.getMessage("AccessTokenProcessingFilter.missingToken","Missing token."));
  }
  if (isRequire10a()) {
    String verifier=oauthParams.get(OAuthConsumerParameter.oauth_verifier.toString());
    if (verifier == null) {
      throw new InvalidOAuthParametersException(messages.getMessage("AccessTokenProcessingFilter.missingVerifier","Missing verifier."));
    }
    OAuthProviderToken requestToken=getTokenServices().getToken(token);
    if (!verifier.equals(requestToken.getVerifier())) {
      throw new InvalidOAuthParametersException(messages.getMessage("AccessTokenProcessingFilter.missingVerifier","Invalid verifier."));
    }
  }
}
