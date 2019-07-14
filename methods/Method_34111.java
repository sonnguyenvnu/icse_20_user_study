public void removeAccessToken(OAuth2ProtectedResourceDetails resource,Authentication authentication){
  jdbcTemplate.update(deleteAccessTokenSql,keyGenerator.extractKey(resource,authentication));
}
