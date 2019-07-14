private List<String> getDbObjects(String objectType) throws SQLException {
  return jdbcTemplate.queryForStringList("select " + objectType + "_NAME from SYS." + objectType + "S where SCHEMA_NAME = ?",name);
}
