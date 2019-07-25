@Override public void apply(RequestTemplate requestTemplate){
  SecurityContext securityContext=SecurityContextHolder.getContext();
  Authentication authentication=securityContext.getAuthentication();
  if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
    OAuth2AuthenticationDetails details=(OAuth2AuthenticationDetails)authentication.getDetails();
    requestTemplate.header(AUTHORIZATION_HEADER,String.format("%s %s",BEARER_TOKEN_TYPE,details.getTokenValue()));
  }
}
