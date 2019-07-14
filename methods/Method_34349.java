/** 
 * Is a refresh token supported for this client (or the global setting if {@link #setClientDetailsService(ClientDetailsService) clientDetailsService} is not set.
 * @param clientAuth the current authorization request
 * @return boolean to indicate if refresh token is supported
 */
protected boolean isSupportRefreshToken(OAuth2Request clientAuth){
  if (clientDetailsService != null) {
    ClientDetails client=clientDetailsService.loadClientByClientId(clientAuth.getClientId());
    return client.getAuthorizedGrantTypes().contains("refresh_token");
  }
  return this.supportRefreshToken;
}
