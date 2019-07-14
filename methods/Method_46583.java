@Override public void onAfterPreparedStatementSet(PreparedStatementInformation statementInformation,int parameterIndex,Object value,SQLException e){
  statementInformation.setParameterValue(parameterIndex,value);
}
