/** 
 * This operation is not applicable for a Resource Server and if called, will throw a  {@link JwkException}.
 * @throws JwkException reporting this operation is not supported
 */
@Override public void storeRefreshToken(OAuth2RefreshToken refreshToken,OAuth2Authentication authentication){
  throw this.operationNotSupported();
}
