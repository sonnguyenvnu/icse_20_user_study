@Override protected boolean doEmpty() throws SQLException {
  return !supportedTypesExist(jdbcTemplate,database,this);
}
