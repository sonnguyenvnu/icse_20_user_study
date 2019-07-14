/** 
 * {@inheritDoc}
 */
@Override public boolean isEnabled(){
  return oauthToken != null && oauthToken instanceof AccessToken;
}
