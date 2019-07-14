/** 
 * The access token validity period in seconds
 * @param clientAuth the current authorization request
 * @return the access token validity period in seconds
 */
protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth){
  if (clientDetailsService != null) {
    ClientDetails client=clientDetailsService.loadClientByClientId(clientAuth.getClientId());
    Integer validity=client.getAccessTokenValiditySeconds();
    if (validity != null) {
      return validity;
    }
  }
  return accessTokenValiditySeconds;
}
