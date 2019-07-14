/** 
 * Discover the provider configuration information.
 * @throws RestClientException if the provider does not support discovery or for any HTTP-related errors
 * @return the provider configuration information
 */
public ProviderConfiguration discover(){
  Map responseAttributes=this.restTemplate.getForObject(this.providerLocation,Map.class);
  ProviderConfiguration.Builder builder=new ProviderConfiguration.Builder();
  builder.issuer((String)responseAttributes.get(ISSUER_ATTR_NAME));
  builder.authorizationEndpoint((String)responseAttributes.get(AUTHORIZATION_ENDPOINT_ATTR_NAME));
  if (responseAttributes.containsKey(TOKEN_ENDPOINT_ATTR_NAME)) {
    builder.tokenEndpoint((String)responseAttributes.get(TOKEN_ENDPOINT_ATTR_NAME));
  }
  if (responseAttributes.containsKey(USERINFO_ENDPOINT_ATTR_NAME)) {
    builder.userInfoEndpoint((String)responseAttributes.get(USERINFO_ENDPOINT_ATTR_NAME));
  }
  if (responseAttributes.containsKey(JWK_SET_URI_ATTR_NAME)) {
    builder.jwkSetUri((String)responseAttributes.get(JWK_SET_URI_ATTR_NAME));
  }
  return builder.build();
}
