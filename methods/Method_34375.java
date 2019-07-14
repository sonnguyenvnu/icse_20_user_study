public OAuth2Authentication readAuthentication(String token){
  OAuth2Authentication authentication=null;
  try {
    authentication=jdbcTemplate.queryForObject(selectAccessTokenAuthenticationSql,new RowMapper<OAuth2Authentication>(){
      public OAuth2Authentication mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return deserializeAuthentication(rs.getBytes(2));
      }
    }
,extractTokenKey(token));
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find access token for token " + token);
    }
  }
catch (  IllegalArgumentException e) {
    LOG.warn("Failed to deserialize authentication for " + token,e);
    removeAccessToken(token);
  }
  return authentication;
}
