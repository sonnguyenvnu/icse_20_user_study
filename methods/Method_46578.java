@Override public void onAfterExecuteQuery(PreparedStatementInformation statementInformation,long timeElapsedNanos,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
