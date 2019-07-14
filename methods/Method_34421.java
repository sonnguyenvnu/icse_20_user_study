@Override public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication){
  String key=authenticationKeyGenerator.extractKey(authentication);
  byte[] serializedKey=serializeKey(AUTH_TO_ACCESS + key);
  byte[] bytes=null;
  RedisConnection conn=getConnection();
  try {
    bytes=conn.get(serializedKey);
  }
  finally {
    conn.close();
  }
  OAuth2AccessToken accessToken=deserializeAccessToken(bytes);
  if (accessToken != null) {
    OAuth2Authentication storedAuthentication=readAuthentication(accessToken.getValue());
    if ((storedAuthentication == null || !key.equals(authenticationKeyGenerator.extractKey(storedAuthentication)))) {
      storeAccessToken(accessToken,authentication);
    }
  }
  return accessToken;
}
