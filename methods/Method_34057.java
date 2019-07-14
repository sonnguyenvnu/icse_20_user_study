@Override public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
  OAuth2AccessToken accessToken;
  try {
    accessToken=restTemplate.getAccessToken();
  }
 catch (  OAuth2Exception e) {
    BadCredentialsException bad=new BadCredentialsException("Could not obtain access token",e);
    publish(new OAuth2AuthenticationFailureEvent(bad));
    throw bad;
  }
  try {
    OAuth2Authentication result=tokenServices.loadAuthentication(accessToken.getValue());
    if (authenticationDetailsSource != null) {
      request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE,accessToken.getValue());
      request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,accessToken.getTokenType());
      result.setDetails(authenticationDetailsSource.buildDetails(request));
    }
    publish(new AuthenticationSuccessEvent(result));
    return result;
  }
 catch (  InvalidTokenException e) {
    BadCredentialsException bad=new BadCredentialsException("Could not obtain user details from token",e);
    publish(new OAuth2AuthenticationFailureEvent(bad));
    throw bad;
  }
}
