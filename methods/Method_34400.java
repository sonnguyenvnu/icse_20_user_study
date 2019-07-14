/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,String userName){
  throw this.operationNotSupported();
}
