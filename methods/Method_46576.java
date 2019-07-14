@Override public void onAfterExecuteUpdate(PreparedStatementInformation statementInformation,long timeElapsedNanos,int rowCount,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
