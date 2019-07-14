@Override protected void doClean() throws SQLException {
  for (  String dropStatement : generateDropStatements("SYNONYM")) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatements("VIEW")) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatements("TABLE")) {
    jdbcTemplate.execute(dropStatement);
  }
  for (  String dropStatement : generateDropStatements("SEQUENCE")) {
    jdbcTemplate.execute(dropStatement);
  }
}
