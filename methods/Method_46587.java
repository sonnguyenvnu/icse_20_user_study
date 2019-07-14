/** 
 * This callback method is executed before the  {@link Statement#executeQuery(String)} method is invoked.
 * @param statementInformation The meta information about the {@link Statement} being invoked
 * @param sql                  The SQL string provided to the execute method
 * @return sql
 * @throws SQLException SQLException
 */
public String onBeforeExecuteQuery(StatementInformation statementInformation,String sql) throws SQLException {
  return sql;
}
