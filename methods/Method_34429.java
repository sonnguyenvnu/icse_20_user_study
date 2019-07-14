public void removeRefreshToken(String tokenValue){
  byte[] refreshKey=serializeKey(REFRESH + tokenValue);
  byte[] refreshAuthKey=serializeKey(REFRESH_AUTH + tokenValue);
  byte[] refresh2AccessKey=serializeKey(REFRESH_TO_ACCESS + tokenValue);
  byte[] access2RefreshKey=serializeKey(ACCESS_TO_REFRESH + tokenValue);
  RedisConnection conn=getConnection();
  try {
    conn.openPipeline();
    conn.del(refreshKey);
    conn.del(refreshAuthKey);
    conn.del(refresh2AccessKey);
    conn.del(access2RefreshKey);
    conn.closePipeline();
  }
  finally {
    conn.close();
  }
}
