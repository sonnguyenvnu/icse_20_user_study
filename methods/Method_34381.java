public Collection<OAuth2AccessToken> findTokensByClientId(String clientId){
  List<OAuth2AccessToken> accessTokens=new ArrayList<OAuth2AccessToken>();
  try {
    accessTokens=jdbcTemplate.query(selectAccessTokensFromClientIdSql,new SafeAccessTokenRowMapper(),clientId);
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find access token for clientId " + clientId);
    }
  }
  accessTokens=removeNulls(accessTokens);
  return accessTokens;
}
