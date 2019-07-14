@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP TABLE " + database.quote(schema.getName(),name) + " CASCADE");
}
