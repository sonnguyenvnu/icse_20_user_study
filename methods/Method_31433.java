@Override protected void doClean() throws SQLException {
  for (  String statement : generateDropStatementsForMaterializedViews()) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForViews()) {
    jdbcTemplate.execute(statement);
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  for (  String statement : generateDropStatementsForBaseTypes(true)) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForRoutines()) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForEnums()) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForDomains()) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForSequences()) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForBaseTypes(false)) {
    jdbcTemplate.execute(statement);
  }
}
