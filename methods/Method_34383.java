public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,String userName){
  List<OAuth2AccessToken> accessTokens=new ArrayList<OAuth2AccessToken>();
  try {
    accessTokens=jdbcTemplate.query(selectAccessTokensFromUserNameAndClientIdSql,new SafeAccessTokenRowMapper(),userName,clientId);
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled()) {
      LOG.info("Failed to find access token for clientId " + clientId + " and userName " + userName);
    }
  }
  accessTokens=removeNulls(accessTokens);
  return accessTokens;
}
