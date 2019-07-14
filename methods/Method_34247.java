@Override protected void store(String code,OAuth2Authentication authentication){
  jdbcTemplate.update(insertAuthenticationSql,new Object[]{code,new SqlLobValue(SerializationUtils.serialize(authentication))},new int[]{Types.VARCHAR,Types.BLOB});
}
