@Override public Authentication extract(HttpServletRequest request){
  String tokenValue=extractToken(request);
  if (tokenValue != null) {
    PreAuthenticatedAuthenticationToken authentication=new PreAuthenticatedAuthenticationToken(tokenValue,"");
    return authentication;
  }
  return null;
}
