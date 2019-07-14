public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource,Authentication authentication){
  OAuth2AccessToken accessToken=null;
  try {
    accessToken=jdbcTemplate.queryForObject(selectAccessTokenSql,new RowMapper<OAuth2AccessToken>(){
      public OAuth2AccessToken mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return SerializationUtils.deserialize(rs.getBytes(2));
      }
    }
,keyGenerator.extractKey(resource,authentication));
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.debug("Failed to find access token for authentication " + authentication);
    }
  }
  return accessToken;
}
