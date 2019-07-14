/** 
 * This callback method is executed before the  {@link Statement#addBatch(String)} or{@link PreparedStatement#addBatch(String)} method is invoked.
 * @param statementInformation The meta information about the {@link Statement} being invoked
 * @param sql                  The SQL string provided to the execute method
 * @return sql
 */
public String onBeforeAddBatch(StatementInformation statementInformation,String sql){
  return sql;
}
