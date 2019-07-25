protected void handle(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
  logger.debug("Passport Server Success Authentication");
  String redirect=obtainRedirect(request);
  if (StringUtils.isEmpty(redirect)) {
    logger.error("Error redirectURL is null for Passport Server");
    response.sendError(HttpStatus.UNAUTHORIZED.value(),"Error redirectURL For Passport Server");
    return;
  }
  String target=obtainTarget(request);
  DefaultPassportToken token=new DefaultPassportToken();
  String ip=IpHelper.getIpAddr(request);
  token.setIp(ip);
  PassportAuthentication passportAuthentication=DetailsConvertPassportAuth.convert((PassportUserDetails)authentication.getPrincipal());
  token.setValue(passportAuthentication);
  PassportToken pt=passportTokenStore.storeToken(token);
  String tokenKey=pt.getKey();
  UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(redirect);
  if (!StringUtils.isEmpty(target)) {
    builder.queryParam(getTargetParamName(),target);
  }
  builder.queryParam(getTokenParamName(),tokenKey);
  redirectStrategy.sendRedirect(request,response,builder.encode().toUriString());
}
