public void removeAccessToken(String tokenValue){
  jdbcTemplate.update(deleteAccessTokenSql,extractTokenKey(tokenValue));
}
