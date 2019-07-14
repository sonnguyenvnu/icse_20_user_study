protected void executeStatement(JdbcTemplate jdbcTemplate,SqlScript sqlScript,SqlStatement sqlStatement){
  logStatementExecution(sqlStatement);
  String sql=sqlStatement.getSql() + sqlStatement.getDelimiter();
  Results results=sqlStatement.execute(jdbcTemplate);
  if (results.getException() != null) {
    printWarnings(results);
    handleException(results,sqlScript,sqlStatement);
    return;
  }
  printWarnings(results);
  handleResults(results);
}
