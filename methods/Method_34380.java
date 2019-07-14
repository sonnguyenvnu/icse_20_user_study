public void removeAccessTokenUsingRefreshToken(String refreshToken){
  jdbcTemplate.update(deleteAccessTokenFromRefreshTokenSql,new Object[]{extractTokenKey(refreshToken)},new int[]{Types.VARCHAR});
}
