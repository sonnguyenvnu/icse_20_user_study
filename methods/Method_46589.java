/** 
 * This callback method is executed before any  {@link java.sql.Statement}.addBatch* method is invoked
 * @param statementInformation The meta information about the {@link java.sql.Statement} being invoked
 * @return sql
 */
public String onBeforeAnyAddBatch(StatementInformation statementInformation){
  return statementInformation.getSql();
}
