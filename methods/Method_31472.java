@Override protected boolean doEmpty() throws SQLException {
  int objectCount=jdbcTemplate.queryForInt("select count(*) from sys.tables where schema_name = ?",name);
  objectCount+=jdbcTemplate.queryForInt("select count(*) from sys.views where schema_name = ?",name);
  objectCount+=jdbcTemplate.queryForInt("select count(*) from sys.sequences where schema_name = ?",name);
  objectCount+=jdbcTemplate.queryForInt("select count(*) from sys.synonyms where schema_name = ?",name);
  return objectCount == 0;
}
