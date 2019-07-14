public OAuth2Authentication readAuthenticationForRefreshToken(String token){
  RedisConnection conn=getConnection();
  try {
    byte[] bytes=conn.get(serializeKey(REFRESH_AUTH + token));
    OAuth2Authentication auth=deserializeAuthentication(bytes);
    return auth;
  }
  finally {
    conn.close();
  }
}
