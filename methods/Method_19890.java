@Override public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException {
  String header=request.getHeader("Authorization");
  if (header == null || !header.startsWith("Basic ")) {
    throw new UnapprovedClientAuthenticationException("?????client??");
  }
  String[] tokens=this.extractAndDecodeHeader(header,request);
  String clientId=tokens[0];
  String clientSecret=tokens[1];
  TokenRequest tokenRequest=null;
  ClientDetails clientDetails=clientDetailsService.loadClientByClientId(clientId);
  if (clientDetails == null) {
    throw new UnapprovedClientAuthenticationException("clientId:" + clientId + "????????");
  }
 else   if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)) {
    throw new UnapprovedClientAuthenticationException("clientSecret???");
  }
 else {
    tokenRequest=new TokenRequest(new HashMap<>(),clientId,clientDetails.getScope(),"custom");
  }
  OAuth2Request oAuth2Request=tokenRequest.createOAuth2Request(clientDetails);
  OAuth2Authentication auth2Authentication=new OAuth2Authentication(oAuth2Request,authentication);
  OAuth2AccessToken token=authorizationServerTokenServices.createAccessToken(auth2Authentication);
  log.info("????");
  response.setContentType("application/json;charset=UTF-8");
  response.getWriter().write(new ObjectMapper().writeValueAsString(token));
}
