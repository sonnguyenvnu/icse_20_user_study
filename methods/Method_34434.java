/** 
 * Loop over the  {@link #setTokenEnhancers(List) delegates} passing the result into the next member of the chain.
 * @see org.springframework.security.oauth2.provider.token.TokenEnhancer#enhance(org.springframework.security.oauth2.common.OAuth2AccessToken,org.springframework.security.oauth2.provider.OAuth2Authentication)
 */
public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,OAuth2Authentication authentication){
  OAuth2AccessToken result=accessToken;
  for (  TokenEnhancer enhancer : delegates) {
    result=enhancer.enhance(result,authentication);
  }
  return result;
}
