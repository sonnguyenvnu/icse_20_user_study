/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public void storeAccessToken(OAuth2AccessToken token,OAuth2Authentication authentication){
  throw this.operationNotSupported();
}
