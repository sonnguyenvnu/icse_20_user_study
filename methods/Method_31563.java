/** 
 * Executes this sql statement using an ordinary Statement.
 * @param sql The statement to execute.
 * @return the results of the execution.
 */
public Results executeStatement(String sql){
  Results results=new Results();
  Statement statement=null;
  try {
    statement=connection.createStatement();
    statement.setEscapeProcessing(false);
    boolean hasResults;
    try {
      hasResults=statement.execute(sql);
    }
  finally {
      extractWarnings(results,statement);
    }
    extractResults(results,statement,hasResults);
  }
 catch (  final SQLException e) {
    extractErrors(results,e);
  }
 finally {
    JdbcUtils.closeStatement(statement);
  }
  return results;
}
