@Override protected boolean doExists() throws SQLException {
  return jdbcTemplate.queryForInt("SELECT COUNT (*) FROM information_schema.system_schemas WHERE table_schem=?",name) > 0;
}
