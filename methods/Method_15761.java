public String createJWT(String id,String subject,long ttlMillis){
  SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
  long nowMillis=System.currentTimeMillis();
  Date now=new Date(nowMillis);
  SecretKey key=jwtConfig.generalKey();
  JwtBuilder builder=Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).signWith(signatureAlgorithm,key);
  if (ttlMillis >= 0) {
    long expMillis=nowMillis + ttlMillis;
    Date exp=new Date(expMillis);
    builder.setExpiration(exp);
  }
  return builder.compact();
}
