public void storeRefreshToken(OAuth2RefreshToken refreshToken,OAuth2Authentication authentication){
  jdbcTemplate.update(insertRefreshTokenSql,new Object[]{extractTokenKey(refreshToken.getValue()),new SqlLobValue(serializeRefreshToken(refreshToken)),new SqlLobValue(serializeAuthentication(authentication))},new int[]{Types.VARCHAR,Types.BLOB,Types.BLOB});
}
