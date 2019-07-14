/** 
 * This callback method is executed before any of the  {@link Statement#execute(String)} methods are invoked.
 * @param statementInformation The meta information about the {@link Statement} being invoked
 * @param sql                  The SQL string provided to the execute method
 * @return sql
 * @throws SQLException SQLException
 */
public String onBeforeExecute(StatementInformation statementInformation,String sql) throws SQLException {
  return sql;
}
