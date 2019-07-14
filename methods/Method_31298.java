@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP TYPE " + database.quote(schema.getName(),name));
}
