public void removeRefreshToken(String token){
  jdbcTemplate.update(deleteRefreshTokenSql,extractTokenKey(token));
}
