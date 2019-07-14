protected Map<String,Object> decode(String token){
  try {
    Jwt jwt=JwtHelper.decodeAndVerify(token,verifier);
    String claimsStr=jwt.getClaims();
    Map<String,Object> claims=objectMapper.parseMap(claimsStr);
    if (claims.containsKey(EXP) && claims.get(EXP) instanceof Integer) {
      Integer intValue=(Integer)claims.get(EXP);
      claims.put(EXP,new Long(intValue));
    }
    this.getJwtClaimsSetVerifier().verify(claims);
    return claims;
  }
 catch (  Exception e) {
    throw new InvalidTokenException("Cannot convert access token to JSON",e);
  }
}
