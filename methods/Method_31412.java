@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP USER " + database.quote(name) + " CASCADE");
}
