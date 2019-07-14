@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  jdbcTemplate.execute("SELECT set_config('search_path', ?, false)",schema);
}
