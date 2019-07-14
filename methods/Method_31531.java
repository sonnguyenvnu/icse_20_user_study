@Override protected void doDrop() throws SQLException {
  jdbcTemplate.execute("DROP TABLE " + getName());
}
