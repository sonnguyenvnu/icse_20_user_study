@Override public void verify(Map<String,Object> claims) throws InvalidTokenException {
  for (  JwtClaimsSetVerifier jwtClaimsSetVerifier : this.jwtClaimsSetVerifiers) {
    jwtClaimsSetVerifier.verify(claims);
  }
}
