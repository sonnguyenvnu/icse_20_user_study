@Override protected void doCreate() throws SQLException {
  jdbcTemplate.execute("CREATE SCHEMA " + database.quote(name));
}
