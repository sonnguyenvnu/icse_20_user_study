public OAuth2Authentication readAuthenticationForRefreshToken(String value){
  OAuth2Authentication authentication=null;
  try {
    authentication=jdbcTemplate.queryForObject(selectRefreshTokenAuthenticationSql,new RowMapper<OAuth2Authentication>(){
      public OAuth2Authentication mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return deserializeAuthentication(rs.getBytes(2));
      }
    }
,extractTokenKey(value));
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find access token for token " + value);
    }
  }
catch (  IllegalArgumentException e) {
    LOG.warn("Failed to deserialize access token for " + value,e);
    removeRefreshToken(value);
  }
  return authentication;
}
