public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
  if (postOnly && !request.getMethod().equals("POST")) {
    throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
  }
  String mobile=obtainMobile(request);
  if (mobile == null) {
    mobile="";
  }
  mobile=mobile.trim();
  SmsAuthenticationToken authRequest=new SmsAuthenticationToken(mobile);
  setDetails(request,authRequest);
  return this.getAuthenticationManager().authenticate(authRequest);
}
