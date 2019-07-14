@Override protected void doClean() throws SQLException {
  List<String> triggerNames=listObjectNames("TRIGGER","");
  for (  String statement : generateDropStatements("TRIGGER",triggerNames,"")) {
    jdbcTemplate.execute(statement);
  }
  for (  String statement : generateDropStatementsForConstraints()) {
    jdbcTemplate.execute(statement);
  }
  List<String> viewNames=listObjectNames("TABLE","TABLETYPE='V'");
  for (  String statement : generateDropStatements("VIEW",viewNames,"")) {
    jdbcTemplate.execute(statement);
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  List<String> sequenceNames=listObjectNames("SEQUENCE","");
  for (  String statement : generateDropStatements("SEQUENCE",sequenceNames,"RESTRICT")) {
    jdbcTemplate.execute(statement);
  }
}
