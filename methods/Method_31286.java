@Override protected void doClean() throws SQLException {
  List<String> dropVersioningStatements=generateDropVersioningStatement();
  if (!dropVersioningStatements.isEmpty()) {
    for (    String dropTableStatement : generateDropStatements("S","TABLE")) {
      jdbcTemplate.execute(dropTableStatement);
    }
  }
  for (  String dropVersioningStatement : dropVersioningStatements) {
    jdbcTemplate.execute(dropVersioningStatement);
  }
  for (  String dropStatement : generateDropStatementsForViews()) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatements("A","ALIAS")) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatements("G","TABLE")) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  for (  String dropStatement : generateDropStatementsForSequences()) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatementsForProcedures()) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatementsForTriggers()) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  Function function : allFunctions()) {
    function.drop();
  }
  for (  Type type : allTypes()) {
    type.drop();
  }
}
