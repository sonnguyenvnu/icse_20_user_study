/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public OAuth2RefreshToken readRefreshToken(String tokenValue){
  throw this.operationNotSupported();
}
