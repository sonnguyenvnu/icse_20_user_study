@Override protected void doClean() throws SQLException {
  for (  Table table : allTables()) {
    table.drop();
  }
  List<String> sequenceNames=listObjectNames("SEQUENCE","IS_GENERATED = false");
  for (  String statement : generateDropStatements("SEQUENCE",sequenceNames)) {
    jdbcTemplate.execute(statement);
  }
  List<String> constantNames=listObjectNames("CONSTANT","");
  for (  String statement : generateDropStatements("CONSTANT",constantNames)) {
    jdbcTemplate.execute(statement);
  }
  List<String> aliasNames=jdbcTemplate.queryForStringList("SELECT ALIAS_NAME FROM INFORMATION_SCHEMA.FUNCTION_ALIASES WHERE ALIAS_SCHEMA = ?",name);
  for (  String statement : generateDropStatements("ALIAS",aliasNames)) {
    jdbcTemplate.execute(statement);
  }
  List<String> domainNames=listObjectNames("DOMAIN","");
  if (!domainNames.isEmpty()) {
    if (name.equals(database.getMainConnection().getCurrentSchema().getName())) {
      for (      String statement : generateDropStatementsForCurrentSchema("DOMAIN",domainNames)) {
        jdbcTemplate.execute(statement);
      }
    }
 else {
      LOG.error("Unable to drop DOMAIN objects in schema " + database.quote(name) + " due to H2 bug! (More info: http://code.google.com/p/h2database/issues/detail?id=306)");
    }
  }
}
