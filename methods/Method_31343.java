@Override protected void doClean() throws SQLException {
  for (  Table table : allTables()) {
    table.drop();
  }
  for (  String statement : generateDropStatementsForSequences()) {
    jdbcTemplate.execute(statement);
  }
}
