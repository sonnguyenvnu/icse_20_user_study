/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public void removeRefreshToken(OAuth2RefreshToken token){
  throw this.operationNotSupported();
}
