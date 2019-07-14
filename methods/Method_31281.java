@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP SPECIFIC FUNCTION " + database.quote(schema.getName(),name));
}
