@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT count(tbl_name) FROM " + database.quote(schema.getName()) + ".sqlite_master WHERE type='table' AND tbl_name='" + name + "'") > 0;
}
