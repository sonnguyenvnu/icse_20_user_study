@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  jdbcTemplate.execute("SET SCHEMA " + database.quote(schema));
}
