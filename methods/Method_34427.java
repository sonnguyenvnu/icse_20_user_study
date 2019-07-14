@Override public OAuth2AccessToken readAccessToken(String tokenValue){
  byte[] key=serializeKey(ACCESS + tokenValue);
  byte[] bytes=null;
  RedisConnection conn=getConnection();
  try {
    bytes=conn.get(key);
  }
  finally {
    conn.close();
  }
  OAuth2AccessToken accessToken=deserializeAccessToken(bytes);
  return accessToken;
}
