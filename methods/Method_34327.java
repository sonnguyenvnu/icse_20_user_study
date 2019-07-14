/** 
 * Tries to discover the grant type requested for the token associated with this request.
 * @return the grant type if known, or null otherwise
 */
public String getGrantType(){
  if (getRequestParameters().containsKey(OAuth2Utils.GRANT_TYPE)) {
    return getRequestParameters().get(OAuth2Utils.GRANT_TYPE);
  }
  if (getRequestParameters().containsKey(OAuth2Utils.RESPONSE_TYPE)) {
    String response=getRequestParameters().get(OAuth2Utils.RESPONSE_TYPE);
    if (response.contains("token")) {
      return "implicit";
    }
  }
  return null;
}
