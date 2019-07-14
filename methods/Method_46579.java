@Override public void onAfterExecuteQuery(StatementInformation statementInformation,long timeElapsedNanos,String sql,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
