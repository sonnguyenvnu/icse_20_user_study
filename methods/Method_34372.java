public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication){
  OAuth2AccessToken accessToken=null;
  String key=authenticationKeyGenerator.extractKey(authentication);
  try {
    accessToken=jdbcTemplate.queryForObject(selectAccessTokenFromAuthenticationSql,new RowMapper<OAuth2AccessToken>(){
      public OAuth2AccessToken mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return deserializeAccessToken(rs.getBytes(2));
      }
    }
,key);
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Failed to find access token for authentication " + authentication);
    }
  }
catch (  IllegalArgumentException e) {
    LOG.error("Could not extract access token for authentication " + authentication,e);
  }
  if (accessToken != null && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
    removeAccessToken(accessToken.getValue());
    storeAccessToken(accessToken,authentication);
  }
  return accessToken;
}
