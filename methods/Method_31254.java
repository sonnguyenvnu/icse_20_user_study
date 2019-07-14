@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  if (!StringUtils.hasLength(schema)) {
    schema="DEFAULT";
  }
  jdbcTemplate.execute("SET database = " + schema);
}
