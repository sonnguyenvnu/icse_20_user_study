@Override public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,String userName){
  byte[] approvalKey=serializeKey(UNAME_TO_ACCESS + getApprovalKey(clientId,userName));
  List<byte[]> byteList=null;
  RedisConnection conn=getConnection();
  try {
    byteList=getByteLists(approvalKey,conn);
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
