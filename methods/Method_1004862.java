public static List<SchemaChange> parse(String currentDB,String sql){
  if (matchesBlacklist(sql)) {
    return null;
  }
  while (true) {
    try {
      return parseSQL(currentDB,sql);
    }
 catch (    ReparseSQLException e) {
      sql=e.getSQL();
      LOGGER.debug("rewrote SQL to " + sql);
    }
catch (    ParseCancellationException e) {
      LOGGER.debug("Parse cancelled: " + e);
      return null;
    }
catch (    MaxwellSQLSyntaxError e) {
      LOGGER.error("Error parsing SQL: '" + sql + "'");
      throw (e);
    }
  }
}
