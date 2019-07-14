protected String extractToken(HttpServletRequest request){
  String token=extractHeaderToken(request);
  if (token == null) {
    logger.debug("Token not found in headers. Trying request parameters.");
    token=request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
    if (token == null) {
      logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
    }
 else {
      request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,OAuth2AccessToken.BEARER_TYPE);
    }
  }
  return token;
}
