@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP SCHEMA " + database.quote(name) + (database.supportsDropSchemaCascade ? " CASCADE" : ""));
}
