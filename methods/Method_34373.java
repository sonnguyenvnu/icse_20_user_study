public OAuth2AccessToken readAccessToken(String tokenValue){
  OAuth2AccessToken accessToken=null;
  try {
    accessToken=jdbcTemplate.queryForObject(selectAccessTokenSql,new RowMapper<OAuth2AccessToken>(){
      public OAuth2AccessToken mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return deserializeAccessToken(rs.getBytes(2));
      }
    }
,extractTokenKey(tokenValue));
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find access token for token " + tokenValue);
    }
  }
catch (  IllegalArgumentException e) {
    LOG.warn("Failed to deserialize access token for " + tokenValue,e);
    removeAccessToken(tokenValue);
  }
  return accessToken;
}
