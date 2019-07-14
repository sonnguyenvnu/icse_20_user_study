/** 
 * Update the request parameters and return a new object with the same properties except the parameters.
 * @param parameters new parameters replacing the existing ones
 * @return a new OAuth2Request
 */
public OAuth2Request createOAuth2Request(Map<String,String> parameters){
  return new OAuth2Request(parameters,getClientId(),authorities,approved,getScope(),resourceIds,redirectUri,responseTypes,extensions);
}
