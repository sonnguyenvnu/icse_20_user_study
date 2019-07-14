/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public Collection<OAuth2AccessToken> findTokensByClientId(String clientId){
  throw this.operationNotSupported();
}
