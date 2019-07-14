void setCurrentDatabase(String databaseName) throws SQLException {
  jdbcTemplate.execute("USE " + database.quote(databaseName));
}
