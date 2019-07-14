@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP DATABASE " + database.quote(name));
}
