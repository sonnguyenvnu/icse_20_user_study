@Override public void onAfterGetResultSet(StatementInformation statementInformation,long timeElapsedNanos,SQLException e){
  DefaultEventListener.INSTANCE.onAfterGetResultSet(statementInformation,timeElapsedNanos,e);
  p6spyEventListener.onAfterGetResultSet(statementInformation,timeElapsedNanos,e);
}
