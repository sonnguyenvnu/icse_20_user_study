public Claims parseJWT(String jwt){
  SecretKey key=jwtConfig.generalKey();
  return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
}
