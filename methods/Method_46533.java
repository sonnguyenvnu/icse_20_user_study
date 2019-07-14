@Override public void onAfterPreparedStatementSet(PreparedStatementInformation statementInformation,int parameterIndex,Object value,SQLException e){
  DefaultEventListener.INSTANCE.onAfterPreparedStatementSet(statementInformation,parameterIndex,value,e);
  p6spyEventListener.onAfterPreparedStatementSet(statementInformation,parameterIndex,value,e);
}
