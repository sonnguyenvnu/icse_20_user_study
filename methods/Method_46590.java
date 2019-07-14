@Override public String onBeforeExecute(StatementInformation statementInformation,String sql) throws SQLException {
  return onBeforeAnyExecute(statementInformation);
}
