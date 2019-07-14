@Override public String onBeforeExecuteQuery(StatementInformation statementInformation,String sql) throws SQLException {
  return onBeforeAnyExecute(statementInformation);
}
