public Collection<OAuth2AccessToken> findTokensByUserName(String userName){
  List<OAuth2AccessToken> accessTokens=new ArrayList<OAuth2AccessToken>();
  try {
    accessTokens=jdbcTemplate.query(selectAccessTokensFromUserNameSql,new SafeAccessTokenRowMapper(),userName);
  }
 catch (  EmptyResultDataAccessException e) {
    if (LOG.isInfoEnabled())     LOG.info("Failed to find access token for userName " + userName);
  }
  accessTokens=removeNulls(accessTokens);
  return accessTokens;
}
