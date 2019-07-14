@Override public void verify(Map<String,Object> claims) throws InvalidTokenException {
  if (!CollectionUtils.isEmpty(claims) && claims.containsKey(ISS_CLAIM)) {
    String jwtIssuer=(String)claims.get(ISS_CLAIM);
    if (!jwtIssuer.equals(this.issuer.toString())) {
      throw new InvalidTokenException("Invalid Issuer (iss) claim: " + jwtIssuer);
    }
  }
}
