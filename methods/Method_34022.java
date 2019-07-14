/** 
 * Validates the OAuth parameters for the given consumer. Base implementation validates only those parameters that are required for all OAuth requests. This includes the nonce and timestamp, but not the signature.
 * @param consumerDetails The consumer details.
 * @param oauthParams     The OAuth parameters to validate.
 * @throws InvalidOAuthParametersException If the OAuth parameters are invalid.
 */
protected void validateOAuthParams(ConsumerDetails consumerDetails,Map<String,String> oauthParams) throws InvalidOAuthParametersException {
  String version=oauthParams.get(OAuthConsumerParameter.oauth_version.toString());
  if ((version != null) && (!"1.0".equals(version))) {
    throw new OAuthVersionUnsupportedException("Unsupported OAuth version: " + version);
  }
  String realm=oauthParams.get("realm");
  realm=realm == null || "".equals(realm) ? null : realm;
  if ((realm != null) && (!realm.equals(this.authenticationEntryPoint.getRealmName()))) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.incorrectRealm",new Object[]{realm,this.getAuthenticationEntryPoint().getRealmName()},"Response realm name '{0}' does not match system realm name of '{1}'"));
  }
  String signatureMethod=oauthParams.get(OAuthConsumerParameter.oauth_signature_method.toString());
  if (signatureMethod == null) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.missingSignatureMethod","Missing signature method."));
  }
  String signature=oauthParams.get(OAuthConsumerParameter.oauth_signature.toString());
  if (signature == null) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.missingSignature","Missing signature."));
  }
  String timestamp=oauthParams.get(OAuthConsumerParameter.oauth_timestamp.toString());
  if (timestamp == null) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.missingTimestamp","Missing timestamp."));
  }
  String nonce=oauthParams.get(OAuthConsumerParameter.oauth_nonce.toString());
  if (nonce == null) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.missingNonce","Missing nonce."));
  }
  try {
    getNonceServices().validateNonce(consumerDetails,Long.parseLong(timestamp),nonce);
  }
 catch (  NumberFormatException e) {
    throw new InvalidOAuthParametersException(messages.getMessage("OAuthProcessingFilter.invalidTimestamp",new Object[]{timestamp},"Timestamp must be a positive integer. Invalid value: {0}"));
  }
  validateAdditionalParameters(consumerDetails,oauthParams);
}
