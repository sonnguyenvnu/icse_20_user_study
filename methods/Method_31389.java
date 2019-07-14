@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  jdbcTemplate.execute("ALTER SESSION SET CURRENT_SCHEMA=" + database.quote(schema));
}
