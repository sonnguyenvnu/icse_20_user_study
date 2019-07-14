/** 
 * This callback method is executed before any  {@link java.sql.Statement}.execute* method is invoked
 * @param statementInformation The meta information about the {@link java.sql.Statement} being invoked
 * @return sql
 * @throws SQLException SQLException
 */
public String onBeforeAnyExecute(StatementInformation statementInformation) throws SQLException {
  return statementInformation.getSql();
}
