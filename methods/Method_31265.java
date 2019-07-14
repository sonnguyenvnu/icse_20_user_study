@Override protected void doClean() throws SQLException {
  for (  String statement : generateDropStatementsForViews()) {
    jdbcTemplate.execute(statement);
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  for (  String statement : generateDropStatementsForSequences()) {
    jdbcTemplate.execute(statement);
  }
}
