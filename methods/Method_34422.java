@Override public OAuth2Authentication readAuthentication(String token){
  byte[] bytes=null;
  RedisConnection conn=getConnection();
  try {
    bytes=conn.get(serializeKey(AUTH + token));
  }
  finally {
    conn.close();
  }
  OAuth2Authentication auth=deserializeAuthentication(bytes);
  return auth;
}
