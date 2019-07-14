@Override public Collection<OAuth2AccessToken> findTokensByClientId(String clientId){
  byte[] key=serializeKey(CLIENT_ID_TO_ACCESS + clientId);
  List<byte[]> byteList=null;
  RedisConnection conn=getConnection();
  try {
    byteList=getByteLists(key,conn);
  }
  finally {
    conn.close();
  }
  if (byteList == null || byteList.size() == 0) {
    return Collections.<OAuth2AccessToken>emptySet();
  }
  List<OAuth2AccessToken> accessTokens=new ArrayList<OAuth2AccessToken>(byteList.size());
  for (  byte[] bytes : byteList) {
    OAuth2AccessToken accessToken=deserializeAccessToken(bytes);
    accessTokens.add(accessToken);
  }
  return Collections.<OAuth2AccessToken>unmodifiableCollection(accessTokens);
}
