@Override protected void doCreate() throws SQLException {
  jdbcTemplate.execute("CREATE DATABASE " + database.quote(name));
}
