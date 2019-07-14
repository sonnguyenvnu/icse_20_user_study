@Override public boolean supports(AuthenticationToken token){
  return token instanceof JWTToken;
}
