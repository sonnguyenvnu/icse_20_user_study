@Override public Authentication authenticate(Authentication authentication) throws AuthenticationException {
  SmsAuthenticationToken authenticationToken=(SmsAuthenticationToken)authentication;
  UserDetails userDetails=userDetailService.loadUserByUsername((String)authenticationToken.getPrincipal());
  if (userDetails == null)   throw new InternalAuthenticationServiceException("?????????????");
  SmsAuthenticationToken authenticationResult=new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
  authenticationResult.setDetails(authenticationToken.getDetails());
  return authenticationResult;
}
