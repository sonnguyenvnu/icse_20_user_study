/** 
 * Validate the signature of the request given the authentication request.
 * @param authentication The authentication request.
 */
protected void validateSignature(ConsumerAuthentication authentication) throws AuthenticationException {
  SignatureSecret secret=authentication.getConsumerDetails().getSignatureSecret();
  String token=authentication.getConsumerCredentials().getToken();
  OAuthProviderToken authToken=null;
  if (token != null && !"".equals(token)) {
    authToken=getTokenServices().getToken(token);
  }
  String signatureMethod=authentication.getConsumerCredentials().getSignatureMethod();
  OAuthSignatureMethod method;
  try {
    method=getSignatureMethodFactory().getSignatureMethod(signatureMethod,secret,authToken != null ? authToken.getSecret() : null);
  }
 catch (  UnsupportedSignatureMethodException e) {
    throw new OAuthException(e.getMessage(),e);
  }
  String signatureBaseString=authentication.getConsumerCredentials().getSignatureBaseString();
  String signature=authentication.getConsumerCredentials().getSignature();
  if (log.isDebugEnabled()) {
    log.debug("Verifying signature " + signature + " for signature base string " + signatureBaseString + " with method " + method.getName() + ".");
  }
  method.verify(signatureBaseString,signature);
}
