public OAuth2Authentication remove(String code){
  OAuth2Authentication authentication;
  try {
    authentication=jdbcTemplate.queryForObject(selectAuthenticationSql,new RowMapper<OAuth2Authentication>(){
      public OAuth2Authentication mapRow(      ResultSet rs,      int rowNum) throws SQLException {
        return SerializationUtils.deserialize(rs.getBytes("authentication"));
      }
    }
,code);
  }
 catch (  EmptyResultDataAccessException e) {
    return null;
  }
  if (authentication != null) {
    jdbcTemplate.update(deleteAuthenticationSql,code);
  }
  return authentication;
}
