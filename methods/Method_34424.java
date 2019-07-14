@Override public void storeAccessToken(OAuth2AccessToken token,OAuth2Authentication authentication){
  byte[] serializedAccessToken=serialize(token);
  byte[] serializedAuth=serialize(authentication);
  byte[] accessKey=serializeKey(ACCESS + token.getValue());
  byte[] authKey=serializeKey(AUTH + token.getValue());
  byte[] authToAccessKey=serializeKey(AUTH_TO_ACCESS + authenticationKeyGenerator.extractKey(authentication));
  byte[] approvalKey=serializeKey(UNAME_TO_ACCESS + getApprovalKey(authentication));
  byte[] clientId=serializeKey(CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId());
  RedisConnection conn=getConnection();
  try {
    conn.openPipeline();
    if (springDataRedis_2_0) {
      try {
        this.redisConnectionSet_2_0.invoke(conn,accessKey,serializedAccessToken);
        this.redisConnectionSet_2_0.invoke(conn,authKey,serializedAuth);
        this.redisConnectionSet_2_0.invoke(conn,authToAccessKey,serializedAccessToken);
      }
 catch (      Exception ex) {
        throw new RuntimeException(ex);
      }
    }
 else {
      conn.set(accessKey,serializedAccessToken);
      conn.set(authKey,serializedAuth);
      conn.set(authToAccessKey,serializedAccessToken);
    }
    if (!authentication.isClientOnly()) {
      conn.sAdd(approvalKey,serializedAccessToken);
    }
    conn.sAdd(clientId,serializedAccessToken);
    if (token.getExpiration() != null) {
      int seconds=token.getExpiresIn();
      conn.expire(accessKey,seconds);
      conn.expire(authKey,seconds);
      conn.expire(authToAccessKey,seconds);
      conn.expire(clientId,seconds);
      conn.expire(approvalKey,seconds);
    }
    OAuth2RefreshToken refreshToken=token.getRefreshToken();
    if (refreshToken != null && refreshToken.getValue() != null) {
      byte[] refresh=serialize(token.getRefreshToken().getValue());
      byte[] auth=serialize(token.getValue());
      byte[] refreshToAccessKey=serializeKey(REFRESH_TO_ACCESS + token.getRefreshToken().getValue());
      byte[] accessToRefreshKey=serializeKey(ACCESS_TO_REFRESH + token.getValue());
      if (springDataRedis_2_0) {
        try {
          this.redisConnectionSet_2_0.invoke(conn,refreshToAccessKey,auth);
          this.redisConnectionSet_2_0.invoke(conn,accessToRefreshKey,refresh);
        }
 catch (        Exception ex) {
          throw new RuntimeException(ex);
        }
      }
 else {
        conn.set(refreshToAccessKey,auth);
        conn.set(accessToRefreshKey,refresh);
      }
      if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
        ExpiringOAuth2RefreshToken expiringRefreshToken=(ExpiringOAuth2RefreshToken)refreshToken;
        Date expiration=expiringRefreshToken.getExpiration();
        if (expiration != null) {
          int seconds=Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L).intValue();
          conn.expire(refreshToAccessKey,seconds);
          conn.expire(accessToRefreshKey,seconds);
        }
      }
    }
    conn.closePipeline();
  }
  finally {
    conn.close();
  }
}
