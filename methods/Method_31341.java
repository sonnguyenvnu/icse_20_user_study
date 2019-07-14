@Override protected void doCreate() throws SQLException {
  String user=jdbcTemplate.queryForString("SELECT USER() FROM (VALUES(0))");
  jdbcTemplate.execute("CREATE SCHEMA " + database.quote(name) + " AUTHORIZATION " + user);
}
