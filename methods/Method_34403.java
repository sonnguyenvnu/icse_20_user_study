/** 
 * This operation (JWT signing) is not supported and if called, will throw a  {@link JwkException}.
 * @throws JwkException
 */
@Override protected String encode(OAuth2AccessToken accessToken,OAuth2Authentication authentication){
  throw new JwkException("JWT signing (JWS) is not supported.");
}
