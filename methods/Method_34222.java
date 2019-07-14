/** 
 * Extract the OAuth bearer token from a header.
 * @param request The request.
 * @return The token, or null if no OAuth authorization header was supplied.
 */
protected String extractHeaderToken(HttpServletRequest request){
  Enumeration<String> headers=request.getHeaders("Authorization");
  while (headers.hasMoreElements()) {
    String value=headers.nextElement();
    if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
      String authHeaderValue=value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
      request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,value.substring(0,OAuth2AccessToken.BEARER_TYPE.length()).trim());
      int commaIndex=authHeaderValue.indexOf(',');
      if (commaIndex > 0) {
        authHeaderValue=authHeaderValue.substring(0,commaIndex);
      }
      return authHeaderValue;
    }
  }
  return null;
}
