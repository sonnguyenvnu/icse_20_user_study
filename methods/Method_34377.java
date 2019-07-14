public OAuth2RefreshToken readRefreshToken(String token){
  OAuth2RefreshToken refreshToken=null;
  try {
    refreshToken=jdbcTemplate.queryForObject(selectRefreshTokenSql,new RowMapper<OAuth2RefreshToken>(){
      public OAuth2RefreshToken mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return deserializeRefreshToken(rs.getBytes(2));
      }
    }
,extractTokenKey(token));
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find refresh token for token " + token);
    }
  }
catch (  IllegalArgumentException e) {
    LOG.warn("Failed to deserialize refresh token for token " + token,e);
    removeRefreshToken(token);
  }
  return refreshToken;
}
