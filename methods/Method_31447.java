@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  if ("unset".equals(schema)) {
    schema="";
  }
  jdbcTemplate.execute("SELECT set_config('search_path', ?, false)",schema);
}
