@Override public OAuth2RefreshToken readRefreshToken(String tokenValue){
  byte[] key=serializeKey(REFRESH + tokenValue);
  byte[] bytes=null;
  RedisConnection conn=getConnection();
  try {
    bytes=conn.get(key);
  }
  finally {
    conn.close();
  }
  OAuth2RefreshToken refreshToken=deserializeRefreshToken(bytes);
  return refreshToken;
}
