@Override public void doChangeCurrentSchemaOrSearchPathTo(String schema) throws SQLException {
  if (StringUtils.hasLength(schema)) {
    jdbcTemplate.getConnection().setCatalog(schema);
  }
 else {
    try {
      String newDb=database.quote(UUID.randomUUID().toString());
      jdbcTemplate.execute("CREATE SCHEMA " + newDb);
      jdbcTemplate.execute("USE " + newDb);
      jdbcTemplate.execute("DROP SCHEMA " + newDb);
    }
 catch (    Exception e) {
      LOG.warn("Unable to restore connection to having no default schema: " + e.getMessage());
    }
  }
}
